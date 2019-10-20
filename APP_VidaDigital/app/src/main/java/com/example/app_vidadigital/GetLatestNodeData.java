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

public class GetLatestNodeData extends AsyncTask< String, Void, ArrayList<String>> {
    String exce = "EXCE";
    String data = "";

    @Override
    protected  ArrayList<String> doInBackground(String... node) {

        /* Listas para los datos que pueden -o no- tener los nodos */

        String node_data  = "";
        String node_fecha = "";
        ArrayList<String> node_aux   = new ArrayList<>();

        ArrayList<String> val_names  = new ArrayList<String>(){
            {
                add("bat");  add("temp");  add("hum");  add("c02");  add("n02");
                add("co");   add("o2");    add("tmps"); add("tmpi"); add("hums");
                add("ph");   add("orp");   add("do");   add("con");
            }
        };

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

            nodesLenght   = arr.length() - 1;

            JSONObject jsonPart = arr.getJSONObject(nodesLenght);

            node_data  = (jsonPart.getString("data"));
            node_fecha = (jsonPart.getString("fecha_hora"));

            //Log.i("VD-NODE " + nodesLenght + " Fecha",node_fecha);

        } catch (MalformedURLException e) {
            //Log.i("EXCEPTION MAL",exce);
            e.printStackTrace();
        } catch (IOException e) {
            //Log.i("EXCEPTION IO",exce);
            e.printStackTrace();
        } catch (JSONException e) {
            //.i("EXCEPTION JSON",exce);
            e.printStackTrace();
        }

        JSONObject jsonData  = null;

        for (int j = 0 ; j < val_names.size() ; j++) {

            try {
                jsonData = new JSONObject(node_data);
                node_aux.add (jsonData.getString(val_names.get(j)));
            }
            catch (JSONException e) {
                node_aux.add (" ");
                e.printStackTrace();
            }

            //Log.i("VD-NODE " + nodesLenght + " " + val_names.get(j) , node_aux.get(j));

        }

        return node_aux;
    }

    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        super.onPostExecute(strings);
    }
}