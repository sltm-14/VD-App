package com.example.getlatestattributedata;

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

public class GetLatestAttributeData extends AsyncTask<MainActivity.AttributesData, Void, ArrayList<String> > {
    String exce = "EXCE";
    
    @Override
    protected ArrayList<String> doInBackground(MainActivity.AttributesData... params) {

        MainActivity.AttributesData attData = params[0];

        ArrayList<String> node_list      = attData.nodesArray;
        String            node_attribute = attData.attribute;

        ArrayList<String> node_aux   = new ArrayList<>();

        int arrayLength = node_list.size();



        for(int i = 0; i < arrayLength; i++){
            String data = "";
            int nodesLenght = 0;
            String node_data  = "";
            try {
                String myURL = "https://papvidadigital-test.com/api/"+node_list.get(i)+"/readings";

                URL url = new URL(myURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream             = httpURLConnection.getInputStream();
                BufferedReader bufferedReader       = new BufferedReader(new InputStreamReader(inputStream));

                String line = "";

                while(line != null){
                    line = bufferedReader.readLine();
                    data = data + line;
                }

                JSONArray arr = new JSONArray(data);
                nodesLenght   = arr.length() - 1;

                JSONObject jsonPart = arr.getJSONObject(nodesLenght);

                node_data  = (jsonPart.getString("data"));

            } catch (MalformedURLException e) {
                Log.i("EXCEPTION MAL",exce);
                e.printStackTrace();
            } catch (IOException e) {
                Log.i("EXCEPTION IO",exce);
                e.printStackTrace();
            } catch (JSONException e) {
                Log.i("EXCEPTION JSON",exce);
                e.printStackTrace();
            }

            JSONObject jsonData  = null;

            try {
                jsonData = new JSONObject(node_data);
                node_aux.add (jsonData.getString(node_attribute));
            }
            catch (JSONException e) {
                node_aux.add (" ");
                e.printStackTrace();
            }

            Log.i("VD-NODE " + nodesLenght + " " + node_list.get(i) + " " + node_attribute , node_aux.get(i));

        }

        return node_aux;
    }


}
