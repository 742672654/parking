package com.example.parking.activety;


import com.example.parking.R;
import com.example.parking.Shared.User_Shared;
import com.example.parking.Static_bean;
import com.example.parking.bean.PhotoToOssBean;
import com.example.parking.fragment.MyFragment;
import com.example.parking.fragment.OrderFragment;
import com.example.parking.fragment.ParkingFragment;
import com.example.parking.http.HttpManager2;
import com.example.parking.util.FileUtil;
import com.example.parking.util.StringUtil;
import com.google.gson.Gson;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends MainBaseActivity {


    private static final String TAG = "MainActivity";


    public static final int parkingPhoto = 101; //停车拍照

    private String pathname = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //默认第一页
        setTitle ("车辆入场");



        super.activity = this;

        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        myFragment = new MyFragment();
        orderFragment = new OrderFragment();
        parkingFragment =  new ParkingFragment();
        userBean = User_Shared.getALL(getApplicationContext());


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_fragment, parkingFragment);
        fragmentTransaction.commit();

    }


    public static Handler getHandler(){

        return activity.handler;
    }

    Uri imageUri;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {

            Log.i(TAG, "收到Handler"+"joinType:" + msg.getData().get("joinType")+";msg.what="+msg.what);

            switch (msg.what) {

                //TODO 停车拍照
                case MainActivity.parkingPhoto:

                    try {
                        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        imageUri = getOutputMediaFileUri();
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

                        //Android7.0添加临时权限标记
                        openCameraIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                        startActivityForResult(openCameraIntent, 101);

                    } catch (Exception e) {

                        Log.w(TAG,e.toString());
                    }

                    break;

                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };







    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_1:
                    setTitle ("车辆入场");
                    fragmentTransaction.replace(R.id.activity_fragment, parkingFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_2:
                    setTitle ("车辆列表");
                    fragmentTransaction.replace(R.id.activity_fragment, orderFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_3:
                    setTitle ("个人中心");
                    fragmentTransaction.replace(R.id.activity_fragment,myFragment);
                    fragmentTransaction.commit();
                    return true;
            }
            return false;
        }
    };





    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {


        switch (requestCode) {
            case 1: {

                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        Log.d("动态权限","权限成功："+ permissions[i]);
                    } else if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        Log.e("动态权限","权限失败："+ permissions[i]);
                    }
                }
            }
            break;
            default: {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }


    String uuid;
    protected Uri getOutputMediaFileUri() {

        File mediaFile = null;
        try {
            File  mediaStorageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    return null;
                }
            }
            uuid=StringUtil.getUuid();
            mediaFile = new File(mediaStorageDir.getPath()
                    + File.separator + "Download/"+uuid+".jpg");
            mediaFile.getAbsolutePath();

        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
           android7.0之后，报FileUriExposedException
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {// sdk >= 24  android7.0以上
            Uri contentUri = FileProvider.getUriForFile(this,
                    this.getApplicationContext().getPackageName() + ".provider",//与清单文件中android:authorities的值保持一致
                    mediaFile);//FileProvider方式或者ContentProvider。也可使用VmPolicy方式

            return contentUri;
        } else {
            return Uri.fromFile(mediaFile);//或者 Uri.isPaise("file://"+file.toString()
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {






        String dz = FileUtil.getSDCardPath()+"/Download/"+uuid+".jpg";


        toast_makeText("系统相机拍照完成，requestCode="+requestCode+";resultCode="+resultCode+";pathname="+"----"+"++++"+dz);

        if ( requestCode == parkingPhoto ){

            Map<String,String> params = new HashMap<String,String>() ;
            params.put("token", userBean.getToken());
            params.put("pathname",FileUtil.getSDCardPath());

            String []  photoPath =  dz.split("/");
            InputStream in = null;
            byte[] databyte =null;

            try {

                in = new FileInputStream(dz);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024 * 4];
                int n = 0;
                while ((n = in.read(buffer)) != -1) { out.write(buffer, 0, n); }
                databyte = out.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
                Log.w(TAG,e.toString());
            }finally {
                try {
                    if (in!=null)in.close();
                } catch (IOException e) {
                    Log.w(TAG,e.toString());
                }
            }
            toast_makeText("上传图片地址="+databyte.length);
            HttpManager2.onResponseFile(Static_bean.photoToOss,params, "file",photoPath[photoPath.length-1],databyte,MainActivity.this, "photoToOss");
        }
    }


    @Override
    public void onResponseFile(String url, Map<String, String> param, String sign, String object) {
        super.onResponseFile( url,  param,  sign,  object);


        try {
            object = URLDecoder.decode(object,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        toast_makeText("拍照结果，"+object);
        try{

            PhotoToOssBean photoToOssBean = new Gson().fromJson(object,PhotoToOssBean.class);


            Log.i(TAG,"photoToOssBean.toString()="+photoToOssBean.toString());

            if (!"".equals(photoToOssBean.getCarmun())){

                parkingFragment.parking_carmun(photoToOssBean.getCarmun(),pathname,photoToOssBean.getImgurl());
            }else {

                toast_makeText( "拍照失败，" + object );
            }
        }catch (Exception e){

            e.printStackTrace();
        }

    }

}
