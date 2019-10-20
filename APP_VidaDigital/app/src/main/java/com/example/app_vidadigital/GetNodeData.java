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

public class GetNodeData extends AsyncTask<String, Void, ArrayList<ArrayList>> {
    String exce = "EXCE";
    String data = "";
    String dataParsed = "";

    @Override
    protected  ArrayList<ArrayList> doInBackground(String... node) {

        /* Listas para los datos que pueden -o no- tener los nodos */

        ArrayList<String> val_names       = new ArrayList<String>(){
            {
                add("bat");  add("temp");  add("hum");  add("c02");  add("n02");
                add("co");   add("o2");    add("tmps"); add("tmpi"); add("hums");
                add("ph");   add("orp");   add("do");   add("con");
            }
        };

        ArrayList<String> node_data       = new ArrayList<>();
        ArrayList<String> node_fecha      = new ArrayList<>();

        ArrayList<ArrayList> nodes_info   = new ArrayList<>();

        int nodesLenght = 0;

        /* Se obtienen los primeros datos del JSON Array (JSONObject con datos de nodos y fecha_hora) */

        try {
            String myURL = "https://papvidadigital-test.com/api/"+node[0]+"/readings";

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
            nodesLenght   = arr.length();

            for (int i = 0; i < nodesLenght;i++){
                JSONObject jsonPart = arr.getJSONObject(i);

                node_data.add  (jsonPart.getString("data"));
                node_fecha.add (jsonPart.getString("fecha_hora"));

                //Log.i("VD-NODE " + i + " Fecha",node_fecha.get(i));
            }

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

        for (int j = 0 ; j < val_names.size() ; j++) {

            ArrayList<String> node_aux = new ArrayList<>();

            for (int i = 0 ; i < nodesLenght ; i++) {

                JSONObject jsonData  = null;

                try {
                    jsonData = new JSONObject(node_data.get(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    node_aux.add (jsonData.getString(val_names.get(j)));
                } catch (JSONException e) {
                    node_aux.add (" ");
                    e.printStackTrace();
                }

                //Log.i("VD-NODE " + i + " " + val_names.get(j) , node_aux.get(i));

            }

            nodes_info.add(node_aux);
        }

        return nodes_info;
    }

    @Override
    protected void onPostExecute(ArrayList<ArrayList> arrayLists) {
        super.onPostExecute(arrayLists);
    }
}
