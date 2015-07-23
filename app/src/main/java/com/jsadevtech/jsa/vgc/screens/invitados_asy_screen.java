package com.jsadevtech.jsa.vgc.screens;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jsadevtech.jsa.vgc.R;
import com.jsadevtech.jsa.vgc.auxiliars.Group;
import com.jsadevtech.jsa.vgc.auxiliars.Invitado;
import com.jsadevtech.jsa.vgc.auxiliars.InvitadoBD;
import com.jsadevtech.jsa.vgc.auxiliars.InvitadosAdapter;

import java.util.ArrayList;

/**
 * Created by Ivan on 22/07/2015.
 */
public class invitados_asy_screen extends Activity{
    // more efficient than HashMap for mapping integers to objects
    SparseArray<Group> groups = new SparseArray<Group>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitados_screen);
        StrictMode.enableDefaults();//modo stricto necesario para la conexion a internet, ¡¡¡esto poboca que no se pueda usar wassap mietras nuetra app esta activa XD!!!!

        createData();
        //Linkamos con el objeto del xml
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        //Creamos su adaptador de datos

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
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

    private void createData()
    {

        informacionAsyc infoAsyc = new informacionAsyc();
        infoAsyc.execute();
    }





private class informacionAsyc extends AsyncTask<Void, Integer, Boolean>{

    ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBarInvitados);
    ExpandableListView lista =(ExpandableListView) findViewById(R.id.listView);
    SparseArray<Group> auxGroups = new SparseArray<Group>();
    @Override
    protected Boolean doInBackground(Void... params) {

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
                publishProgress(j * 10);
            }

            //Ponemos el grupo de datos con el resto.
            auxGroups.append(i, group);
            publishProgress(i * 10);
            if(isCancelled()) {
                break;
            }
        }
        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progreso = values[0].intValue();

        progressBar.setProgress(progreso);
    }

    @Override
    protected void onPreExecute() {
        progressBar.setMax(100);
        progressBar.setProgress(0);
        lista.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(result)
        {

            InvitadosAdapter adapter = new InvitadosAdapter(invitados_asy_screen.this,auxGroups);
            lista.setAdapter(adapter);
            System.out.println(auxGroups.toString());
            progressBar.setVisibility(View.INVISIBLE);
            lista.setVisibility(View.VISIBLE);

            
        }
    }

    @Override
    protected void onCancelled() {
        startActivity(new Intent(invitados_asy_screen.this, com.jsadevtech.jsa.vgc.screens.main_screen.class));

    }
}

}
