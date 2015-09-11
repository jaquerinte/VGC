package com.jsadevtech.jsa.vgc.exceptions;

/**
 * Created by shirkam on 07/09/2015.
 */
public class TooManyDaysException extends Exception {
    public TooManyDaysException()
    { super(); }

    public String getMessage()
    { return "ERROR: Demasiados dias para la operacion.";}
}
