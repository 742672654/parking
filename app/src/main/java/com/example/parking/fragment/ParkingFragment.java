package com.example.parking.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.parking.R;
import com.example.parking.Static_bean;
import com.example.parking.activety.MainActivity;
import com.example.parking.bean.http.FirstPageRecordBean;
import com.example.parking.bean.http.HttpBean;
import com.example.parking.bean.http.OrderAddBean;
import com.example.parking.bean.http.PhotoToOssBean;
import com.example.parking.bean.http.SelectSubPlaceBean;
import com.example.parking.http.HttpManager2;
import com.example.parking.util.JsonUtil2;
import com.example.parking.util.TimeUtil;
import com.google.gson.Gson;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;


/**
 * 功能说明: 停车Fragment
 * 日期:	2019年5月29日
 * 开发者:	KXD
 */
public class ParkingFragment extends ParkingBase {



    public static final String TAG = "ParkingFragment";
    public String inimage = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView( inflater, container, savedInstanceState );
    }

    @Override
    public void onStart() {
        super.onStart();
        inimage = "";

        SelectSubPlaceBean.SelectSubPlaceData selectSubPlaceDate = (SelectSubPlaceBean.SelectSubPlaceData) getArguments().getSerializable("selectSubPlaceDate");

        if (selectSubPlaceDate!=null){
            parking_carmun.setText(selectSubPlaceDate.getCarnum()==null?"":selectSubPlaceDate.getCarnum());
            parking_pre_price.setText(selectSubPlaceDate.getPreprice()==null?"":selectSubPlaceDate.getPreprice().toString());
        }


        // 剩余车位和预约车位
        Map<String,String> param = new HashMap<String,String>(1);
        param.put("token",activity.userBean.getToken());
        HttpManager2.requestPost(Static_bean.firstPageRecord,  param, this, "firstPageRecord");
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
                Handler handler2 = activity.handler;
                handler2.sendMessage(message2);
                break;

            case R.id.button_order_add:
                Map<String,String> params = new HashMap<String,String>() ;
                params.put("token", activity.userBean.getToken());
                params.put("carnum", parking_carmun.getText().toString());
                params.put("preprice", parking_pre_price.getText().toString());
                params.put("ordertype", String.valueOf(1));
                params.put("inimage", inimage);
                params.put("subid", selectSubPlaceData.getId());
                params.put("subname", selectSubPlaceData.getCode());
                HttpManager2.requestPost(Static_bean.orderAdd, params, this,"orderAdd");
                break;
            default:
                break;
        }
    }

    //TODO 修改车牌号
    public void parking_carmun(final String obj) {


        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {

                    final PhotoToOssBean photoToOssBean = new Gson().fromJson(URLDecoder.decode(obj, "UTF-8"), PhotoToOssBean.class);
                    Log.i(TAG, obj+"----"+URLDecoder.decode(obj, "UTF-8") + "---------" + photoToOssBean.toString());

                    if (photoToOssBean.getCarmun()==null || "".equals(photoToOssBean.getCarmun())){

                        toast_makeText("车牌识别错误，请手动输入车牌");
                        parking_carmun.setText("车牌识别错误");
                        return;
                    }

                    inimage = photoToOssBean.getImgurl();
                    parking_carmun.setText(photoToOssBean.getCarmun());

                } catch (Exception e) {
                    Log.w(TAG, e);
                    toast_makeText("车牌识别错误，请手动输入车牌");
                }
            }
        });

    }

    //TODO >>>>>上传订单
    public void orderAdd(String obj) {

        try {
            final HttpBean httpBean = JsonUtil2.fromJson(URLDecoder.decode(obj, "UTF-8"), HttpBean.class);

            Log.i(TAG,"117"+httpBean.toString());

            if (httpBean.getCode()==200){
                Log.i(TAG,"打开ParkingFragment");
                toast_makeText("订单上传成功");


                StringBuffer buf = new StringBuffer("\r\n\r\n\r\n\r\n---------------------------\r\n");
                buf.append("停车场："+activity.userBean.getParkname()+"   \r\n\r\n");
                buf.append("车位号："+button_choice_parkingspace.getText().toString()+" \r\n\r\n");
                buf.append("车牌号："+parking_carmun.getText().toString()+" \r\n\r\n");
                buf.append("驶入时间"+ ((httpBean.getData()==null)?TimeUtil.getDateTime():httpBean.getData())+" \r\n");
                buf.append("预交金额："+parking_pre_price.getText().toString()+".0元\r\n\r\n");

                buf.append("每天单次收费5元，晚上12点后重新收费。车辆离开车位后，视为停车订单结算完成。\r\n\r\n");
                buf.append("收费单位：泉州市畅顺停车管理有限公司\r\n\r\n");
                buf.append("监督电话：0595-28282818\r\n\r\n");

                buf.append(" \r\n \r\n \r\n \r\n \r\n ");
                printer_marking_gaozi(buf.toString());
                activity.openParkingIndex();

            }else if (httpBean.getCode()==400){

               if ( httpBean.getData()==null){

                   AlertDialog_Builder("上传失败",httpBean.getMessage());
               }else if ( httpBean.getData().equals("order")){

                   AlertDialog_Builder("提示","已有订单");
               }
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    //TODO >>>>>剩余车位/预约车位
    public void firstPageRecord(final String obj) {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                try {

                    final FirstPageRecordBean firstPageRecordBean = JsonUtil2.fromJson(URLDecoder.decode(obj, "UTF-8"), FirstPageRecordBean.class);

                    Log.i(TAG, "剩余车位/预约车位=" + firstPageRecordBean.toString());

                    nouse.setText( String.valueOf(firstPageRecordBean.getData().getNouse()) );
                    wxnum.setText( String.valueOf(firstPageRecordBean.getData().getWxnum()) );
                } catch (Exception e) {
                    Log.w(TAG, e);
                }
            }
        });

    }

    @Override
    public void onResponsePOST(String url, Map<String, String> param, String sign, String object) {
        super.onResponsePOST(url,  param,  sign,  object);
        Log.i(TAG,"url="+url+";param="+param+";sign="+sign+";\r\n object="+object);

        switch (sign){
            case "orderAdd": orderAdd(object);
            break;
            case "firstPageRecord": firstPageRecord(object);
                break;

            default:break;
        }

    }

}