package com.jsadevtech.jsa.vgc;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Jaquer on 16/06/2015.
 *
 * clase con metodos estaticos de tiempo
 */
public class Time {
    /**
     *
     * @return devuelve la fecha actual de la siguente forma yyyy-MM-dd_HH:mm:ss
     */
    public static String fechaHoraActual(){

        return new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault()).format(Calendar.getInstance() .getTime());
    }


}
