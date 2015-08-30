package com.jsadevtech.jsa.vgc.screens;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.jsadevtech.jsa.vgc.R;
import com.jsadevtech.jsa.vgc.auxiliars.ExpandableListAdapter;
import com.jsadevtech.jsa.vgc.auxiliars.Group;

public class actividades_screen extends Activity {
    private SparseArray<Group> groups = new SparseArray<>();
    //private final String[] tipoActividades = {"CAMPEONATOS", "CONCURSOS", "TORNEOS"};
    //private final String[][] nombreActividades = {{"Magic", "Infinity"}, {"Cosplay"},
    //        {"Ultra Street Fighter IV", "Street Fighter II Champion Edition", "Super Smash Bros"}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades_screeen);

        Button magicButton = (Button)findViewById(R.id.button_magic);
        Button infinityButton = (Button) findViewById(R.id.button_infinity);

        magicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(actividades_screen.this, webActividades_screen.class);
                i.putExtra("url", parseURL(getString(R.string.campeonatos_magic)));
                i.putExtra("title", getString(R.string.campeonatos_magic));
                startActivity(i);
            }
        });
        magicButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final String magicGif = "http://www.reactiongifs.com/r/mgc.gif";
                Intent i = new Intent(actividades_screen.this, webActividades_screen.class);
                i.putExtra("url", magicGif);
                i.putExtra("title", getString(R.string.campeonatos_magic));
                startActivity(i);
                return true;
            }
        });
        infinityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(actividades_screen.this, webActividades_screen.class);
                i.putExtra("url", parseURL(getString(R.string.campeonatos_infinity)));
                i.putExtra("title", getString(R.string.campeonatos_infinity));
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actividades_screeen, menu);
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
            startActivity(new Intent(actividades_screen.this, informacion.class));
            return true;
        }
        if(id == R.id.action_zonas) {
            startActivity(new Intent(actividades_screen.this, zonas_screen.class));
            return true;
        }
        if(id == R.id.action_about) {
            startActivity(new Intent(actividades_screen.this, about_screen.class));
            return true;
        }
        if(id == R.id.action_principal) {
            startActivity(new Intent(actividades_screen.this, main_screen.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String parseURL(String activity)
    {
        String url = "http://www.vgcomic.com/";
        String[] partes;

        partes = activity.toLowerCase().split(" ");

        for(int i=0; i<partes.length; i++)
        {
            if(i == partes.length-1)
                url += partes[i]+"/";
            else
                url += (partes[i]+"-");
        }

        return url;
    }
}