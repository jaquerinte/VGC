package com.jsadevtech.jsa.vgc;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.Vector;


public class main_screen extends Activity {
    TextView textoinicio;
    private static final int NOTIFICACION_PRUEBA = 1;
    private Notifications newNotificacionConst;
    NotificationManager nt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        StrictMode.enableDefaults();//modo stricto necesario para la conexion a internet


        textoinicio = (TextView) findViewById(R.id.texto);
        Button bt = (Button) findViewById(R.id.buttonNotifications);
        Button buttonInvitados =(Button) findViewById(R.id.buttonInvitados);
        nt = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        bt.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                getData();

            }

        });
        buttonInvitados.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(main_screen.this ,invitados_screen.class ));
            }
        });



             // textoinicio.setText(Time.fechaHoraActual());


         }


    public void getData()
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

}
