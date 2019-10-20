package com.example.app_vidadigital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public class AttributesData {
        public ArrayList<String> nodesArray;
        public String            attribute;
        AttributesData(ArrayList<String> array,String att){
            nodesArray = array;
            attribute  = att;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ArrayList> node_info;
        ArrayList<String> nodes_id = new ArrayList<>();
        ArrayList<Double> nodes_lon = new ArrayList<>();
        ArrayList<Double> nodes_lat = new ArrayList<>();

        GetNodes process = new GetNodes();

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

        Intent intent = new Intent(getApplicationContext(),MapsActivity.class);

        intent.putExtra("nodes_id",  nodes_id);
        intent.putExtra("nodes_lon", nodes_lon);
        intent.putExtra("nodes_lat", nodes_lat);
        intent.putExtra("nodes_len", nodes_id.size());

        startActivity(intent);

    }
}
