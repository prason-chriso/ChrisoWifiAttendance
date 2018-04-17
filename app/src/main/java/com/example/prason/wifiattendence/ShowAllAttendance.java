package com.example.prason.wifiattendence;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Prason on 6/21/2017.
 */

public class ShowAllAttendance extends AppCompatActivity {

    ListView myList;
    public static List<String> mydata = new ArrayList<>(); //this variable is declared to hold the list for all the presentt day for the  user as per the database record;
    //NOTE : THAT THE LIST WILL BE INCLUDING THE INCICATION MESSAGE  IN THE first (0) INDEX FOR THE TIMESTAMP AVAILABLE OR NOT WHICH IS USED TO CONTROL THE BUTTON ENABLING OR DISABLING LOGIG
    // NOTE : SO BEFORE DISPLAYING THHE DATA IN THE LISTVIEW THE ZERO INDEX VALUE SHOULD BE PREVENTED FROM BEING DISPLAY

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendancelist);

    }

    @Override
    protected void onStart() {
        super.onStart();
        myList = (ListView)findViewById(R.id.myList);
        if(mydata.size()>0){
           mydata.remove(0);
            String [] datas = new String[mydata.size()];
            myList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mydata.toArray(datas)));
            Toast.makeText(this, "data show here length is "+datas.length+" "+mydata.size(), Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "no any data to show here", Toast.LENGTH_SHORT).show();   
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mydata.clear();
        Toast.makeText(this, "list item is cleared", Toast.LENGTH_SHORT).show();
    }
}
