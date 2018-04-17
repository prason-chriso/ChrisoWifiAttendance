package com.example.prason.wifiattendence;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Prason on 6/20/2017.
 */

public class BackgroundTask extends AsyncTask<String,Void,String> {  //params, progress, result
    Context context;

    public BackgroundTask(Context context){
        this.context  =context;
}

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        //now the operation has to be done  here
      /*  String register_url = "http://192.168.1.9/andriodpage/register.php";
        String login_url = "http://192.168.1.9/andriodpage/login.php";
        String attendance_url="http://192.168.1.9/andriodpage/attendanceConfirm.php";
        String presendDays_url = "http://192.168.1.9/andriodpage/presentDays.php";*/

        String attendance_url=   "http://solid-commas.000webhostapp.com/andriodpage/attendanceConfirm.php";
        String presendDays_url = "http://solid-commas.000webhostapp.com/andriodpage/presentDays.php";
        String register_url =    "http://solid-commas.000webhostapp.com/andriodpage/register.php";
        String login_url =       "http://solid-commas.000webhostapp.com/andriodpage/login.php";
        //                       http://solid-commas.000webhostapp.com/andriodpage/


        String method = params[0];
        if(method.equals("register")){
            //do the task for the registration
            String nameVal  = params[1];
            String passVal  = params[2];
            String classVal  = params[3];
            String rolVal  = params[4];


            //now setting uup the url
            try {
                URL url  = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                //setting up the request method
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                //now setting  up the outpurstream annd prepareing the buffered writer for creating the strem to write the data to ht sql
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                //now we have to encode th eddata in to the format before writing it into the database throught the buffered writer
                String data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(nameVal,"UTF-8")+"&"+
                        URLEncoder.encode("user_pass","UTF-8")+"="+URLEncoder.encode(passVal,"UTF-8")+"&"+
                        URLEncoder.encode("user_class","UTF-8")+"="+URLEncoder.encode(classVal,"UTF-8")+"&"+
                        URLEncoder.encode("user_roll","UTF-8")+"="+URLEncoder.encode(rolVal,"UTF-8")+"&"+
                        URLEncoder.encode("user_type","UTF-8")+"="+URLEncoder.encode(""+ SignupFirst.USER_TYPE,"UTF-8");

                //now we need to write the data
                bufferedWriter.write(data);
                bufferedWriter.flush();;
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String message ="";
                String line = "";
                while((line = bufferedReader.readLine())!=null){
                    message +=line;
                }

                bufferedReader.close();
                inputStream.close();

                //return the success message
                return  message;//"successfully inserted the user record....." ;



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(method.equals("login")){
            //do the task for the log in
            String  login_name = params[1];
            String  login_pass = params[2];

            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true); //for sending data
                httpURLConnection.setDoInput(true); //for getting response
                OutputStream outputStream= httpURLConnection.getOutputStream();
                BufferedWriter  bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                String data = URLEncoder.encode("login_name","UTF-8")+"="+URLEncoder.encode(login_name,"UTF-8")+"&"+
                        URLEncoder.encode("login_pass","UTF-8")+"="+URLEncoder.encode(login_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                //NOW CHECKING THE VALUE RETURNED
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String message ="";
                String line = "";
                while((line = bufferedReader.readLine())!=null){
                    message +=line;
                }

                bufferedReader.close();
                inputStream.close();

                return  message;  // "log in success ".$row['name'].",>>".$row['id'];

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(method.equals("presentDays")){
            String id  = params[1];
            try{
            URL url = new URL(presendDays_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true); //for sending data
            httpURLConnection.setDoInput(true); //for getting response
            OutputStream outputStream= httpURLConnection.getOutputStream();
            BufferedWriter  bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            String data = URLEncoder.encode("user_id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            //NOW CHECKING THE VALUE RETURNED
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            while((line = bufferedReader.readLine())!=null){
                ShowAllAttendance.mydata.add(new String(line));
            }

            bufferedReader.close();
            inputStream.close();


                return  "All present days are shown in this list"+ShowAllAttendance.mydata.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
        else  if(method.equals("make_attendace")){
            //getting the url first
            String  id = params[1];
            try {
                URL  url = new URL(attendance_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                //preparing the data
                String data = URLEncoder.encode("user_id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                String msg= "";
                String line = "";
                InputStream is  = httpURLConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                while((line = br.readLine())!=null){
                    msg+=line;
                }
                br.close();
                is.close();




                if(msg.equals("")) {
                    return "your attendance has been made ";
                }
                else{
                    return msg;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        //return the success message
        return  "error....."+method ;
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


    @Override
    protected void onPostExecute(String result) {
        if(result.equals("successfully inserted the user record.....")) {
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context,LauncherClassFile.class));
        }
        else if (result.equals("your attendance has been made ") || result.contains("remaining for next attendance")){
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            MainActivity.makeAttendance.setEnabled(false);
        }
        else{

            if(result.contains("log in success")) {
                Toast.makeText(context, "Log in Successful " + result, Toast.LENGTH_SHORT).show();
                //below two statement is all for storing the id of the current user logged into the system; by extracting the integer value appended in teh last part
                int index = result.lastIndexOf(",>>");
                LauncherClassFile.CURRENT_USER_ID = Integer.parseInt(result.substring(index+3));
                context.startActivity(new Intent(context, MainActivity.class));
            }
            else{
                LauncherClassFile.login.setEnabled(true);
                LauncherClassFile.register.setEnabled(true);
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            }
        }


    }


}
