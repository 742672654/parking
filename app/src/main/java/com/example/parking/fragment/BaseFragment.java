package com.example.parking.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.parking.Static_bean;
import com.example.parking.activety.MainActivity;
import com.example.parking.http.HttpCallBack2;
import com.example.parking.http.HttpManager2;
import com.example.parking.listView.PrrkingindexView;
import com.example.parking.printer.PrintBillService;
import com.example.parking.util.FileUtil;
import com.example.parking.util.StringUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class BaseFragment extends Fragment implements View.OnClickListener, HttpCallBack2 {

    private static final String TAG = "BaseFragment<Base>";
    protected MainActivity activity;


    protected static String[] string_pre_price = null;


    @Override
    public void onAttach(Activity activity) {

        if (string_pre_price==null){
            string_pre_price = new String[101];
            for (int s=0;s<=100;s++){

                string_pre_price[s]=String.valueOf(s);
            }
        }

        super.onAttach(activity);
        this.activity = (MainActivity) activity;
    }



    public void onPosition(String TAG) {
        MainActivity.FragmentStartTAG = TAG;
    }


    //TODO 判断是否是主线程
    protected boolean isOnMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    //TODO 提示
    protected void toast_makeText(final String text) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(activity,text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //TODO 大提示
    protected void AlertDialog_Builder(final String title, final String text ) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(title);
                builder.setMessage(text);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
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
    }



    /**
     * TODO 打印机 二维码
     * @param date 需要打印的内容
     */
    protected void printer_marking_QRcode( String date ){

        if ( !StringUtil.is_valid(date) ) return;

        Intent intentService = new Intent(getActivity(), PrintBillService.class);
        intentService.putExtra("SPRT", date);
        intentService.putExtra("type", 2);//1是文字，2是二维码
        getActivity().startService(intentService);
    }

    /**
     * TODO 打印机 告知文字
     * @param date 需要打印的内容
     */
    protected void printer_marking_gaozi(String date){

        if ( !StringUtil.is_valid(date) ) return;

        Intent intentService = new Intent(getActivity(), PrintBillService.class);
        intentService.putExtra("SPRT", date);
        intentService.putExtra("type", 1); //1是文字，2是收费
        getActivity().startService(intentService);
    }

    /**
     * TODO 打印机 收费文字
     * @param date 需要打印的内容
     */
    protected void printer_marking_soufei(String date){

        if ( !StringUtil.is_valid(date) ) return;

        Intent intentService = new Intent(getActivity(), PrintBillService.class);
        intentService.putExtra("SPRT", date);
        intentService.putExtra("type", 2); //1是文字，2是收费
        getActivity().startService(intentService);
    }



    @Override
    public void onClick(View v) { }

    @Override
    public void onResponseGET(String url, Map<String, String> param, String sign, String object) { }

    @Override
    public void onResponsePOST(String url, Map<String, String> param, String sign, String object) { }

    @Override
    public void onResponseFile(String url, Map<String, String> param, String sign, String object) { }

}

