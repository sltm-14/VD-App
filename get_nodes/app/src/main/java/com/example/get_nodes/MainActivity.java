package com.example.get_nodes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ArrayList> node_info;
        ArrayList<String> nodes_id = new ArrayList<>();
        ArrayList<Double> nodes_lon = new ArrayList<>();
        ArrayList<Double> nodes_lat = new ArrayList<>();

        getNodes process = new getNodes();

        try {
            node_info      = process.execute().get();
            nodes_id  = node_info.get(0);
            nodes_lon = node_info.get(1);
            nodes_lat = node_info.get(2);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i("VD-NODE TEST",nodes_id.get(0));
        Log.i("VD-NODE TEST",nodes_lon.get(0).toString());
        Log.i("VD-NODE TEST",nodes_lat.get(0).toString());
    }
}
