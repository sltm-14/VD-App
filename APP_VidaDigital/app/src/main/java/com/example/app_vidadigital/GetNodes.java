package com.example.app_vidadigital;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GetNodes extends AsyncTask<Void, Void, ArrayList<ArrayList>> {

    String data = "";

    @Override
    protected ArrayList<ArrayList> doInBackground(Void... voids) {

        ArrayList<ArrayList> nodes_info   = new ArrayList<>();

        ArrayList<String> nodeId  = new ArrayList<>();
        ArrayList<Double> nodeLon = new ArrayList<>();
        ArrayList<Double> nodeLat = new ArrayList<>();

        int nodesLenght = 0;

        /* Id, longitud y latitud */

        try {
            URL url = new URL("https://papvidadigital-test.com/api");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";

            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONArray arr = new JSONArray(data);
            nodesLenght   = arr.length();

            for (int i = 0; i < nodesLenght;i++){
                JSONObject jsonPart = arr.getJSONObject(i);

                nodeId.add (jsonPart.getString("id"));
               // Log.i("VD-NODE " + i + " id",nodeId.get(i));

                nodeLon.add (jsonPart.getDouble("longitud"));
               // Log.i("VD-NODE " + i + " lon",nodeLon.get(i).toString());

                nodeLat.add (jsonPart.getDouble("latitud"));
               // Log.i("VD-NODE " + i + " lat",nodeLat.get(i).toString());
            }

            nodes_info.add (nodeId);
            nodes_info.add (nodeLon);
            nodes_info.add (nodeLat);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return nodes_info;
    }

}