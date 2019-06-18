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
import com.example.parking.fragment.MyFragment;
import com.example.parking.fragment.OrderFragment;
import com.example.parking.fragment.ParkingFragment;


public class MainBaseActivity extends BaseActivity {



    private static final String TAG = "BaseActivity";

    protected static MainActivity activity;

    ParkingFragment parkingFragment =  null;
    OrderFragment orderFragment = null;
    MyFragment myFragment = null;

    public UserBean userBean;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;//创建的菜单显示出来
    }



   //TODO 提示
   protected void toast_makeText(final String text) {

       Log.i(TAG,text);
    activity.runOnUiThread(new Runnable() {
        @Override
        public void run() {

            Toast.makeText(activity,text, Toast.LENGTH_SHORT).show();
        }
    });
}

}
