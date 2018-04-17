package com.example.prason.wifiattendence;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Prason on 6/20/2017.
 */

public class LauncherClassFile extends AppCompatActivity {
   EditText userName, password;
   public static Button login, register;
    public static int CURRENT_USER_ID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcherlayout);


    }

    @Override
    protected void onStart() {
        super.onStart();


    userName = (EditText)findViewById(R.id.etuserName);
        password = (EditText)findViewById(R.id.etpassword);

        login = (Button)findViewById(R.id.login);
        login.setEnabled(true);


        register = (Button)findViewById(R.id.register);
        register.setEnabled(true);
        //enabling the  log in and registration only when the wifinetwork is connected
     /*   if(!isConnectedViaWifi()){
            login.setEnabled(false);
            login.setEnabled(false);
        }
*/


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LauncherClassFile.this,SignupFirst.class));
            }
        });

        //log in check when the log in button is clicked;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                String pass = password.getText().toString();
                if(name.equals("")&& pass.equals("")){
                    //do nothing at all 
                    Toast.makeText(LauncherClassFile.this, "Provide username and password first", Toast.LENGTH_SHORT).show();
                }
                else{
                    login.setEnabled(false);
                    register.setEnabled(false);
                    BackgroundTask backgroundTask = new BackgroundTask(LauncherClassFile.this);
                    backgroundTask.execute("login",name,pass);

                }

            }
        });

    }

    private boolean isConnectedViaWifi() {
        ConnectivityManager connectivityManager = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
       // return (activeNetwork != null && activeNetwork.isConnectedOrConnecting());
          if(activeNetwork != null && activeNetwork.isConnectedOrConnecting()){
              return activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
          }
        return  false;
    }
}
