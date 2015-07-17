package com.jsadevtech.jsa.vgc;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
//import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.net.URI;

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
        mMap.setMyLocationEnabled(true);
        //inicio de los markers
        final Marker monografic = mMap.addMarker(new MarkerOptions()
                        .position(latMonografic)
                        .title("Monografic")
                        .snippet("Comics y merchandising")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.markermono))


        );
       final Marker fnac = mMap.addMarker(new MarkerOptions()
                        .position(latFnac)
                        .title("FNAC")
                        .snippet("Toda la cultura y la tecnologia")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.markerfnac))


        );
        final Marker ateneo = mMap.addMarker(new MarkerOptions()
                        .position(latAteneo)
                        .title("Ateneo")
                        .snippet("Comics")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.markerate))


        );
       final Marker comixcity = mMap.addMarker(new MarkerOptions()
                        .position(latComixcity)
                        .title("Comix City")
                        .snippet("")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.markercomix))


        );
        final Marker homelands = mMap.addMarker(new MarkerOptions()
                        .position(latHomelands)
                        .title("Homelands")
                        .snippet("Tienda de ocio alternativo")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.markerhome))


        );
        final Marker spiderland = mMap.addMarker(new MarkerOptions()
                        .position(latSpiderland)
                        .title("Spiderland")
                        .snippet("Comics")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.markersp))


        );


        //fin de los markers

        //incio de los liseners de los markers
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                TextView textoInformativo = (TextView) findViewById(R.id.texto_distibuidoror);
                ImageView imagenInformativo = (ImageView) findViewById(R.id.imagen_distribuidor);

            @Override
            public boolean onMarkerClick(final Marker marker) {

                if (marker.equals(monografic))
                {
                    textoInformativo.setText(R.string.monograficDesciption);
                    imagenInformativo.setImageResource(R.drawable.monografic_logo);
                    return true;
                }
               else if(marker.equals(fnac)){
                    textoInformativo.setText(R.string.fnacDesciption);
                    imagenInformativo.setImageResource(R.drawable.fnac);
                    return true;
                }
                else if(marker.equals(ateneo)){
                    textoInformativo.setText(R.string.ateneoDesciption);
                    imagenInformativo.setImageResource(R.drawable.ateneo_logo);
                    return true;
                }
                else if(marker.equals(comixcity)){
                    textoInformativo.setText(R.string.comixcityDesciption);
                    imagenInformativo.setImageResource(R.drawable.comixcity_logo);
                    return true;
                }
                else if(marker.equals(homelands)){
                    textoInformativo.setText(R.string.homelandsDesciption);
                    imagenInformativo.setImageResource(R.drawable.homelands_logo);
                    return true;
                }
                else if(marker.equals(spiderland)){
                    textoInformativo.setText(R.string.spiderlandDesciption);
                    imagenInformativo.setImageResource(R.drawable.spiderland);
                    return true;
                }

                else {
                return false;
                }
            }
        });

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ifaPosition, 10));
        //monografic.showInfoWindow();
    }
}
