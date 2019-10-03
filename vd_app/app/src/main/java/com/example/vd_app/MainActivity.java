package com.example.vd_app;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        task.execute("http://papvidadigital-test.com/api");

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

            Log.i("Website content", result);

            try {
                JSONArray arr = new JSONArray(result);

                String[] nodeId  = new String[50];
                String[] nodeLon = new String[50];
                String[] nodeLat = new String[50];

                for (int i = 0; i < arr.length();i++){
                    JSONObject jsonPart = arr.getJSONObject(i);

                    nodeId[i] = jsonPart.getString("id");
                    Log.i("NODE " + i + " id",nodeId[i]);

                    nodeLon[i] = jsonPart.getString("longitud");
                    Log.i("NODE " + i + " lon",nodeLon[i]);

                    nodeLat[i] = jsonPart.getString("latitud");
                    Log.i("NODE " + i + " lat",nodeLat[i]);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}
