package com.jsadevtech.jsa.vgc.auxiliars;

/**
 * Created by shirkam on 05/09/2015.
 */
public class Notificacion extends com.jsadevtech.jsa.vgc.auxiliars.Evento
{
    public Notificacion(String id, String nombre, String lugar, String descripcion,String hora_inicio, String hora_fin)
    {
        this.id = id;
        this.nombre = nombre;
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
    }

    @Override
    public String toString()
    {
        String message;
        message = "-- EVENTO " + nombre + " --\n";
        message += "Descripcion: " + descripcion + "\nLugar: " + lugar + ", Hora inicio: " + hora_inicio
                + ", Hora fin: " + hora_fin + ".\n";
        return message;
    }
}
