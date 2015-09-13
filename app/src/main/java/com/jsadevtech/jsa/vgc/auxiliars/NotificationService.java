package com.jsadevtech.jsa.vgc.auxiliars;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.HandlerThread;
import android.os.IBinder;
import android.preference.PreferenceManager;
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
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

    private void getEventNotifications(final Context cont)
    {
        String horaActual = Time.fechaHoraActual();
        final Timer notificaciones = new Timer("pene");
        TimerTask actualizarNotificaciones = new TimerTask() {
            @Override
            public void run() {
                int lastId= 0;
                try {
                    Vector<Notificacion> notificacionesRestantes = NotificacionesBD.getNotificacionesById("" + lastId);
                    for(int i=0;i<notificacionesRestantes.size();i++){
                        Notifications a = new Notifications(cont,notificacionesRestantes.get(i).getNombre(),notificacionesRestantes.get(i).getNombre(),notificacionesRestantes.get(i).getNombre(), R.drawable.iconoprincipal30x30,true,true,4);
                        nt.notify(Integer.parseInt(notificacionesRestantes.get(i).getId()), a.getNotificacion());
                    }

                    lastId= Integer.parseInt(notificacionesRestantes.get(notificacionesRestantes.size()-1).getId()) ;
                }
                catch(Exception ex){

                }
            }
        };
        notificaciones.schedule(actualizarNotificaciones, new Date(), 60000);
    }


}
