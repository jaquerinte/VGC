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
import android.widget.ExpandableListView;

import com.jsadevtech.jsa.vgc.R;
import com.jsadevtech.jsa.vgc.auxiliars.ExpandableListAdapter;
import com.jsadevtech.jsa.vgc.auxiliars.Group;

public class actividades_screen extends Activity {
    private SparseArray<Group> groups = new SparseArray<>();
    private final String[] tipoActividades = {"CAMPEONATOS", "CONCURSOS", "TORNEOS"};
    private final String[][] nombreActividades = {{"Magic", "Infinity"}, {"Cosplay"},
            {"Ultra Street Fighter IV", "Street Fighter II Champion Edition", "Super Smash Bros"}};

    private ExpandableListView listView;
    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitados_screen);
        StrictMode.enableDefaults();//modo stricto necesario para la conexion a internet


        //Realizamos la interpretacion de los datos en modo asincrono
        actividades_asy descargar = new actividades_asy();
        descargar.execute();
        //Linkamos con el objeto del xml
        listView = (ExpandableListView) findViewById(R.id.listView);
        web = (WebView) findViewById(R.id.webView_actividades);

        //Creamos su adaptador de datos
        ExpandableListAdapter adapter = new ExpandableListAdapter(this,
                groups);
        //Y se lo ponemos
        listView.setAdapter(adapter);

        listView.setOnChildClickListener( new ExpandableListView.OnChildClickListener(){
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id){
                //Como todas las web tienen la misma estructura de nombre, podemos pasar el nombre de la actividad
                abrirWeb(nombreActividades[groupPosition][childPosition]);

                return true;
            }
        });
    }

    private void createData()
    {

        for(int i=0; i<tipoActividades.length; i++)
        {
            //Le ponemos el titulo al grupo
            Group group = new Group(tipoActividades[i]);
            for(int j=0; j<nombreActividades[i].length; j++)
            {
                //Ponemos las actividades dentro de cada grupo
                group.children.add(nombreActividades[i][j]);
            }

            groups.append(i, group);
        }
    }

    private void abrirWeb(String activity)
    {
        listView.setVisibility(View.INVISIBLE);


        //Activamos el javascript
        WebSettings setings = web.getSettings();
        setings.setJavaScriptEnabled(true);

        //Ponemos la url
        web.loadUrl(parseURL(activity));
        //Forzamos la webview para que abra enlaces dentro de la pagina
        web.setWebViewClient(new WebViewClient());
        //Y la ponemos visible
        web.setVisibility(View.VISIBLE);
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

    private class actividades_asy extends AsyncTask<Void, Integer, Boolean>
    {
        @Override
        protected Boolean doInBackground(Void... params) {

            createData();
            if(isCancelled()) {
            }

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();
        }

        @Override
        protected void onPreExecute() {


        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
            {
                ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
                //Creamos su adaptador de datos
                ExpandableListAdapter adapter = new ExpandableListAdapter(actividades_screen.this, groups);
                //Y se lo ponemos
                listView.setAdapter(adapter);

                listView.setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected void onCancelled() {
            startActivity(new Intent(actividades_screen.this,main_screen.class));
        }
    }
}
