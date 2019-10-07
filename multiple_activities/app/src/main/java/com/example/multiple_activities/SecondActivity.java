package com.example.multiple_activities;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class SecondActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        ArrayList<String> intent_nodes_id  = (ArrayList<String>) getIntent().getSerializableExtra("nodes_id");
        ArrayList<Double> intent_nodes_lat = (ArrayList<Double>) getIntent().getSerializableExtra("nodes_lat");
        ArrayList<Double> intent_nodes_lon = (ArrayList<Double>) getIntent().getSerializableExtra("nodes_lon");
        int intent_nodes_len = (int) getIntent().getSerializableExtra("nodes_len");


        mMap = googleMap;


        for (int i = 0; i < intent_nodes_len;i++){
            LatLng sydney = new LatLng(intent_nodes_lat.get(i), intent_nodes_lon.get(i));
            mMap.addMarker(new MarkerOptions().position(sydney).title(intent_nodes_id.get(i)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        }


    }
}
