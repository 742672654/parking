package com.example.parking.activety;


import com.example.parking.R;
import com.example.parking.Static_bean;
import com.example.parking.bean.http.OrderlistBean;
import com.example.parking.bean.http.SelectSubPlaceBean;
import com.example.parking.http.HttpCallBack2;
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



public class MainActivity extends MainBaseActivity implements HttpCallBack2 {


    private static final String TAG = "MainActivity<主>";

    public static final int parkingPhoto = 101; //停车拍照
    public static final int orderPhoto = 102; //订单拍照
    public static final int updateTieleParking = 103;//修改车位数量
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.activity = this;
        super.onCreate(savedInstanceState);
    }



    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {

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

                //TODO 订单拍照
                case MainActivity.orderPhoto:

                    //获取安卓Uri
                    imageUri = getOutputMediaFileUri();
                    //发起拍照
                    Intent openCameraIntent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    openCameraIntent2.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    //Android7.0添加临时权限标记
                    startActivityForResult( openCameraIntent2.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION), MainActivity.orderPhoto);
                    break;

                //TODO 修改title车位数量
                case MainActivity.updateTieleParking:
                    parking_surplus.setText(String.valueOf(msg.getData().getInt("surplus")));
                    parking_already.setText(String.valueOf(msg.getData().getInt("already")));
                    break;

                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

   public void openParking(final SelectSubPlaceBean.SelectSubPlaceData selectSubPlaceDate){

       MainActivity.this.runOnUiThread(new Runnable() {
           @Override
           public void run() {

               FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
               fragmentTransaction.replace(R.id.activity_fragment, parkingFragment);

               Bundle bundle = new Bundle();
               bundle.putSerializable("selectSubPlaceDate",selectSubPlaceDate);
               parkingFragment.setArguments(bundle);


               fragmentTransaction.addToBackStack(null);
               fragmentTransaction.commit();
           }
       });

   }

    public void openParkingIndex(){

        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.activity_fragment, parkingIndexFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    public void openOrder_details(final OrderlistBean.OrderlistData orderlistData ){

        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.activity_fragment, order_detailsFragment);
                fragmentTransaction.addToBackStack(null);

                Bundle bundle = new Bundle();
                bundle.putSerializable("orderlistData",orderlistData);

                order_detailsFragment.setArguments(bundle);

                fragmentTransaction.commit();
            }
        });
    }

    public void openOrder(){

        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.activity_fragment, orderFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i(TAG,resultCode == -1 ?FileUtil.getFile_Suffix(imageUri)+"----"+ FileUtil.getFile_Byte(imageUri).length
                +"系统相机拍照完成，requestCode="+requestCode+"; resultCode="+resultCode+"; File_Path="+FileUtil.getFile_Path(imageUri):"拍照取消");

        if ( requestCode == MainActivity.parkingPhoto && resultCode == -1 ){

            toast_makeText("照片正在上传......");

            Map<String,String> params = new HashMap<String,String>(1) ;
            params.put("token", userBean.getToken());
            HttpManager2.onResponseFile(Static_bean.photoToOss, params,
                    "file",
                    FileUtil.getFile_Suffix(imageUri),
                    FileUtil.getFile_Byte(imageUri),
          this,
             "photoToOss_parking");
            Log.i(TAG,"上传停车页面拍摄的照片");
        }

        if ( requestCode == MainActivity.orderPhoto && resultCode == -1 ){

            toast_makeText("照片正在上传......");

            Map<String,String> params = new HashMap<String,String>(1) ;
            params.put("token", userBean.getToken());
            HttpManager2.onResponseFile(Static_bean.photoToOss, params,
                    "file",
                    FileUtil.getFile_Suffix(imageUri),
                    FileUtil.getFile_Byte(imageUri),
                    this,
                    "orderPhoto");
            Log.i(TAG,"上传订单页面拍摄的照片");
        }
    }


    @Override
    public void onResponseFile(String url, Map<String, String> param, String sign, String object) {


        Log.i( TAG,"asdasdasd");
        super.onResponseFile( url,  param,  sign,  object);

        Log.i( TAG,"url="+url+";  param="+param+"; \r\n object="+object );

        switch (sign){
            case "photoToOss_parking":
                parkingFragment.parking_carmun( object );
                break;

            case "orderPhoto":
                orderFragment.parking_carmun( object );
                break;



            default:
                break;
        }
    }

}
