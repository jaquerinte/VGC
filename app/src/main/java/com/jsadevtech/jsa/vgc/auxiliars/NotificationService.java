package com.jsadevtech.jsa.vgc.auxiliars;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.HandlerThread;
import android.os.IBinder;
import android.widget.Toast;

import com.jsadevtech.jsa.vgc.R;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/**
 * Created by Ivan on 13/09/2015.
 */
public class NotificationService extends Service{


    private static final int PREFERENCE_MODE_PRIVATE = 0;

    NotificationManager nt;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
    @Override
    public void onCreate()
    {
        HandlerThread thread = new HandlerThread("ServiceStartArguments");
        thread.start();
        Toast.makeText(this, "Service Started in new thread", Toast.LENGTH_LONG).show();
        nt = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        getEventNotifications(this);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_SHORT).show();
    }

    private void getEventNotifications(final Context cont)
    {
        Toast.makeText(cont, "Intentando pillar not.", Toast.LENGTH_SHORT).show();
        final Timer notificaciones = new Timer("pene");
        TimerTask actualizarNotificaciones = new TimerTask() {
            @Override
            public void run() {
                SharedPreferences.Editor toSaved = getSharedPreferences("com.jsadevtech.jsa.vgc.saved", MODE_PRIVATE).edit();
                SharedPreferences toRestored = getSharedPreferences("com.jsadevtech.jsa.vgc.saved", MODE_PRIVATE);

                try {
                    int lastId= toRestored.getInt("lastid", 0);
                    Toast.makeText(cont, "lastId: "+lastId, Toast.LENGTH_SHORT).show();
                    lastId--;
                    Vector<Notificacion> notificacionesRestantes = NotificacionesBD.getNotificacionesById("" + lastId);
                    for(int i=0; i<notificacionesRestantes.size(); i++){
                        Toast.makeText(cont, "Paso por el for: "+i, Toast.LENGTH_LONG).show();
                        Notifications a = new Notifications(cont,notificacionesRestantes.get(lastId).getNombre(),notificacionesRestantes.get(lastId).getNombre(),notificacionesRestantes.get(lastId).getNombre(), R.drawable.iconoprincipal30x30,true,true,4);
                        a.setSoundMario();
                        nt.notify(Integer.parseInt(notificacionesRestantes.get(lastId).getId()), a.getNotificacion());
                        lastId++;
                    }

                   /* for(int i=lastId;i<notificacionesRestantes.size();i++){
                        System.out.println("paso por el for"+i);
                        Notifications a = new Notifications(cont,notificacionesRestantes.get(i).getNombre(),notificacionesRestantes.get(i).getNombre(),notificacionesRestantes.get(i).getNombre(), R.drawable.iconoprincipal30x30,true,true,4);
                        a.setSoundMario();
                        nt.notify(Integer.parseInt(notificacionesRestantes.get(i).getId()), a.getNotificacion());
                    }*/

                    lastId= Integer.parseInt(notificacionesRestantes.get(notificacionesRestantes.size()-1).getId()) ;

                    System.out.println(lastId);
                    toSaved.putInt("lastid", lastId);
                   // toSaved.putInt("lastid",0);

                    toSaved.commit();
                }
                catch(Exception ex){
                    Toast.makeText(cont, "ERROR: No ha sido posible obtener las notificaciones.", Toast.LENGTH_LONG).show();
                }
            }
        };
        Toast.makeText(cont, "Empezamos a ejecutar el schedule", Toast.LENGTH_SHORT).show();
        notificaciones.schedule(actualizarNotificaciones, new Date(), 60000);
    }


}
