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
            int type; //1是"告知",2是"收费"

            if ( !StringUtil.is_valid(SPRT=intent.getStringExtra("SPRT")) ) return;

            if ( (type=intent.getIntExtra("type",0)) == 0 ) return;



                //打印文字
                printer.drawTextEx("\r\n     "+(type==1?"告知":"收费"), 20, 0, 380, -1, "simsun", 50, 0, 0, 0);

                printer.drawTextEx(SPRT, 0, 0, 385, -1, "simsun", intent.getIntExtra("fontsize",28), 0, 0, 0);


                //打印二维码
//                case 2:
//                    printer.prn_drawBarcode(SPRT, 10, 10, 58, 10, 10, 0);
//                    break;


            sendBroadcast( new Intent("android.prnt.message").putExtra("ret", printer.printPage(0)));
        } catch (Exception e) {

            Log.w(TAG,e);
        }
    }
}