package com.jsadevtech.jsa.vgc.screens;


import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

<<<<<<< HEAD:app/src/main/java/com/jsadevtech/jsa/vgc/screens/invitados_screen.java
import com.jsadevtech.jsa.vgc.auxiliars.Group;
import com.jsadevtech.jsa.vgc.auxiliars.Invitado;
import com.jsadevtech.jsa.vgc.auxiliars.InvitadoBD;
import com.jsadevtech.jsa.vgc.auxiliars.InvitadosAdapter;
=======
import com.jsadevtech.jsa.vgc.Group;
import com.jsadevtech.jsa.vgc.Invitado;
import com.jsadevtech.jsa.vgc.InvitadoBD;
import com.jsadevtech.jsa.vgc.InvitadosAdapter;
>>>>>>> master:app/src/main/java/com/jsadevtech/jsa/vgc/invitados_screen.java
import com.jsadevtech.jsa.vgc.R;

import java.util.ArrayList;

/**
 * Created by Shirkam on 14/07/2015.
 * Clase encargada de manejar la pantalla de invitados.
 * Utiliza una ExpandableListView y coje los datos de la bd
 * para mostrarlos por categoria.
 */
public class invitados_screen extends Activity {
    // more efficient than HashMap for mapping integers to objects
    SparseArray<Group> groups = new SparseArray<Group>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitados_screen);
        StrictMode.enableDefaults();//modo stricto necesario para la conexion a internet

        createData();
        //Linkamos con el objeto del xml
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        //Creamos su adaptador de datos
        InvitadosAdapter adapter = new InvitadosAdapter(this,
                groups);
        //Y se lo ponemos
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
        for(int i=0; i<tipoInvitados.size(); i++)
        {
            //Creamos un nuevo grupo de datos. Cabecera el tipo de invitados, y
            //subgrupo, todos los invitados de esa categoria.
            Group group = new Group(tipoInvitados.get(i));
            ArrayList<Invitado> invitados = new ArrayList<>();

            try {
                //Cojemos los invitados por tipo
                //Invitados de una determinada categoria.
                invitados = InvitadoBD.getInvitadosByTipo(tipoInvitados.get(i));
            } catch (Exception e) //Si no se pueden mostrar invitados, se muestra el error en su lugar.
            { invitados.add(new Invitado(e.getMessage(), "", "", "", "", "")); }
            //Y para todos los invitados que nos ha devuelto, los anaydimos al grupo
            for(int j=0; j<invitados.size(); j++)
            {
                group.children.add(invitados.get(j).getNombre());
            }

            //por ultimo, ponemos el grupo recien creado en el array que
            //va a usar la expandable list view.
            //Ponemos el grupo de datos con el resto.
            groups.append(i, group);

        }
    }

}

