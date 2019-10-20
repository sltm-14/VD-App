package com.example.getlatestattributedata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

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

        ArrayList<String> nodes_id            = new ArrayList<>();
        ArrayList<String> latestAttributeData = new ArrayList<>();

        nodes_id.add("A0");
        nodes_id.add("A2");
        nodes_id.add("A3");
        nodes_id.add("HM1");
        nodes_id.add("GW1");

        GetLatestAttributeData process = new GetLatestAttributeData();

        AttributesData attributesLatestData = new AttributesData(nodes_id,"temp");

        try {
            latestAttributeData = process.execute(attributesLatestData).get();
            Log.i("VD-NODE TEST",latestAttributeData.get(0));
            Log.i("VD-NODE TEST",latestAttributeData.get(1));
            Log.i("VD-NODE TEST",latestAttributeData.get(2));
            Log.i("VD-NODE TEST",latestAttributeData.get(3));
            Log.i("VD-NODE TEST",latestAttributeData.get(4));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
