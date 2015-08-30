package com.jsadevtech.jsa.vgc.screens;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.jsadevtech.jsa.vgc.R;

public class webActividades_screen extends AppCompatActivity {
    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_actividades_screen);
        Bundle bundle = getIntent().getExtras();

        setTitle(bundle.getString("title"));

        web = (WebView)findViewById(R.id.webActividades);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        abrirWeb(bundle.getString("url"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web_actividades_screen, menu);
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

    public void abrirWeb(String url)
    {
        //Activamos el javascript
        WebSettings setings = web.getSettings();
        setings.setJavaScriptEnabled(true);

        //Ponemos la url
        web.loadUrl(url);
        //Forzamos la webview para que abra enlaces dentro de la pagina
        web.setWebViewClient(new WebViewClient());
        //Y la ponemos visible
        web.setVisibility(View.VISIBLE);
    }
}
