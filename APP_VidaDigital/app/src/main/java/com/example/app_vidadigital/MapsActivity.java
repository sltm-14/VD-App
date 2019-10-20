package com.example.app_vidadigital;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;

    ArrayList<Marker> nodes_markers = new ArrayList<Marker>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        ArrayList<String> intent_nodes_id  = (ArrayList<String>) getIntent().getSerializableExtra("nodes_id");
        ArrayList<Double> intent_nodes_lat = (ArrayList<Double>) getIntent().getSerializableExtra("nodes_lat");
        ArrayList<Double> intent_nodes_lon = (ArrayList<Double>) getIntent().getSerializableExtra("nodes_lon");
        int intent_nodes_len = (int) getIntent().getSerializableExtra("nodes_len");

        mMap = googleMap;

        ArrayList<LatLng> vd_nodes_list = new ArrayList<LatLng>();

        for (int i = 0; i < intent_nodes_len;i++){
            vd_nodes_list.add(new LatLng(intent_nodes_lat.get(i), intent_nodes_lon.get(i)));

            nodes_markers.add(mMap.addMarker(new MarkerOptions().position(vd_nodes_list.get(i)).title(intent_nodes_id.get(i))));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(vd_nodes_list.get(i)));
            mMap.setOnMarkerClickListener(this);
            mMap.setOnInfoWindowClickListener(this);
        }

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this,"Click sobre la ventana de información", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        
        Toast.makeText(this,"Click sobre el marcador " + marker.getTitle(), Toast.LENGTH_SHORT).show();

        return false;
    }
}