package com.example.parking.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.parking.R;
import com.example.parking.activety.MainActivity;
import com.example.parking.bean.PhotoToOssBean;
import com.example.parking.listView.OrderView;
import com.example.parking.listView.ParkingSpaceView;
import com.example.parking.listView.PrrkingindexView;
import com.example.parking.printer.PrintBillService;
import com.example.parking.printer.PrinterManagerActivity;
import com.google.gson.Gson;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 功能说明: 停车Fragment
 * 日期:	2019年5月29日
 * 开发者:	KXD
 */
public class ParkingIndexFragment extends BaseFragment {



    public static final String TAG = "ParkingIndexFragment<九宫格>";

    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.parkingindex_fragment, container, false);

        listView = rootView.findViewById(R.id.prrkingindex_listView);

        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
        super.onPosition(TAG);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        // TODO  自定义配置器
        //第一步：准备数据源
        List<String> list = new ArrayList<String>();

        list.add("");
        list.add("");
        list.add("");
        list.add("");

        //第二步：创建适配器,存入数据
        PrrkingindexView myAdapter = new PrrkingindexView( getActivity(),this, list );

        //第三步：绑定适配器
        listView.setAdapter(myAdapter);
    }

    //TODO 打开停车保存页面
    public void openParking() {

        activity.openParking();
    }
}