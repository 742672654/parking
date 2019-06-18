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
import com.example.parking.bean.ParkingSpaceBean;
import com.example.parking.listView.ParkingSpaceView;
import com.example.parking.printer.PrintBillService;
import com.example.parking.printer.PrinterManagerActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 功能说明: 停车Fragment
 * 日期:	2019年5月29日
 * 开发者:	KXD
 */
public class ParkingFragment extends ParkingBase {



    private static final String TAG = "ParkingFragment";

    private String pathname = null; //本地地址
    private String inimage = null;  //远程地址


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView( inflater, container, savedInstanceState );
        parkingSpaceView = new ParkingSpaceView( this, rootView );
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

                Map<String,String> map = new HashMap<String,String>();
                    map.put("token",activity.userBean.getToken());
                    map.put("carnum",parking_carmun.getText().toString());
                    map.put("preprice",parking_pre_price.getText().toString());
                    map.put("ordertype","1");
                    map.put("inimage",inimage);
                    map.put("subid","");
                    map.put("subname","");
                break;
            case R.id.btn_choice_parkingspace:

                List<ParkingSpaceBean> list = new ArrayList<ParkingSpaceBean>();

                ParkingSpaceBean ss = new ParkingSpaceBean();
                ss.setId("asdasd");
                ss.setSubname("sadasdasd                               ");

                list.add(ss);

                parkingSpaceView.showPopupWindow( list );


                break;

            default:
                break;
        }
    }

//TODO 修改车牌号,并且显示<保存订单>按钮
    public void parking_carmun(final String carmun,String pathname0,String inimage0){
        pathname = pathname0;
        inimage = inimage0;


        if (getActivity()==null){

            Log.i(TAG,"activity===null");
        }

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                parking_carmun.setText(carmun);
                button_order_add.setVisibility( View.VISIBLE ); //INVISIBLE

                try{

                    Intent intentService = new Intent(getActivity(), PrintBillService.class);
                    intentService.putExtra("SPRT", PrinterManagerActivity.CfgStr);
                    getActivity().startService(intentService);
                }catch (Exception e){
                    e.printStackTrace();
                }

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