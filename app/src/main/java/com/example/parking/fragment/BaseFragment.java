package com.example.parking.fragment;

import android.app.Activity;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.parking.activety.MainActivity;


public class BaseFragment extends Fragment {

    protected MainActivity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
    }

    //TODO 判断是否是主线程
    public boolean isOnMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    //TODO 提示
    public void toast_makeText(final String text) {


        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(activity,text, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
