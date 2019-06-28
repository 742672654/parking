package com.example.parking.fragment;



import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.parking.R;
import com.example.parking.Static_bean;
import com.example.parking.bean.http.HttpBean;
import com.example.parking.bean.http.OrderDetailsBean;
import com.example.parking.bean.http.OrderlistBean;
import com.example.parking.bean.http.ParkingSpaceBean;
import com.example.parking.bean.http.ParkingSpaceData;
import com.example.parking.bean.http.SelectSubPlaceBean;
import com.example.parking.http.HttpManager2;
import com.example.parking.util.JsonUtil2;
import java.util.HashMap;
import java.util.Map;


public class Order_detailsFragment extends BaseFragment{

    public static final String TAG = "Order_detailsFragment<订单收费>";

    protected Button soufei_Button;
    protected Button taofei_Button;
    protected TextView a1,a22,a3,a33,a4,a5,a6;

    private OrderlistBean.OrderlistData  orderlistData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.order_details, container, false);

        initView(rootView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        super.onPosition(TAG);
        orderlistData = null;
        orderlistData = (OrderlistBean.OrderlistData) getArguments().getSerializable("orderlistData");

        Map<String,String> param = new HashMap<String,String>(4);
        param.put("token",activity.userBean.getToken());
        param.put("id",orderlistData.getId());
        param.put("camum",orderlistData.getCarNo());
        param.put("subname",orderlistData.getSubname());
        HttpManager2.requestPost(Static_bean.getLeavePageOrder,  param, this, "getLeavePageOrder");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()){
            case R.id.soufei:

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("结算订单");
                        builder.setMessage("是否收费出场");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Map<String,String> param = new HashMap<String,String>(6);
                                param.put("token",activity.userBean.getToken());
                                param.put("id",orderlistData.getId());
                                param.put("orderprice",a3.getText().toString());
                                param.put("subid",orderlistData.getSubid());
                                param.put("subname",orderlistData.getSubname());
                                param.put("outimage",activity.orderFragment.outimage);
                                HttpManager2.requestPost(Static_bean.payPointOrderToPoint,  param, Order_detailsFragment.this, "payPointOrderToPoint");
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) { }
                        });
                        builder.show();
                    }
                });
                break;

            case R.id.taofei:
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("逃费处理");
                        builder.setMessage("是否按照逃费处理");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Map<String,String> param = new HashMap<String,String>(6);
                                param.put("token",activity.userBean.getToken());
                                param.put("id",orderlistData.getId());
                                param.put("subid",orderlistData.getSubid());
                                param.put("subname",orderlistData.getSubname());
                                param.put("carnum",orderlistData.getCarNo());
                                HttpManager2.requestPost(Static_bean.escape_add,  param, Order_detailsFragment.this, "escape_add");
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        builder.show();
                    }
                });
                break;

            case R.id.order_details_a6:

                Map<String,String> param = new HashMap<String,String>(2);
                param.put("token",activity.userBean.getToken());
                param.put("id",orderlistData.getId());
                HttpManager2.requestPost(Static_bean.selectOrderSubPlace,  param, this, "selectOrderSubPlace");
                break;
        default:break;
        }
    }

    // 初始化控件
    private void initView(View rootView) {

        soufei_Button = rootView.findViewById(R.id.soufei);
        soufei_Button.setOnClickListener(this);

        taofei_Button = rootView.findViewById(R.id.taofei);
        taofei_Button.setOnClickListener(this);

        a1 = rootView.findViewById(R.id.order_details_a1);
        a22 = rootView.findViewById(R.id.order_details_a22);
        a3 = rootView.findViewById(R.id.order_details_a3);
        a33 = rootView.findViewById(R.id.order_details_a33);
        a4 = rootView.findViewById(R.id.order_details_a4);
        a5 = rootView.findViewById(R.id.order_details_a5);
        a6 = rootView.findViewById(R.id.order_details_a6);

        a6.setOnClickListener(this);
    }

    //TODO >>>接收订单详情
    private void getLeavePageOrder(final Map<String, String> param, final String object) {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {

                    OrderDetailsBean orderDetailsBean = JsonUtil2.fromJson(object, OrderDetailsBean.class);
                    a1.setText(orderDetailsBean.getData().getCarnum()==null?"":orderDetailsBean.getData().getCarnum());
                    a22.setText(String.valueOf(orderDetailsBean.getData().getWait_price()));
                    a3.setText(String.valueOf(orderDetailsBean.getData().getPrice()));
                    a33.setText(String.valueOf(orderDetailsBean.getData().getPre_price()));
                    a4.setText(orderDetailsBean.getData().getTime());
                    a5.setText(orderDetailsBean.getData().getStartTime());
                    a6.setText(param.get("subname"));
                } catch (Exception e) {
                    Log.w(TAG, e);
                }
            }
        });
    }

    //TODO >>>获取订单收费结果
    private void payPointOrderToPoint(final Map<String, String> param, final String object) {

        HttpBean httpBean = JsonUtil2.fromJson(object,HttpBean.class);
        if (httpBean.getCode()==200){

            toast_makeText("订单结算完成");

            StringBuffer buf = new StringBuffer("\r\n\r\n\r\n\r\n---------------------------\r\n");
            buf.append("停车场："+activity.userBean.getParkname()+"   \r\n\r\n");
            buf.append("车位号："+a6.getText().toString()+"\r\n\r\n");
            buf.append("驶入时间"+a5.getText().toString()+"\r\n\r\n");
            buf.append("驶出时间"+httpBean.getData()+"\r\n\r\n");
            buf.append("停车时长："+a4.getText().toString()+"\r\n\r\n");
            buf.append("总停车费："+ a3.getText().toString()+"元\r\n\r\n");
            buf.append("已缴金额："+ a33.getText().toString()+"元\r\n\r\n");
            buf.append("本次收取："+ a22.getText().toString()+"元\r\n\r\n");
            buf.append("收费单位：泉州市畅顺停车管理有限公司\r\n\r\n");
            buf.append("监督电话：0595-28282818\r\n\r\n");

            buf.append(" \r\n \r\n \r\n \r\n \r\n ");

            printer_marking_soufei(buf.toString());

            activity.openOrder();
        }else if (httpBean.getCode()==400){

            toast_makeText(httpBean.getMessage());
        }
    }

    //TODO >>>接收逃费处理
    private void escape_add(final Map<String, String> param, final String object) {

        HttpBean httpBean = JsonUtil2.fromJson(object,HttpBean.class);
        if (httpBean.getCode()==200){

            toast_makeText("订单已提交");
            activity.openOrder();
        }else if (httpBean.getCode()==400){

            toast_makeText(httpBean.getMessage());
        }
    }

    //TODO >>>接收可用车位接口
    private void selectOrderSubPlace(final Map<String, String> param, final String object) {

        final ParkingSpaceBean httpBean = JsonUtil2.fromJson(object,ParkingSpaceBean.class);


        final String[] listString= new String[httpBean.getData().size()];
        for (int i=0;i<httpBean.getData().size();i++){
            listString[i]=httpBean.getData().get(i).getSubname();
        }

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (httpBean.getCode() == 200) {
                    toast_makeText("订单已提交");

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Holo_Light_Dialog);
                    builder.setItems(listString, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int w) {
                            for (int i = 0; i < listString.length; i++) {
                                if (httpBean.getData().get(i).getSubname().equals(listString[w])) {

                                    ParkingSpaceData selectSubPlaceData = httpBean.getData().get(i);
                                    orderlistData.setSubid(selectSubPlaceData.getId());
                                    orderlistData.setSubname(selectSubPlaceData.getSubname());

                                    a6.setText(selectSubPlaceData.getSubname());
                                }
                            }
                        }
                    }).show();

                } else if (httpBean.getCode() == 400) {
                    toast_makeText(httpBean.getMessage());
                }
            }
        });
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onResponsePOST(String url, Map<String, String> param, String sign, String object) {
        super.onResponsePOST(url, param, sign, object);
        Log.i(TAG,"url="+url+";param="+param+";sign="+sign+";object="+object);
        switch (sign){

            case "getLeavePageOrder":getLeavePageOrder(param,object);
                break;

            case "payPointOrderToPoint":payPointOrderToPoint(param,object);
                break;

            case "escape_add":escape_add(param,object);
                break;

            case "selectOrderSubPlace":selectOrderSubPlace(param,object);
                break;
            default:break;
        }

    }
}

