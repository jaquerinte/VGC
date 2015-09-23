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
import com.jsadevtech.jsa.vgc.screens.main_screen;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/**
 * Created by Ivan on 13/09/2015.
 */
public class NotificationService extends Service {

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
    }

    private void getEventNotifications(final Context cont)
    {
        final Timer notificaciones = new Timer("Notificaciones VGC");
        TimerTask actualizarNotificaciones = new TimerTask() {
            @Override
            public void run() {
                SharedPreferences.Editor toSaved = getSharedPreferences("com.jsadevtech.jsa.vgc.saved", MODE_PRIVATE).edit();
                SharedPreferences toRestored = getSharedPreferences("com.jsadevtech.jsa.vgc.saved", MODE_PRIVATE);
                if(toRestored.getBoolean("notificationsStatus", true)) {
                    try {
                        String lastDate = toRestored.getString("lastDate", "2015-09-24 00:00");
                        Vector<Notificacion> notificacionesRestantes = NotificacionesBD.getNotificacionesEarlierThanFecha(lastDate, Time.fechaHoraActual());
                        for (int i = 0; i < notificacionesRestantes.size(); i++) {
                            System.out.println("Paso por el for" + i + "; Notificacion: " + notificacionesRestantes.get(i).getId());
                            Notifications a = new Notifications(cont, notificacionesRestantes.get(i).getNombre(), notificacionesRestantes.get(i).getNombre(), notificacionesRestantes.get(i).getDescripcion(), R.drawable.logo_vgc_blanco30x30, true, true, 4);
                            a.setSoundMario();
                            a.setIntent(new Intent(cont, main_screen.class), cont);
                            nt.notify(Integer.parseInt(notificacionesRestantes.get(i).getId()), a.getNotificacion());
                        }

                        lastDate = Time.fechaHoraActual();

                        System.out.println("Almacenamos: " + lastDate);
                        toSaved.putString("lastDate", lastDate);


                        toSaved.apply();

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        };
        notificaciones.schedule(actualizarNotificaciones, new Date(), 600000);
    }


}
