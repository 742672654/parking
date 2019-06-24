package com.example.parking.listView;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parking.R;
import com.example.parking.fragment.OrderBase;

import java.util.List;
import java.util.Map;


public class OrderView extends BaseAdapter {



    List<Map<String,Object>> list;  //数据源与配置器建立连接
    LayoutInflater layoutInflater;//初始化布局填充器
    Context context;
    OrderBase orderBase;


    public OrderView(Context context, OrderBase orderBase, List<Map<String, Object>> list) {
        this.layoutInflater = layoutInflater.from(context);
        this.context=context;
        this.list = list;
        this.orderBase=orderBase;
    }

    //itme的数量
    @Override
    public int getCount() {
        return list.size();
    }

    //返回第几条itme信息
    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    //返回itme的ID
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        //第三个参数false：不会为item添加父布局
        View view1 = layoutInflater.inflate(R.layout.listview_order_item,viewGroup,false);

        TextView item_title1 = view1.findViewById(R.id.item_title1);
        TextView item_title2 = view1.findViewById(R.id.item_title2);
        TextView item_title3 = view1.findViewById(R.id.item_title3);

        Map<String,Object> map = list.get(i);

        item_title1.setText((String)map.get("item_title1"));
        item_title2.setText((String)map.get("item_title2"));
        item_title3.setText((String)map.get("item_title3"));


         Button btn = (Button) view1.findViewById(R.id.item_but1);


        btn.setTag(map.get("item_title1"));//设置标签

        btn.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View v) {

                        Toast.makeText(context, "" + v.getTag(), Toast.LENGTH_SHORT).show();
            }
        });


        return view1;
    }
}
