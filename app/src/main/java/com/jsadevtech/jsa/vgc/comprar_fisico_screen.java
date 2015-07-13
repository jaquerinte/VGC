package com.jsadevtech.jsa.vgc;

import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
//import android.widget.Button;

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
        //inicio de las latitudes
        LatLng ifaPosition = new LatLng(38.296248,-0.575816);
        LatLng latMonografic = new LatLng(38.267659,-0.695115);
        LatLng latFnac =new LatLng(38.345437, -0.492089);
        LatLng latAteneo =new LatLng(38.344619, -0.493567);
        LatLng latComixcity =new LatLng(38.344635, -0.493694);
        LatLng latHomelands =new LatLng(38.268102, -0.694669);
        LatLng latSpiderland =new LatLng(38.263011,-0.702073);
        //fin de las latitudes
        //inicio de los markers
        Marker monografic = mMap.addMarker(new MarkerOptions()
                        .position(latMonografic)
                        .title("Monografic")
                        .snippet("Comics y merchandising")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.markervgc))


        );
        Marker fnac = mMap.addMarker(new MarkerOptions()
                        .position(latFnac)
                        .title("FNAC")
                        .snippet("Toda la cultura y la tecnologia")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.markervgc))


        );
        Marker ateneo = mMap.addMarker(new MarkerOptions()
                        .position(latAteneo)
                        .title("Ateneo")
                        .snippet("Comics")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.markervgc))


        );
        Marker comixcity = mMap.addMarker(new MarkerOptions()
                        .position(latComixcity)
                        .title("Comix City")
                        .snippet("")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.markervgc))


        );
        Marker homelands = mMap.addMarker(new MarkerOptions()
                        .position(latHomelands)
                        .title("Homelands")
                        .snippet("Tienda de ocio alternativo")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.markervgc))


        );
        Marker spiderland = mMap.addMarker(new MarkerOptions()
                        .position(latSpiderland)
                        .title("Spiderland")
                        .snippet("Comics")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.markervgc))


        );


        //fin de los markers
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ifaPosition, 10));
        //monografic.showInfoWindow();
    }
}
