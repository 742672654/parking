package com.example.parking.printer;

import android.app.IntentService;
import android.content.Intent;
import android.device.PrinterManager;
import android.util.Log;

import com.example.parking.util.StringUtil;

public class PrintBillService extends IntentService {


    private static final String TAG = "PrintBillService<打印机服务>";

    private PrinterManager printer;
    
    public PrintBillService() {
        super("bill");
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        printer = new PrinterManager();
        //初始化
        printer.setupPage(384, -1);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try {

            String SPRT; //需要打印的内容
            String type; //1是文字，2是二维码，

            if ( !StringUtil.is_valid(SPRT=intent.getStringExtra("SPRT")) ) return;

            if ( !StringUtil.is_valid(type=intent.getStringExtra("type")) ) return;

            switch (type){

                //打印文字
                case "1":
                    printer.drawTextEx(SPRT, 5, 0, 384, -1, "simsun", 24, 0, 0, 0);
                    break;

                //打印二维码
                case "2":
                    printer.prn_drawBarcode(SPRT, 10, 10, 58, 10, 10, 0);
                    break;

                default:return;
            }

            sendBroadcast( new Intent("android.prnt.message").putExtra("ret", printer.printPage(0)));
        } catch (Exception e) {

            Log.w(TAG,e);
        }
    }
}