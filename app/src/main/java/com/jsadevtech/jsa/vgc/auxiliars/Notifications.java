package com.jsadevtech.jsa.vgc.auxiliars;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;

import com.jsadevtech.jsa.vgc.R;

/**
 * Created by Jaquer on 11/06/2015.
 * Clase encargada de Simplificar las notificaciones en vison de que sean llamandas de una BBDD
 * V:1.1.0
 */
public class Notifications {

    private NotificationCompat.Builder notification;
    private String contentTitle;
    private String contentText;
    private String ticket;
    private int smallImag;
    private Bitmap bigImag;
    private Boolean sound;
    private int priorty;
    private Boolean vibracion;



    public Notifications(Context micontexto){

        notification = new NotificationCompat.Builder(micontexto);
        notification.setTicker("");
        notification.setContentTitle("");
        notification.setContentText("");

        sound=false;
        vibracion=false;

    }

    /**
     *  Contructor avanzado de la clase notifications que crea un objeto notificacion completo.
     * @param miContexto  Contexto donde se lanzara la notificacion de tipo Context
     * @param miTiquet Lo que se va a escribir en el tikect cuando aparezca la notificacion
     * @param miTitulo Titulo de la notificacion
     * @param miTexto Texto de la notificacion
     * @param miImgSmall Id del icono pequeno de la aplicacion
     * @param song si la aplicacion tiene sonido por defecto
     * @param vibrate si la aplicacion tendra vibracion
     * @param prioridad prioridad de la aplicacion 0 minima, 1 baja, 2 defaul,3 alta, 4 maxima
     */
    public Notifications(Context miContexto, String miTiquet, String miTitulo, String miTexto, int miImgSmall, Boolean song, Boolean vibrate, int prioridad){
         notification = new NotificationCompat.Builder(miContexto);
        this.setTicket(miTiquet);
        this.setContentTitle(miTitulo);
        this.setContentText(miTexto);
        this.setSmallImag(miImgSmall);
        if(song){this.setStardartSong();}
        if(vibrate) {this.setVibracion();}
        this.setPriorty(prioridad);
    }
    public void setTicket(String miTexto) {
        ticket=miTexto;
        notification.setTicker(ticket);
    }
    public void setIntent (Intent destiny, Context source){

        PendingIntent contentIntent = PendingIntent.getActivity(source, 0, destiny, 0);
        notification.setContentIntent(contentIntent);
    }

    public void setContentTitle(String miTexto){
        contentTitle = miTexto;
        notification.setContentTitle(contentTitle);
    }

    public void setContentText(String miTexto){
        contentText=miTexto;
        notification.setContentText(contentText);
    }

    public void setSmallImag(int id){
        smallImag =id;
        notification.setSmallIcon(smallImag);
    }
    public void setBigImag (Bitmap img){
        bigImag=img;
        notification.setLargeIcon(bigImag);
    }
    public void setPendinIntent(PendingIntent pantallaPendiente){
        notification.setContentIntent(pantallaPendiente);
    }

    public void setStardartSong(){
        sound=true;
        Uri sonido = RingtoneManager.getDefaultUri(Notification.DEFAULT_SOUND);
        notification.setSound(sonido);

    }
    public void setSoundMario(){
        sound=true;
        Uri sonido = Uri.parse("android.resource://com.jsadevtech.jsa.vgc/" + R.raw.coinmario) ;
        notification.setSound(sonido);
    }

    /**
     * Establece la prioridad de la aplicacion: 0 minima, 1 baja , 2 porDefecto ,3 alta, 4 maxima,
     * por Default default.
     * Nota es una aproximacion par el SO, el ara lo que le parezca en cosideracion con lo que se le manda
     * @param prioridad 0 minima, 1 baja , 2 porDefecto ,3 alta, 4 maxima,por Default default.
     */
    public void setPriorty(int prioridad){
        priorty=prioridad;
        switch (priorty){
            case 0: notification.setPriority(Notification.PRIORITY_MIN);
                break;
            case 1: notification.setPriority(Notification.PRIORITY_LOW);
                break;
            case 2: notification.setPriority(Notification.PRIORITY_DEFAULT);
                break;
            case 3: notification.setPriority(Notification.PRIORITY_HIGH);
                break;
            case 4: notification.setPriority(Notification.PRIORITY_MAX);
                break;
            default: notification.setPriority(Notification.PRIORITY_DEFAULT);
                   break;
        }
    }

    public void setVibracion(){
        long[] pattern = new long[]{1000,500,1000};
        vibracion=true;
        notification.setVibrate(pattern);
    }

    public Notification getNotificacion(){
        return notification.build();
    }

}
