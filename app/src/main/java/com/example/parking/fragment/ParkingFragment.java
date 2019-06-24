package com.example.parking.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.parking.R;
import com.example.parking.activety.MainActivity;
import com.example.parking.bean.PhotoToOssBean;
import com.example.parking.printer.PrintBillService;
import com.example.parking.printer.PrinterManagerActivity;
import com.google.gson.Gson;
import java.net.URLDecoder;



/**
 * 功能说明: 停车Fragment
 * 日期:	2019年5月29日
 * 开发者:	KXD
 */
public class ParkingFragment extends ParkingBase {



    public static final String TAG = "ParkingFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView( inflater, container, savedInstanceState );
        return rootView;
    }



    @Override
    public void onClick(View view) {
        super.onClick(view);

        switch (view.getId()) {

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

                printer_marking(1,"http://www.baidu.com");
                break;

            default:
                break;
        }
    }

    //TODO 修改车牌号
    public void parking_carmun(String obj) {

        try {

            final PhotoToOssBean photoToOssBean = new Gson().fromJson(URLDecoder.decode(obj, "UTF-8"), PhotoToOssBean.class);

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    parking_carmun.setText(photoToOssBean.getCarmun());

                    Intent intentService = new Intent(activity, PrintBillService.class);
                    intentService.putExtra("SPRT", PrinterManagerActivity.CfgStr);
                    activity.startService(intentService);
                }
            });
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }
}