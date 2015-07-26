package com.jsadevtech.jsa.vgc;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jaquer on 01/07/2015.
 *
 * clase encargada de manejar la pantalla de invitados
 */
public class invitados_screen extends Activity {
    // more efficient than HashMap for mapping integers to objects
    SparseArray<Group> groups = new SparseArray<Group>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitados_screen);
        StrictMode.enableDefaults();//modo stricto necesario para la conexion a internet

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);

        if(listView.isInEditMode())
            //Poner algo para mostrar de ejemplo en el IDE

        createData();
        InvitadosAdapter adapter = new InvitadosAdapter(this,
                groups);
        listView.setAdapter(adapter);
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
        //Este array va a guardar los tipos de invitados almacenados en la bd. CINE, COMIC...
        ArrayList<String> tipoInvitados = new ArrayList<>();
        try {
            //Obtenemos valores de la bd
            tipoInvitados  = InvitadoBD.getTipos();
        }
        catch(Exception e)
        { tipoInvitados.add("ERROR: No se ha podido recuperar la informacion."); }

        //Y para todos los tipos de invitados que tenemos, obtenemos sus invitados correspondientes
        for(int i=0; i<tipoInvitados.size(); i++) {
            //Creamos un grupo de titulo tipo de invitado y contenido los invitados que hay
            Group group = new Group(tipoInvitados.get(i));
            ArrayList<Invitado> invitados = new ArrayList<>();

            try {
                //Cojemos los invitados por tipo
                invitados = InvitadoBD.getInvitadosByTipo(tipoInvitados.get(i));
            } catch (Exception e)
            { invitados.add(new Invitado(e.getMessage(), "", "", "", "", "")); }
            //Y para todos los invitados que nos ha devuelto, los anaydimos al grupo
            for(int j=0; j<invitados.size(); j++)
            {
                group.children.add(invitados.get(j).getNombre());
            }


            //por ultimo, ponemos el grupo recien creado en el array que
            //va a usar la expandable list view.
            groups.append(i, group);

        }
    }

}


