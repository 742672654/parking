package com.example.parking.fragment;


import android.util.Log;
import android.view.View;

import java.util.Map;


/**
 * 功能说明: 订单Fragment
 * 日期:	2019年5月29日
 * 开发者:	KXD
 */
public class MyFragment extends MyBase {



    public static final String TAG = "MyFragment";


    public void onStart() {
        super.onStart();
        super.onPosition(TAG);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            default:
                break;
        }
    }

    @Override
    public void onResponseGET(String url, Map<String, String> param, String sign, String object) {

        Log.i(TAG, url + "----" + object+"'****"+param);

        switch (sign) {
            default:
                break;
        }
    }

    @Override
    public void onResponsePOST(String url, Map<String, String> param, String sign, String object) {

        Log.i(TAG, url + "----" + object+"'****"+param);

        switch (sign) {
            default:
                break;
        }
    }



}