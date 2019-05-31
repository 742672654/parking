package com.example.parking.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.example.parking.R;
import com.example.parking.activety.MainActivity;

import java.util.HashMap;
import java.util.Map;


/**
 * 功能说明: 停车Fragment
 * 日期:	2019年5月29日
 * 开发者:	KXD
 */
public class ParkingFragment extends ParkingBase {



    private static final String TAG = "ParkingFragment";

    private String pathname = null; //本地地址
    private int pre_price = 0;      //预交费用
    private String inimage = null;  //远程地址
    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {

            case R.id.parking_photo:
                Bundle bundle = new Bundle();
                bundle.putString( "joinType",TAG );
                bundle.putString("text","停车拍照");
                Message message2 = new Message();
                message2.what = MainActivity.parkingPhoto;
                Handler handler2 = MainActivity.getHandler();
                handler2.sendMessage(message2);
                break;
            case R.id.button_order_add:

                Map<String,String> map = new HashMap<String,String>();
                    map.put("token",activity.userBean.getToken());
                    map.put("carnum",parking_carmun.getText().toString());
                    map.put("preprice",parking_pre_price.getText().toString());
                    map.put("ordertype","1");
                    map.put("inimage",inimage);
                    map.put("subid","");
                    map.put("subname","");
                break;
            default:
                break;
        }
    }

//TODO 修改车牌号,并且显示<保存订单>按钮
    public void parking_carmun(final String carmun,String pathname0,String inimage0){
        pathname = pathname0;
        inimage = inimage0;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                parking_carmun.setText(carmun);
                parking_pre_price.setText(pre_price);

                button_order_add.setVisibility( View.INVISIBLE );
            }
        });
    }



    @Override
    public void onResponseGET(String url, Map<String, String> param, String sign, String object) {

        Log.i(TAG, url + "----" + object+"'****"+param);

        switch (sign) {
            default:
                break;
        }
    }

}