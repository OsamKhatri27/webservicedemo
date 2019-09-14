package com.example.innu.webservicesdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        task.execute();
    }


    class DownloadTask extends AsyncTask<Void,Void,String>
    {
        @Override
        protected String doInBackground(Void... params) {
            String result=null;
            try
            {
                URL url = new URL("http://gadgetworld.000webhostapp.com/getcategories.php");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(20000);
                con.setReadTimeout(10000);

                InputStream stream = con.getInputStream();
                //to convert the byte format into character format
                InputStreamReader reader = new InputStreamReader(stream);

                BufferedReader buffer = new BufferedReader(reader);


                result=buffer.readLine();
            }
            catch(Exception e)
            {
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            if(s==null)
            {
                Log.i("Myapp","No Data");
            }
            else
            {
                Log.i("Myapp",s);
            }
            parsejson(s);
        }
        public void parsejson(String s){
            try {
                JSONArray jsonarray3 = new JSONArray(s);
                for(int i=0;i<jsonarray3.length();i++)
                {
                    JSONObject jsonobject1=jsonarray3.getJSONObject(i);

                    String name= jsonobject1.getString("name");

                    Log.i("Myapp",name);
                }
            }catch(Exception e){}
        }
    }
}
