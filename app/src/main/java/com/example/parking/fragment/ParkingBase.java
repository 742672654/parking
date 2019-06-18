package com.example.parking.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.parking.R;
import com.example.parking.bean.ParkingSpaceBean;
import com.example.parking.http.HttpCallBack2;
import com.example.parking.listView.ParkingSpaceView;
import com.example.parking.util.FileUtil;
import com.example.parking.util.StringUtil;

import java.io.File;
import java.util.Map;


/**
 * 功能说明: 停车Fragment
 * 日期:	2019年5月29日
 * 开发者:	KXD
 */
public class ParkingBase extends BaseFragment implements OnClickListener, HttpCallBack2 {


    private static final String TAG = "ParkingBase";


    protected TextView parking_carmun;//车牌号
    protected TextView parking_pre_price;//预交费用
    protected Button parking_photo;
    protected Button button_order_add;
    protected Button button_choice_parkingspace;

    protected ParkingSpaceView parkingSpaceView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.parking_fragment, container, false);

        initView(rootView);
        return rootView;
    }

    @Override
    public void onClick(View v){

    }

    public void setListview_item_title(final ParkingSpaceBean parkingSpaceBean){

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                button_choice_parkingspace.setText(parkingSpaceBean.getSubname());
            }
         });

    }

    // 初始化控件
    private void initView(View rootView) {

        parking_photo = rootView.findViewById(R.id.parking_photo);
        parking_photo.setOnClickListener(this);

        button_order_add = rootView.findViewById(R.id.button_order_add);
        button_order_add.setOnClickListener(this);

        button_choice_parkingspace = rootView.findViewById(R.id.btn_choice_parkingspace);
        button_choice_parkingspace.setOnClickListener(this);

        parking_carmun = rootView.findViewById(R.id.parking_edittext_carmun);
        parking_pre_price = rootView.findViewById(R.id.parking_pre_price);

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
