package com.example.nodeinfo;

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

        ArrayList<ArrayList> node_info   = new ArrayList<>();
        ArrayList<String> attribute_vals = new ArrayList<>();

        getNodeInfo process = new getNodeInfo();

        try {
            node_info      = process.execute("A0").get();
            attribute_vals = node_info.get(2);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i("VD-NODE TEST",attribute_vals.get(0));
    }
}
