package com.jsadevtech.jsa.vgc.screens;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
//import android.widget.Button;
import com.google.android.gms.maps.*;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jsadevtech.jsa.vgc.R;

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
        LatLng lat7heroes =new LatLng(37.999417, -1.138713);
        LatLng latFnacM =new LatLng(38.040213, -1.149364);
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
        final Marker heroes7 =mMap.addMarker(new MarkerOptions()
                        .position(lat7heroes)
                        .title("7Heroes")
                        .snippet("Tienda de Comics murcia")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker7heroes))

        );
        final Marker fnacM =mMap.addMarker(new MarkerOptions()
                        .position(latFnacM)
                        .title("FNAC")
                        .snippet("Toda la cultura y la tecnologia")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.markerfnac))

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
                    imagenInformativo.setImageResource(R.drawable.fnac_logo);
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
                    imagenInformativo.setImageResource(R.drawable.spiderland_logo);
                    return true;
                }
                else if(marker.equals(heroes7)){
                    textoInformativo.setText(R.string.heroes7Description);
                    imagenInformativo.setImageResource(R.drawable.heroes_logo);
                    return true;
                }
                else if(marker.equals(fnacM)){
                    textoInformativo.setText(R.string.fnacMDesciption);
                    imagenInformativo.setImageResource(R.drawable.fnac_logo);
                    return true;
                }

                else {
                return false;
                }
            }
        });

        // Get LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Create a criteria object to retrieve provider
        Criteria criteria = new Criteria();
        // Get the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);
        // Get Current Location
        Location myLocation = locationManager.getLastKnownLocation(provider);
        // Get latitude of the current location
        double latitude = myLocation.getLatitude();
        // Get longitude of the current location
        double longitude = myLocation.getLongitude();
        // Create a LatLng object for the current location
        LatLng position = new LatLng(latitude, longitude);
        // Show the current location in Google Map
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 10));

        /*mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ifaPosition, 10));*/
        //monografic.showInfoWindow();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_comprar_fisico_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_colaboradores) {
            startActivity(new Intent(comprar_fisico_screen.this, informacion.class));
            return true;
        }
        if(id == R.id.action_zonas) {
            startActivity(new Intent(comprar_fisico_screen.this, zonas_screen.class));
            return true;
        }
        if(id == R.id.action_about) {
            startActivity(new Intent(comprar_fisico_screen.this, about_screen.class));
            return true;
        }
        if(id == R.id.action_principal) {
            startActivity(new Intent(comprar_fisico_screen.this, main_screen.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
