package com.example.parking.activety;



import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.parking.R;
import com.example.parking.fragment.MyFragment;
import com.example.parking.fragment.OrderFragment;
import com.example.parking.fragment.ParkingFragment;
import com.example.parking.http.HttpCallBack2;
import com.example.parking.util.FileUtil;
import com.example.parking.util.StringUtil;

import java.io.File;
import java.util.Map;



public class BaseActivity extends AppCompatActivity implements HttpCallBack2 {



    private static final String TAG = "BaseActivity";



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
