package com.jsadevtech.vgc.servicetest;

/**
 * Created by shirkam on 06/09/2015.
 */
public class TooManyMinutesException extends Exception {
    public TooManyMinutesException()
    {super();}

    public String getMessage()
    { return "ERROR: Demasiados minutos para la operacion.";}

}
