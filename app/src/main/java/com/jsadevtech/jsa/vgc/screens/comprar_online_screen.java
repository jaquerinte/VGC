package com.jsadevtech.jsa.vgc.screens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jsadevtech.jsa.vgc.R;

/**
 * Created by Ivan on 10/07/2015.
 */
public class comprar_online_screen extends Activity{
   private WebView  paginaIfa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar_online);
        StrictMode.enableDefaults();//modo stricto necesario para la conexion a internet

        paginaIfa = (WebView) findViewById(R.id.webifa);

        //activamos Javascrip

        WebSettings setings = paginaIfa.getSettings();
        setings.setJavaScriptEnabled(true);

        //url
        paginaIfa.loadUrl("http://www.vgcomic.com/entradas/");

        //forzamos el webView para que abra los enlaces internos de la APP
        paginaIfa.setWebViewClient(new WebViewClient());





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_comprar_online_screen, menu);
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
            startActivity(new Intent(comprar_online_screen.this, informacion.class));
            return true;
        }
        if(id == R.id.action_zonas) {
            startActivity(new Intent(comprar_online_screen.this, zonas_screen.class));
            return true;
        }
        if(id == R.id.action_about) {
            startActivity(new Intent(comprar_online_screen.this, about_screen.class));
            return true;
        }
        if(id == R.id.action_principal) {
            startActivity(new Intent(comprar_online_screen.this, main_screen.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
