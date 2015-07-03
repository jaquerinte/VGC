package com.jsadevtech.jsa.vgc;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class mapsView extends FragmentActivity implements OnMapReadyCallback {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_view);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        // pone un marcador en ifa y mueve la camara
        LatLng ifa = new LatLng(38.296248,-0.575816);
        map.setMyLocationEnabled(true);
        map.addMarker(new MarkerOptions().position(ifa).title("IFA, donde se celebra la VGC"));
        map.moveCamera(CameraUpdateFactory.newLatLng(ifa));
    }
}