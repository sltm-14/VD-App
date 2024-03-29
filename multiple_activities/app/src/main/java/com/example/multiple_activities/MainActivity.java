package com.example.multiple_activities;import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        task.execute("https://papvidadigital-test.com/api");

    }

    public class DownloadTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL nodesURL;
            HttpURLConnection nodesURLConnection = null;

            try {
                nodesURL =new URL(urls[0]);
                nodesURLConnection = (HttpURLConnection) nodesURL.openConnection();
                InputStream in = nodesURLConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while(data != -1){
                    char current = (char)data;
                    result += current;
                    data = reader.read();
                }
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONArray arr = new JSONArray(result);

                ArrayList<String> nodeId = new ArrayList<>();
                ArrayList<Double> nodeLon = new ArrayList<>();
                ArrayList<Double> nodeLat = new ArrayList<>();

                for (int i = 0; i < arr.length();i++){
                    JSONObject jsonPart = arr.getJSONObject(i);

                    nodeId.add (jsonPart.getString("id"));
                    Log.i("NODE " + i + " id",nodeId.get(i));

                    nodeLon.add (jsonPart.getDouble("longitud"));
                    Log.i("NODE " + i + " lon",nodeLon.get(i).toString());

                    nodeLat.add (jsonPart.getDouble("latitud"));
                    Log.i("NODE " + i + " lat",nodeLat.get(i).toString());
                }

                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);

                intent.putExtra("nodes_id",nodeId);
                intent.putExtra("nodes_lon",nodeLon);
                intent.putExtra("nodes_lat",nodeLat);
                intent.putExtra("nodes_len", arr.length());

                startActivity(intent);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}





