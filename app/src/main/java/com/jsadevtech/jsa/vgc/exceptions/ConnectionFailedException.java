package com.jsadevtech.jsa.vgc.exceptions;

/**
 * Created by shirkam on 11/06/2015.
 */
public class ConnectionFailedException extends Exception
{
    public ConnectionFailedException()
    {}

    public String getMessage()
    { return "ERROR: No ha sido posible realizar la conexion. Tal vez no se este conectado a internet"; }
}
