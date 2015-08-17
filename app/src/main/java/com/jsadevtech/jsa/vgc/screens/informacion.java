package com.jsadevtech.jsa.vgc.screens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;

import com.jsadevtech.jsa.vgc.auxiliars.Group;
import com.jsadevtech.jsa.vgc.R;

// TODO hay que cambiarle el nombre a informacion_screen
public class informacion extends Activity {
    // more efficient than HashMap for mapping integers to objects
    private SparseArray<Group> groups = new SparseArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        //StrictMode.enableDefaults();//modo stricto necesario para la conexion a internet
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_informacion, menu);
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
            startActivity(new Intent(informacion.this, informacion.class));
            return true;
        }
        if(id == R.id.action_zonas) {
            startActivity(new Intent(informacion.this, Vgc_screen.class));
            return true;
        }
        if(id == R.id.action_about) {
            startActivity(new Intent(informacion.this, about_screen.class));
            return true;
        }
        if(id == R.id.action_principal) {
            startActivity(new Intent(informacion.this, main_screen.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
