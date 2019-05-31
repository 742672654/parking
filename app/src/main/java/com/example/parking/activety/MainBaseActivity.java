package com.example.parking.activety;



import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        myFragment = new MyFragment();
        orderFragment = new OrderFragment();
        parkingFragment =  new ParkingFragment();
        userBean = User_Shared.getALL(getApplicationContext());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;//创建的菜单显示出来
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_1:
                    setTitle ("车辆入场");
                    fragmentTransaction.replace(R.id.activity_fragment, parkingFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_2:
                    setTitle ("车辆列表");
                    fragmentTransaction.replace(R.id.activity_fragment, orderFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_3:
                    setTitle ("个人中心");
                    fragmentTransaction.replace(R.id.activity_fragment,myFragment);
                    fragmentTransaction.commit();
                    return true;
            }
            return false;
        }
    };

   //TODO 提示
   protected void toast_makeText(final String text) {


    activity.runOnUiThread(new Runnable() {
        @Override
        public void run() {

            Toast.makeText(activity,text, Toast.LENGTH_SHORT).show();
        }
    });
}

}
