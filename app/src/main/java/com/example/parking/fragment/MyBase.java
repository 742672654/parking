package com.example.parking.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.view.View;
import android.webkit.WebViewClient;

import com.example.parking.R;
import com.example.parking.activety.MainActivity;
import com.example.parking.http.HttpCallBack2;
import com.example.parking.listView.PrrkingindexView;

import java.util.Map;


/**
 * 功能说明: 订单Fragment
 * 日期:	2019年5月29日
 * 开发者:	KXD
 */
public class MyBase extends BaseFragment implements OnClickListener, HttpCallBack2 {


    public static final String TAG = "MyBase<我的信息>";


    //声明引用
    private WebView mWVmhtml;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_fragment, container, false);

        initView( rootView );
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        onPosition(TAG);
    }

    @Override
    public void onClick(View v){}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    // 初始化控件
    private void initView(View rootView) {

        mWVmhtml = rootView.findViewById(R.id.WV_Id);

        mWVmhtml.getSettings().setJavaScriptEnabled(true);
        //访问百度首页
        mWVmhtml.loadUrl("https://fanyi.baidu.com/#zh/en/条形码2222");
        //设置在当前WebView继续加载网页
        mWVmhtml.setWebViewClient(new WebViewClient(){
            @Override  //WebView代表是当前的WebView
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //表示在当前的WebView继续打开网页
                view.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.d("WebView","开始访问网页");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d("WebView","访问网页结束");
            }
        });

        mWVmhtml.setWebChromeClient(new WebChromeClient(){

            @Override //监听加载进度
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
            @Override//接受网页标题
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

            }
        });
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
