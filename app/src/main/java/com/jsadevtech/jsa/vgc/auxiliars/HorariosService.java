package com.jsadevtech.jsa.vgc.auxiliars;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;

/**
 * Created by Ivan on 17/09/2015.
 */
public class HorariosService extends Service {
    NotificationManager nt;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
    @Override
    public void onCreate() {
        HandlerThread thread = new HandlerThread("ServiceStartArguments");
        thread.start();
        nt = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
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



}
