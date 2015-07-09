package com.jsadevtech.jsa.vgc;

import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class comprar_fisico_screen extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar_fisico_screen);
        Fragment fragmento =getFragmentManager().findFragmentById(R.id.map);

        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }


    private void setUpMap() {

        LatLng ifaPosition = new LatLng(38.296248,-0.575816);




        Marker ifa = mMap.addMarker(new MarkerOptions()
                        .position(ifaPosition)
                        .title("VGC 2015")
                        .snippet("Como te lo vas a perder ¡¡¡¡Compra ya!!!!")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.markervgc))


        );
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ifaPosition, 10));
        ifa.showInfoWindow();
    }
}
