package com.example.parking.activety;


import com.example.parking.R;
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
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_fragment, new ParkingFragment());
        fragmentTransaction.commit();
        super.activity = this;
    }
    public static Handler getHandler(){

        return activity.handler;
    }


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {

            Log.i(TAG, "收到Handler"+"joinType:" + msg.getData().get("joinType")+";msg.what="+msg.what);

            switch (msg.what) {

                //TODO 停车拍照
                case MainActivity.parkingPhoto:

                    Intent intent=new Intent();
                    // 指定开启系统相机的Action
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    // 根据文件地址创建文件
                    pathname = FileUtil.getSDCardPath() + "/parking/" + StringUtil.getUuid() + ".jpg";
                    File file=new File(pathname);
                    // 把文件地址转换成Uri格式
                    Uri uri= Uri.fromFile(file);
                    // 设置系统相机拍摄照片完成后图片文件的存放地址
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(intent, parkingPhoto);
                    break;


                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        Log.i(TAG, "系统相机拍照完成，requestCode="+requestCode+";resultCode="+resultCode+";pathname="+pathname);

        if ( requestCode == parkingPhoto ){

            Map<String,String> params = new HashMap<String,String>() ;
            params.put("token", userBean.getToken());
            params.put("pathname",pathname);


            String []  photoPath =  pathname.split("//");
            InputStream in = null;
            byte[] databyte =null;

            try {

                in = new FileInputStream(pathname);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024 * 4];
                int n = 0;
                while ((n = in.read(buffer)) != -1) { out.write(buffer, 0, n); }
                databyte = out.toByteArray();
            } catch (Exception e) {
                Log.w(TAG,e.toString());
            }finally {
                  try {
                      if (in!=null)in.close();
                  } catch (IOException e) {
                      Log.w(TAG,e.toString());
                  }
            }

            HttpManager2.onResponseFile(Static_bean.photoToOss,params, "file",photoPath[photoPath.length-1],databyte,MainActivity.this, "photoToOss");
        }
    }


    @Override
    public void onResponseFile(String url, Map<String, String> param, String sign, String object) {
        super.onResponseFile( url,  param,  sign,  object);

        PhotoToOssBean photoToOssBean = new Gson().fromJson(object,PhotoToOssBean.class);
        if (photoToOssBean.getCode()==200){

            parkingFragment.parking_carmun(photoToOssBean.getData().getCarmun(),pathname,photoToOssBean.getData().getImgurl());
        }else {

            toast_makeText("拍照失败，"+photoToOssBean.getMessage());
        }
    }

}
