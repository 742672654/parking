package com.example.parking.activety;


import com.example.parking.R;
import com.example.parking.Static_bean;
import com.example.parking.http.HttpManager2;
import com.example.parking.util.FileUtil;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;



public class MainActivity extends MainBaseActivity {


    private static final String TAG = "MainActivity<主>";

    public static final int parkingPhoto = 101; //停车拍照

    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.activity = this;
        super.onCreate(savedInstanceState);
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

                        //获取安卓Uri
                        imageUri = getOutputMediaFileUri();
                        //发起拍照
                        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        //Android7.0添加临时权限标记
                        startActivityForResult( openCameraIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION), MainActivity.parkingPhoto);
                    break;

                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

   public void openParking(){

       FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
       fragmentTransaction.replace(R.id.activity_fragment, parkingFragment);
       fragmentTransaction.addToBackStack(null);
       fragmentTransaction.commit();
   }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i(TAG,"系统相机拍照完成，requestCode="+requestCode+"; resultCode="+resultCode+"; File_Path="+FileUtil.getFile_Path(imageUri));

        if ( requestCode == MainActivity.parkingPhoto ){

            Map<String,String> params = new HashMap<String,String>() ;
            params.put("token", userBean.getToken());
            params.put("pathname",FileUtil.getSDCardPath());
            HttpManager2.onResponseFile(Static_bean.photoToOss, params, "file",
                    FileUtil.getFile_Suffix(imageUri),
                    FileUtil.getFile_Byte(imageUri),
          MainActivity.this,
             "photoToOss_parking");
        }


    }


    @Override
    public void onResponseFile(String url, Map<String, String> param, String sign, String object) {
        super.onResponseFile( url,  param,  sign,  object);
        Log.i( TAG,"url="+url+";  param="+param+";  object="+object );


        switch (sign){

            case "photoToOss_parking":
               // parkingFragment.parking_carmun( object );
                break;

            default:
                break;
        }





    }

}
