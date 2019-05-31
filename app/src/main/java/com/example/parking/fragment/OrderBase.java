package com.example.parking.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.example.parking.R;
import com.example.parking.http.HttpCallBack2;

import java.util.Map;


/**
 * 功能说明: 订单Fragment
 * 日期:	2019年5月29日
 * 开发者:	KXD
 */
public class OrderBase extends BaseFragment implements OnClickListener, HttpCallBack2 {


    private static final String TAG = "OrderBase";




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.order_fragment, container, false);
        return rootView;
    }

    @Override
    public void onClick(View v){}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    // 初始化控件
    private void initView(View rootView) {

    }


    @Override
    public void onResponseGET(String url, Map<String, String> param, String sign, String object) {

    }

    @Override
    public void onResponsePOST(String url, Map<String, String> param, String sign, String object) {

    }

    @Override
    public void onResponseFile(String url, Map<String, String> param, String sign, String object) {

    }

}
