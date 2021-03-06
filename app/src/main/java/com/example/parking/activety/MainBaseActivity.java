package com.example.parking.activety;



import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parking.R;
import com.example.parking.Static_bean;
import com.example.parking.bean.UserBean;
import com.example.parking.Shared.User_Shared;
import com.example.parking.fragment.MyBase;
import com.example.parking.fragment.MyFragment;
import com.example.parking.fragment.OrderBase;
import com.example.parking.fragment.OrderFragment;
import com.example.parking.fragment.Order_detailsFragment;
import com.example.parking.fragment.ParkingBase;
import com.example.parking.fragment.ParkingFragment;
import com.example.parking.fragment.ParkingIndexFragment;
import com.example.parking.http.HttpManager2;
import com.example.parking.util.ImageUitls;

import java.util.HashMap;
import java.util.Map;


public class MainBaseActivity extends BaseActivity {




    private static final String TAG = "BaseActivity<主Base>";

    protected ImageView user_photo = null;

    protected TextView user_name= null, user_parkingName= null, parking_surplus= null, parking_already= null;


    public static String FragmentStartTAG = "";

    public ParkingFragment parkingFragment = null;
    public ParkingIndexFragment parkingIndexFragment =  null;
    public OrderFragment orderFragment = null;
    public MyFragment myFragment = null;
    public Order_detailsFragment order_detailsFragment = null;
    public UserBean userBean;




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_1:

                    fragmentTransaction.replace(R.id.activity_fragment, parkingIndexFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_2:

                    fragmentTransaction.replace(R.id.activity_fragment, orderFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_3:

                    fragmentTransaction.replace(R.id.activity_fragment,myFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    return true;
            }
            return false;
        }
    };



    @Override
    public void onBackPressed() {

        // 返回键监听
        switch (FragmentStartTAG){


            case ParkingFragment.TAG: if (parkingFragment.isShow()){ return; }   break;
            case ParkingBase.TAG:  if (parkingFragment.isShow()){ return; }   break;
            case Order_detailsFragment.TAG: break;

            default:return;
        }

        super.onBackPressed();//注释掉这行,back键不退出activity
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        user_parkingName = findViewById(R.id.user_parkingName);
        parking_surplus = findViewById(R.id.parking_surplus);
        parking_already = findViewById(R.id.parking_already);

        user_photo = findViewById(R.id.user_photo);
        user_name = findViewById(R.id.user_name);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        myFragment = new MyFragment();
        orderFragment = new OrderFragment();
        parkingIndexFragment =  new ParkingIndexFragment();
        parkingFragment = new ParkingFragment();
        order_detailsFragment = new Order_detailsFragment();

        userBean = User_Shared.getALL(getApplicationContext());


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_fragment, parkingIndexFragment);
        fragmentTransaction.commit();

        if (user_name!=null && userBean!=null && userBean.getNickName()!=null){
            user_name.setText(userBean.getNickName()+"");
        }


        if (user_parkingName!=null && userBean!=null && userBean.getParkname()!=null){
            user_parkingName.setText(userBean.getParkname()+"");
        }



        ImageUitls.setImageHttpURL(this,user_photo,userBean.getAvatarUrl());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;//创建的菜单显示出来
    }


}
