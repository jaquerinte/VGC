package com.jsadevtech.jsa.vgc.auxiliars;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

/**
 * Created by shirkam on 01/09/2015.
 */
public class MyService extends Service
{
    // This is the object that receives interactions from clients.  See
    // RemoteService for a more complete example.
    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }


    @Override
    public void onCreate()
    {
        getEventNotifications();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    //Devolvemos que el proceso debe estar siempre activo
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy()
    {} //DO NOTHING

    //TODO A method that does the work of the service
    private void getEventNotifications()
    {
        String horaActual = Time.fechaHoraActual();
    }

}
