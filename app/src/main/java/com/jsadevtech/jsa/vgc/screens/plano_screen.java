package com.jsadevtech.jsa.vgc.screens;


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


import com.jsadevtech.jsa.vgc.R;

/**
 * Created by Ivan on 01/07/2015.
 */
public class plano_screen extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plano_screen);
        StrictMode.enableDefaults();//modo stricto necesario para la conexion a internet
        setTitle("Plano del recinto");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_plano_screen, menu);
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
            startActivity(new Intent(plano_screen.this, informacion.class));
            return true;
        }
        if(id == R.id.action_zonas) {
            startActivity(new Intent(plano_screen.this, zonas_screen.class));
            return true;
        }
        if(id == R.id.action_about) {
            startActivity(new Intent(plano_screen.this, about_screen.class));
            return true;
        }
        if(id == R.id.action_principal) {
            startActivity(new Intent(plano_screen.this, main_screen.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
