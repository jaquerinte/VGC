package com.jsadevtech.jsa.vgc.screens;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import com.jsadevtech.jsa.vgc.R;
import com.jsadevtech.jsa.vgc.auxiliars.Group;
import com.jsadevtech.jsa.vgc.auxiliars.Invitado;
import com.jsadevtech.jsa.vgc.auxiliars.InvitadoBD;
import com.jsadevtech.jsa.vgc.auxiliars.ExpandableListAdapter;

import java.util.ArrayList;

/**
 * Created by Ivan on 22/07/2015.
 */
public class invitados_asy_screen extends AppCompatActivity {
    // more efficient than HashMap for mapping integers to objects
    SparseArray<Group> groups = new SparseArray<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitados_screen);
        StrictMode.enableDefaults();//modo stricto necesario para la conexion a internet

        setTitle("Invitados");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

      //  createData();
        //ejecutamos la descarga e interpretacion de datos en modo asincrono
        invitadors_asy descargar = new invitadors_asy();
        descargar.execute();
        //Linkamos con el objeto del xml
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);

        //Creamos su adaptador de datos
        ExpandableListAdapter adapter = new ExpandableListAdapter(this,
                groups);
        //Y se lo ponemos
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_invitados_screen, menu);
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
            startActivity(new Intent(invitados_asy_screen.this, informacion.class));
            return true;
        }
        if(id == R.id.action_zonas) {
            startActivity(new Intent(invitados_asy_screen.this, zonas_screen.class));
            return true;
        }
        if(id == R.id.action_about) {
            startActivity(new Intent(invitados_asy_screen.this, about_screen.class));
            return true;
        }
        if(id == R.id.action_principal) {
            startActivity(new Intent(invitados_asy_screen.this, main_screen.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createData()
    {
        //Conseguimos todos los tipos de invitados que hay (comic, cine...).
        ArrayList<String> tipoInvitados = new ArrayList<>();
        try {
            //Obtenemos los tipos de la bd.
            tipoInvitados  = InvitadoBD.getTipos();
        }
        catch(Exception e)
        {
            //Si da error, vamos a mostrar esto por pantalla.
            tipoInvitados.add("ERROR: No se ha podido recuperar la informacion.");
        }

        for(int i=0; i<tipoInvitados.size(); i++) {
            //Creamos un nuevo grupo de datos. Cabecera el tipo de invitados, y
            //subgrupo, todos los invitados de esa categoria.
            Group group = new Group(tipoInvitados.get(i));
            ArrayList<Invitado> invitados = new ArrayList<>();
            try {
                //Invitados de una determinada categoria.
                invitados = InvitadoBD.getInvitadosByTipo(tipoInvitados.get(i));
            } catch (Exception e) //Si no se pueden mostrar invitados, se muestra el error en su lugar.
            { invitados.add(new Invitado(e.getMessage(), "", "", "", "", "")); }
            for(int j=0; j<invitados.size(); j++)
            {
                group.children.add(invitados.get(j).getNombre());
            }

            //Ponemos el grupo de datos con el resto.
            groups.append(i, group);

        }
    }

    private class invitadors_asy extends AsyncTask<Void, Integer, Boolean> {

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
                ExpandableListAdapter adapter = new ExpandableListAdapter(invitados_asy_screen.this, groups);
                //Y se lo ponemos
                listView.setAdapter(adapter);

                listView.setVisibility(View.VISIBLE);


            }
        }

        @Override
        protected void onCancelled() {
            startActivity(new Intent(invitados_asy_screen.this,main_screen.class));
        }
    }










}








