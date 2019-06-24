package com.example.parking.fragment;


import android.app.Activity;
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


public class BaseFragment extends Fragment implements View.OnClickListener, HttpCallBack2 ,Position{

    private static final String TAG = "BaseFragment<Base>";
    protected MainActivity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
    }


    @Override
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

    /**
     * TODO 打印机
     * @param type 1是文字，2是二维码
     * @param date 需要打印的内容
     */
    protected void printer_marking( int type, String date ){

        if ( type!=1 && type!=2 ) return;
        if ( !StringUtil.is_valid(date) ) return;

        Intent intentService = new Intent(getActivity(), PrintBillService.class);
        intentService.putExtra("SPRT", date);
        intentService.putExtra("type", type);
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

