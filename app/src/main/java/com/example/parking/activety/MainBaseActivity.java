package com.example.parking.activety;



import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.parking.R;
import com.example.parking.bean.UserBean;
import com.example.parking.Shared.User_Shared;
import com.example.parking.fragment.MyBase;
import com.example.parking.fragment.MyFragment;
import com.example.parking.fragment.OrderBase;
import com.example.parking.fragment.OrderFragment;
import com.example.parking.fragment.ParkingBase;
import com.example.parking.fragment.ParkingFragment;
import com.example.parking.fragment.ParkingIndexFragment;


public class MainBaseActivity extends BaseActivity {




    private static final String TAG = "BaseActivity<主Base>";

    public static String FragmentStartTAG = "";

    protected ParkingFragment parkingFragment = null;
    protected ParkingIndexFragment parkingIndexFragment =  null;
    protected OrderFragment orderFragment = null;
    protected MyFragment myFragment = null;

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


            case ParkingFragment.TAG: break;
            case ParkingBase.TAG:break;
            default:return;
        }

        super.onBackPressed();//注释掉这行,back键不退出activity
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        myFragment = new MyFragment();
        orderFragment = new OrderFragment();
        parkingIndexFragment =  new ParkingIndexFragment();
        parkingFragment = new ParkingFragment();
        userBean = User_Shared.getALL(getApplicationContext());

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_fragment, parkingIndexFragment);
        fragmentTransaction.commit();

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;//创建的菜单显示出来
    }


}
