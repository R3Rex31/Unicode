package com.example.unicodetasks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class Task4 extends AppCompatActivity
{
    String main,dets,temp,min,max;
    public class DownloadTask extends AsyncTask<String, Void, String >
    {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data>0) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task4);

        final EditText city = findViewById(R.id.city);
        Button display = findViewById(R.id.display);

        display.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DownloadTask task = new DownloadTask();
                String res = null;
                try
                {
                    res = task.execute("https://api.openweathermap.org/data/2.5/weather?q=london&appid=c5f86e4054707fe2340a2a466f27212f").get();

                    JSONObject jsonObject = null;
                    try
                    {
                        jsonObject = new JSONObject(res);

                        String weatherInfo = jsonObject.getString("weather");
                        //String tempInfo =jsonObject.getString("main");

                        JSONArray jsonArray = new JSONArray(weatherInfo);
                        for(int i = 0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonPart = jsonArray.getJSONObject(i);

                            main = jsonPart.getString("main");
                            dets = jsonPart.getString("description");
                        }

                       /* JSONArray jsonArray1 = new JSONArray(tempInfo);
                        for(int i = 0;i<jsonArray1.length();i++)
                        {
                            JSONObject jsonPart2 = jsonArray1.getJSONObject(i);
                            temp = jsonPart2.getString("temp");
                            min = jsonPart2.getString("temp_min");
                            max = jsonPart2.getString("temp_max");
                        }*/
                        Intent intent = new Intent(getApplicationContext(),Task4b.class);
                        intent.putExtra("main",main);
                        intent.putExtra("desc",dets);
                        /*intent.putExtra("temp",temp);
                        intent.putExtra("min",min);
                        intent.putExtra("max",max);*/
                        intent.putExtra("city",city.getText().toString().trim());
                        startActivity(intent);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                catch(ExecutionException e)
                {
                    e.printStackTrace();
                }


            }
        });

    }


}
