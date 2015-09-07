package com.jsadevtech.vgc.servicetest;

/**
 * Created by shirkam on 16/06/2015.
 */
public class Evento
{
    protected String id;
    protected String nombre;
    protected String lugar;
    private String descripcion;
    private String persona_destacada;
    protected String hora_inicio;
    protected String hora_fin;

    public Evento()
    {
        this.id = "";
        this.nombre = "";
        this.lugar = "";
        this.descripcion = "";
        this.persona_destacada = "";
        this.hora_inicio = "";
        this.hora_fin = "";
    }

    public Evento(String id, String nombre, String lugar, String desc,String persona, String hora_inicio, String hora_fin)
    {
        this.id = id;
        this.nombre = nombre;
        this.lugar = lugar;
        this.descripcion = desc;
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
    public String getDescripcion()
    {return descripcion;}
    public String getPersona_destacada()
    {return persona_destacada;}
    public String getHora_inicio()
    {return hora_inicio;}
    public String getHora_fin()
    {return hora_fin;}
}
