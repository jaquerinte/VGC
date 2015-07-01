package com.jsadevtech.jsa.vgc.exceptions;

/**
 * Created by shirkam on 11/06/2015.
 */
public class CouldNotGetInformationException extends Exception
{
    public CouldNotGetInformationException()
    {}

    public String getMessage()
    {
        return "ERROR: No ha sido posible obtener la informacion de los eventos.\n";
    }
}
