package com.jsadevtech.jsa.vgc;

/**
 * Created by shirkam on 19/06/2015.
 */
public class Invitado
{
    private String nombre;
    private String telefono;
    private String email;
    private String inform;
    private String anotaciones;
    private String tipo;

    public Invitado(String nombre, String tlf, String email, String inform, String anot, String tip)
    {
        this.nombre = nombre;
        this.telefono = tlf;
        this.email = email;
        this.inform = inform;
        this.anotaciones = anot;
        this.tipo = tip;
    }

    public String toString()
    {
        return "Tipo: " + this.tipo + "\nNombre: " + this.nombre + "\t\nInformacion: " + this.inform;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getInform() { return inform; }

    public String getAnotaciones() {
        return anotaciones;
    }

    public String getTipo() { return tipo; }
}
