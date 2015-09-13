package com.jsadevtech.jsa.vgc.auxiliars;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.HandlerThread;
import android.os.IBinder;
import android.widget.Toast;

import com.jsadevtech.jsa.vgc.R;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/**
 * Created by shirkam on 01/09/2015.
 */


public class MyService extends Service
{
    private final String ides ="savedlastids";
    SharedPreferences prefs = getSharedPreferences(ides, Context.MODE_PRIVATE);
    NotificationManager nt;
    // This is the object that receives interactions from clients.  See
    // RemoteService for a more complete example.
    private final IBinder mBinder = new LocalBinder();
    //private ServiceHandler mServiceHandler;

    public class LocalBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }


    @Override
    public void onCreate()
    {
       // Toast toast = Toast.makeText(getApplicationContext(),"he entrado en servicio", Toast.LENGTH_SHORT);
        //toast.show();

       // nt = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //getEventNotifications(this);

        HandlerThread thread = new HandlerThread("ServiceStartArguments");
        thread.start();

        System.out.println("entrando en servicio");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    //Devolvemos que el proceso debe estar siempre activo
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(MyService.this, "service starting", Toast.LENGTH_SHORT).show();

        return START_STICKY;
    }

    @Override
    public void onDestroy()
    {super.onDestroy();
        Toast.makeText(MyService.this, "Service Destroyed", Toast.LENGTH_LONG).show();} //DO NOTHING

    //TODO A method that does the work of the service
    private void getEventNotifications(final Context cont)
    {
        String horaActual = Time.fechaHoraActual();
        final Timer notificaciones = new Timer("pene");
        TimerTask actualizarNotificaciones = new TimerTask() {
            @Override
            public void run() {
               int lastId= prefs.getInt("id",0);
                try {
                    Vector<Notificacion> notificacionesRestantes = NotificacionesBD.getNotificacionesById("" + lastId);
                    for(int i=0;i<notificacionesRestantes.size();i++){
                        Notifications a = new Notifications(cont,notificacionesRestantes.get(i).getNombre(),notificacionesRestantes.get(i).getNombre(),notificacionesRestantes.get(i).getNombre(), R.drawable.iconoprincipal30x30,true,true,4);
                        nt.notify(Integer.parseInt(notificacionesRestantes.get(i).getId()), a.getNotificacion());
                    }
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("id", Integer.parseInt(notificacionesRestantes.get(notificacionesRestantes.size()-1).getId()) );
                }
                catch(Exception ex){

                }
             }
        };
        notificaciones.schedule(actualizarNotificaciones, new Date(), 60000);
    }

    private void atualizarId(int id){

            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("id", id);




    }

}
