package com.jsadevtech.jsa.vgc.screens;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jsadevtech.jsa.vgc.R;

/**
 * Created by Ivan on 14/09/2015.
 */
public class settings_screen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button buttonResetear =(Button) findViewById(R.id.button_resetear_notificaciones);
        final SharedPreferences.Editor toSaved = getSharedPreferences("com.jsadevtech.jsa.vgc.saved", MODE_PRIVATE).edit();
        buttonResetear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSaved.putInt("lastid", 0);
                Toast.makeText(settings_screen.this, "Notificaciones Reseteadas" ,Toast.LENGTH_LONG).show();

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_colaboradores) {
            startActivity(new Intent(settings_screen.this, informacion.class));
            return true;
        }
        if(id == R.id.action_zonas) {
            startActivity(new Intent(settings_screen.this, zonas_screen.class));
            return true;
        }
        if(id == R.id.action_principal) {
            startActivity(new Intent(settings_screen.this, main_screen.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}




