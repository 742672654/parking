package com.example.parking.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.parking.R;
import com.example.parking.activety.MainActivity;
import com.example.parking.http.HttpCallBack2;
import com.example.parking.listView.OrderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 功能说明: 订单Fragment
 * 日期:	2019年5月29日
 * 开发者:	KXD
 */
public class OrderBase extends BaseFragment implements OnClickListener, HttpCallBack2 {


    public static final String TAG = "OrderBase";

    private ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.order_fragment, container, false);


        listView = rootView.findViewById(R.id.orderlistview);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        super.onPosition(TAG);
    }

    @Override
    public void onClick(View v){}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




// TODO  自定义配置器
        //第一步：准备数据源
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("item_title1","360卫士");
        map.put("item_title2","80.5分");
        map.put("item_title3","80亿人的选择");
        list.add(map);

        Map<String,Object> map2 = new HashMap<String,Object>();
        map2.put("item_title1","腾讯手机卫士");
        map2.put("item_title2","98.5分");
        map2.put("item_title3","70万人不选择");
        list.add(map2);

        //第二步：创建适配器,存入数据
        OrderView myAdapter = new OrderView(activity,this,list);

        //第三步：绑定适配器
        listView.setAdapter(myAdapter);
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
