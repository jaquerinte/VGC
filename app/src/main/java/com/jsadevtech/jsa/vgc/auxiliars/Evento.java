package com.jsadevtech.jsa.vgc.auxiliars;

/**
 * Created by shirkam on 16/06/2015.
 */
public class Evento
{
    private String id;
    private String nombre;
    private String lugar;
    private String persona_destacada;
    private String hora_inicio;
    private String hora_fin;


    public Evento(String id, String nombre, String lugar, String persona, String hora_inicio, String hora_fin)
    {
        this.id = id;
        this.nombre = nombre;
        this.lugar = lugar;
        this.persona_destacada = persona;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
    }

    public String toString()
    {
        String message;
        message = "-- EVENTO " + nombre + " --\n";
        message += "Lugar: " + lugar + ", Persona destacada: " + persona_destacada + ", Hora inicio: " + hora_inicio
                + ", Hora fin: " + hora_fin + "\n";
        return message;
    }

    public String getId()
    {return id;}
    public String getNombre()
    {return nombre;}
    public String getLugar()
    {return lugar;}
    public String getPersona_destacada()
    {return persona_destacada;}
    public String getHora_inicio()
    {return hora_inicio;}
    public String getHora_fin()
    {return hora_fin;}
}
