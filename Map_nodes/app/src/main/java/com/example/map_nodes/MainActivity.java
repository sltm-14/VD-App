package com.example.map_nodes;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DownloadTask task = new DownloadTask();
        task.execute("http://papvidadigital-test.com/nodos/app.php?action=GetNodes");
    }

    public class DownloadTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL nodesURL;
            HttpURLConnection nodesURLConnection = null;

            try {
                nodesURL =  new URL(urls[0]);
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

        protected void onPostExecute(String result){
            super.onPostExecute(result);

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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
