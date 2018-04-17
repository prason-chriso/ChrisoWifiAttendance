package com.example.prason.wifiattendence;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.RunnableFuture;

public class MainActivity extends AppCompatActivity  {

    public static  Button makeAttendance;
    Button show_list;
    public static ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

  }

    @Override
    protected void onStart(){
        super.onStart();
        ShowAllAttendance.mydata.clear();
        BackgroundTask bkt = new BackgroundTask(MainActivity.this);
        bkt.execute("presentDays", "" + LauncherClassFile.CURRENT_USER_ID);



            dialog = new ProgressDialog(MainActivity.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("Loading. Please wait...");
            dialog.setIndeterminate(true);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            TimerTask  task = new TimerTask() {
                @Override
                public void run() {
                    if(dialog.isShowing()){
                        dialog.dismiss();
                    }
                }
            };

        Timer timer = new Timer();
        timer.schedule(task,4000);

        makeAttendance = (Button) findViewById(R.id.makeAttendance);
/*
        if (ShowAllAttendance.mydata.size() > 0) {
            if (ShowAllAttendance.mydata.get(0).equals("new_user") || ShowAllAttendance.mydata.get(0).equals("need_attendance")) {
                //do nothing
            } else if (ShowAllAttendance.mydata.get(0).contains("remaining for next attendance")) {
                MainActivity.makeAttendance.setEnabled(false);
                Toast.makeText(MainActivity.this, ShowAllAttendance.mydata.get(0), Toast.LENGTH_SHORT).show();
            }
        }
*/

        show_list = (Button) findViewById(R.id.show_list);
        show_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShowAllAttendance.class));
            }
        });

        makeAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeAttendance.setEnabled(false);
                registerEvent();
            }
        });



    }

public void registerEvent(){
        registerReceiver(new MyBroadcastReceiver(),  new IntentFilter(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION));
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute("make_attendace",""+LauncherClassFile.CURRENT_USER_ID);

}
}

