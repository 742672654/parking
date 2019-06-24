package com.example.parking.listView;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.parking.R;
import com.example.parking.fragment.ParkingIndexFragment;

import java.util.List;


public class PrrkingindexView extends BaseAdapter {


    public static String id = "";

    private List<String> list;  //数据源与配置器建立连接
    private Context context;
    private ParkingIndexFragment fragment;

    public PrrkingindexView(Context context, ParkingIndexFragment fragment, List<String> list) {
        this.fragment = fragment;
        this.context = context;
        this.list = list;
    }

    //itme的数量
    @Override
    public int getCount() { return list.size(); }

    //返回第几条itme信息
    @Override
    public Object getItem(int i) { return list.get(i); }

    //返回itme的ID
    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        //第三个参数false：不会为item添加父布局
        View view1 = LayoutInflater.from(context).inflate(R.layout.listview_prrkingindex_item, viewGroup,false);

        setleft(  view1 );
        setright( view1 );

        return view1;
    }

    int s = 1;
    private void setleft( View view1 ){

        final RelativeLayout relativeLayout = view1.findViewById(R.id.listview_prrkingindex_item_RelativeLayout_Left);

        final  TextView id_number_left = view1.findViewById(R.id.id_number_left);
        TextView car_number_left = view1.findViewById(R.id.car_number_left);
        TextView start_time_left = view1.findViewById(R.id.start_time_left);
        TextView earnest_money_left = view1.findViewById(R.id.earnest_money_left);
        TextView arrears_money_left = view1.findViewById(R.id.arrears_money_left);



        relativeLayout.setBackgroundResource(R.drawable.listview_index_yes);
        relativeLayout.setPadding(relativeLayout.getPaddingLeft(),
                    relativeLayout.getPaddingTop(),
                    relativeLayout.getPaddingRight(),
                    relativeLayout.getPaddingBottom());

        id_number_left.setText(String.valueOf(s++));
        relativeLayout.setTag("");
        relativeLayout.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = v.getTag().toString();
                fragment.openParking();
            }
        });


//        if (1==1){
//
//            car_number_left.setText("");
//            start_time_left.setText("");
//            earnest_money_left.setText("");
//            arrears_money_left.setText("");
//            relativeLayout.setBackgroundResource(R.drawable.listview_index_yes);
//            relativeLayout.setPadding(relativeLayout.getPaddingLeft(),
//                    relativeLayout.getPaddingTop(),
//                    relativeLayout.getPaddingRight(),
//                    relativeLayout.getPaddingBottom());
//
//            relativeLayout.setTag("");//设置标签
//            relativeLayout.setOnClickListener(new android.view.View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Toast.makeText(context, "" + v.getTag(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }else{
//
//            relativeLayout.setBackgroundResource(R.drawable.listview_index_no);
//            relativeLayout.setPadding(relativeLayout.getPaddingLeft(),
//                    relativeLayout.getPaddingTop(),
//                    relativeLayout.getPaddingRight(),
//                    relativeLayout.getPaddingBottom());
//
//            view1.findViewById(R.id.car_fang_left).setVisibility( View.INVISIBLE );
//            car_number_left.setVisibility( View.INVISIBLE );
//            start_time_left.setVisibility( View.INVISIBLE );
//            earnest_money_left.setVisibility( View.INVISIBLE );
//            arrears_money_left.setVisibility( View.INVISIBLE );
//        }

    }


    private void setright( View view1 ){


        RelativeLayout relativeLayout = view1.findViewById(R.id.listview_prrkingindex_item_RelativeLayout_Right);

        if (1==0){ relativeLayout.setVisibility( View.INVISIBLE );return; }

        final TextView id_number_right = view1.findViewById(R.id.id_number_right);
        TextView car_number_right = view1.findViewById(R.id.car_number_right);
        TextView start_time_right = view1.findViewById(R.id.start_time_right);
        TextView earnest_money_right = view1.findViewById(R.id.earnest_money_right);
        TextView arrears_money_right = view1.findViewById(R.id.arrears_money_right);



        if (1==0){

            id_number_right.setText(String.valueOf(s++));
            start_time_right.setText("");
            earnest_money_right.setText("");
            arrears_money_right.setText("");
            relativeLayout.setBackgroundResource(R.drawable.listview_index_yes);
            relativeLayout.setPadding(relativeLayout.getPaddingLeft(),
                    relativeLayout.getPaddingTop(),
                    relativeLayout.getPaddingRight(),
                    relativeLayout.getPaddingBottom());

            relativeLayout.setTag("");//设置标签
            relativeLayout.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(context, "" + v.getTag(), Toast.LENGTH_SHORT).show();
                }
            });
        }else{

            relativeLayout.setBackgroundResource(R.drawable.listview_index_no);
            relativeLayout.setPadding(relativeLayout.getPaddingLeft(),
                    relativeLayout.getPaddingTop(),
                    relativeLayout.getPaddingRight(),
                    relativeLayout.getPaddingBottom());

            view1.findViewById(R.id.car_fang_right).setVisibility( View.INVISIBLE );
            car_number_right.setVisibility( View.INVISIBLE );
            start_time_right.setVisibility( View.INVISIBLE );
            earnest_money_right.setVisibility( View.INVISIBLE );
            arrears_money_right.setVisibility( View.INVISIBLE );
        }

    }




}
