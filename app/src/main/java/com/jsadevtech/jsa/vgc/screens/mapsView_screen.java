package com.jsadevtech.jsa.vgc.screens;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jsadevtech.jsa.vgc.R;

public class mapsView_screen extends FragmentActivity implements OnMapReadyCallback {



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
        LatLng ifaPosition = new LatLng(38.296248,-0.575816);
        map.setMyLocationEnabled(true);
        Marker ifa=map.addMarker(new MarkerOptions()
                        .position(ifaPosition)
                        .title("VGC 2015")
                        .snippet("Como te lo vas a perder")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.markervgc))


        );
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(ifaPosition,10));
        ifa.showInfoWindow();

        //map.moveCamera(CameraUpdateFactory.newLatLng(ifa));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}