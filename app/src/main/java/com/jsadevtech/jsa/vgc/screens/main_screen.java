package com.jsadevtech.jsa.vgc.screens;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.StrictMode;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.jsadevtech.jsa.vgc.auxiliars.Notifications;
import com.jsadevtech.jsa.vgc.R;

//import com.facebook.FacebookSdk;


public class main_screen extends Activity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "XIvJydx7ZcQovBSAOzMltxxyZ";
    private static final String TWITTER_SECRET = "KuC1QuvOAWOvJYKd2sZIZlbfVAwWWFSZlseBAldbvkeqtyWB67";

    private static final int NOTIFICACION_PRUEBA = 1;
    private Notifications newNotificacionConst;
    NotificationManager nt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_screen);
        StrictMode.enableDefaults();//modo stricto necesario para la conexion a internet
        //declaracion de notificaciones
        nt = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //fin de declaracion de notificaciones

        //buttons declaracion
        Button buttonInvitados =(Button) findViewById(R.id.buttonInvitados);
        Button buttonHorarios =(Button) findViewById(R.id.buttonHoraio);
        Button buttonComprar =(Button) findViewById(R.id.buttonComprar);
        Button buttonPlano =(Button) findViewById(R.id.buttonPlano);
        Button buttonMapa =(Button)findViewById(R.id.buttonMap);
        Button buttonTwitter = (Button) findViewById(R.id.button_twitter);
       // ImageButton informacion = (ImageButton) findViewById(R.id.informacionButton);

        //fin de buttons

        //enlace de botones
        buttonInvitados.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(main_screen.this, invitados_asy_screen.class));
            }
        });
        buttonHorarios.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(main_screen.this,horarios_screen.class));

            }
        });

        buttonComprar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(main_screen.this,comprar_screen.class));
            }
        });

        buttonPlano.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(main_screen.this,plano_screen.class));
            }
        });
        buttonMapa.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(main_screen.this,mapsView_screen.class));
            }
        });
        buttonTwitter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(main_screen.this,twitter_screen.class));
            }
        });

       /* informacion.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(main_screen.this, com.jsadevtech.jsa.vgc.screens.informacion.class));
            }
        });*/

        //FIn enlace bottones


         }

    //TODO Este es el metodo para usar las notificacion, NO BORRAR
/*
    private void getData()
    {
        Vector<Evento> aux;
        String result = "RESULTADOS: \n";
        textoinicio.setText("");

        try
        {
            aux = EventoBD.getEventosDesdeFecha("2015/09/14 16:00:00");

            for(int i=0; i<aux.size(); i++) {

                newNotificacionConst = new Notifications(getApplicationContext(),"", aux.get(i).getNombre(), aux.get(i).getLugar()+"\n"+aux.get(i).getPersona_destacada(), R.drawable.icon, true, true, 2);
                nt.notify(NOTIFICACION_PRUEBA+i, newNotificacionConst.getNotificacion());

                result += aux.get(i).toString();
            }

            textoinicio.setText(result);
        }
        catch(Exception ex)
        { textoinicio.setText(ex.getMessage()); }
    }
*/



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
        if (id == R.id.action_colaboradores) {
            startActivity(new Intent(main_screen.this, informacion.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
