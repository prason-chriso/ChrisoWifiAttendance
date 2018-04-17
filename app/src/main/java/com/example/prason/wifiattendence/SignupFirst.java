package com.example.prason.wifiattendence;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Prason on 6/19/2017.
 */

public class SignupFirst extends AppCompatActivity {

    public static int USER_TYPE;  // 0-> Student user and  1-> Teacher user
    EditText name,pass, stdclass, roll;
    Spinner userType;
    String spinnerVal;
    Button register ;
    ProgressDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginfirst);


    }

    @Override
    protected void onStart() {
        super.onStart();
        //initialization of the componene
        name = (EditText)findViewById(R.id.etname);
        pass = (EditText)findViewById(R.id.etpass);
        stdclass = (EditText)findViewById(R.id.etstdclass);
        roll = (EditText)findViewById(R.id.etstdroll);
        register = (Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameVal = name.getText().toString();
                String passVal = pass.getText().toString();
                String classVal = stdclass.getText().toString();
                String rollVal  =roll.getText().toString();
                DataModule dm  = new DataModule();
                dm.setName(nameVal);
                dm.setPassword(passVal);
                dm.setRoll(Integer.parseInt(rollVal));
                dm.setStdClass(classVal);

                BackgroundTask backgroundTask = new BackgroundTask(SignupFirst.this);
                backgroundTask.execute("register",nameVal,passVal,classVal,rollVal);


                dialog = new ProgressDialog(SignupFirst.this);
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setMessage("Loading. Please wait...");
                dialog.setIndeterminate(true);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        if(dialog.isShowing()){
                            dialog.dismiss();
                        }
                    }
                };

                Timer timer = new Timer();
                timer.schedule(task,4000);
            }
        });


        ///declaring the spinner
        userType = (Spinner)findViewById(R.id.spinner);
        spinnerVal = userType.getSelectedItem().toString();
        userType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerVal= userType.getItemAtPosition(position).toString();
                if(spinnerVal.equals("Student")){
                    stdclass.setEnabled(true);
                    roll.setEnabled(true);
                    Toast.makeText(SignupFirst.this, "student is selected", Toast.LENGTH_SHORT).show();
                    USER_TYPE  = 0;
                }
                else{
                    stdclass.setText("");
                    roll.setText("");
                    stdclass.setEnabled(false);
                    roll.setEnabled(false);
                    Toast.makeText(SignupFirst.this, "teacher is selected", Toast.LENGTH_SHORT).show();
                    USER_TYPE =1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
