package com.example.googlemapstest;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_);

        /* Add a marker in Primavera - Node 1 */
        LatLng primaveraNodo_1 = new LatLng(20.580407, -103.511447);
        mMap.addMarker(new MarkerOptions().position(primaveraNodo_1).title("Marker in Primavera - nodo 1"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(primaveraNodo_1));

        /* Add a marker in Primavera - Node 2 */
        LatLng primaveraNodo_2 = new LatLng(20.580226, -103.511112);
        mMap.addMarker(new MarkerOptions().position(primaveraNodo_2).title("Marker in Primavera - nodo 2"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(primaveraNodo_2));

        /* Add a marker in Primavera - Node 3 */
        LatLng primaveraNodo_3 = new LatLng(20.580069, -103.511555);
        mMap.addMarker(new MarkerOptions().position(primaveraNodo_3).title("Marker in Primavera - nodo 3"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(primaveraNodo_3));

        /* Add a marker in Primavera - Node 4 */
        LatLng primaveraNodo_4 = new LatLng(20.579889, -103.511168);
        mMap.addMarker(new MarkerOptions().position(primaveraNodo_4).title("Marker in Primavera - nodo 4"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(primaveraNodo_4,10));

    }
}
