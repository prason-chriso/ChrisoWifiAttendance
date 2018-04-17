package com.example.prason.wifiattendence;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.Toast;

/**
 * Created by Prason on 6/17/2017.
 */

class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //WHEN THE INTENT IS REVEIVED THEN CHECK TEH ACTION FIRST
        String action = intent.getAction();
        if(action == WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION){
            //it means the wifi is reaadyy to connected
            if(intent.getBooleanExtra(WifiManager.EXTRA_SUPPLICANT_CONNECTED,false)){
                Toast.makeText(context, "wifi is connected ", Toast.LENGTH_SHORT).show();

                //now the task for the Attendance should be done


            }
            else{
                Toast.makeText(context, "Wifi is now available for the  attendance" , Toast.LENGTH_SHORT).show();
            }
        }
    }
}
