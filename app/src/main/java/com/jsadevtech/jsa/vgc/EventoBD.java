package com.jsadevtech.jsa.vgc;

import com.jsadevtech.jsa.vgc.exceptions.ConnectionFailedException;
import com.jsadevtech.jsa.vgc.exceptions.CouldNotGetInformationException;
import com.jsadevtech.jsa.vgc.exceptions.CouldNotConvertFormatException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;

/**
 * Clase de conexion con la base de datos.
 * Devuelve todos los eventos que se encuentren en la bd
 * Created by shirkam on 11/06/2015.
 */
public class EventoBD
{
    private static final String phpFile = "http://jsadevtech.site40.net/getEventos.php";
    private static final String phpFileFromFecha = "http://jsadevtech.site40.net/getEventosFromFecha.php?argument1=";

    public EventoBD()
    {}

    public static Vector<Evento> getAllEventos() throws ConnectionFailedException, CouldNotConvertFormatException, CouldNotGetInformationException
    {
        Vector<Evento> resultado;
        try
        {
            resultado = getDatos(phpFile);
            return resultado;
        }
        catch(ConnectionFailedException ex)
        { throw new ConnectionFailedException(); }
        catch(CouldNotConvertFormatException ex)
        { throw new CouldNotConvertFormatException(); }
        catch(CouldNotGetInformationException ex)
        { throw new CouldNotGetInformationException(); }
    }

    public static Vector<Evento> getEventosDesdeFecha(String fecha) throws ConnectionFailedException, CouldNotConvertFormatException, CouldNotGetInformationException
    {
        Vector<Evento> resultado;

        try
        {
            resultado = getDatos(phpFileFromFecha+fecha);
            return resultado;
        }
        catch(ConnectionFailedException ex)
        { throw new ConnectionFailedException(); }
        catch(CouldNotConvertFormatException ex)
        { throw new CouldNotConvertFormatException(); }
        catch(CouldNotGetInformationException ex)
        { throw new CouldNotGetInformationException(); }
    }

    private static Vector<Evento> getDatos(String connectionUrl) throws ConnectionFailedException, CouldNotConvertFormatException, CouldNotGetInformationException
    {
        Vector<Evento> resultado = new Vector<>();
        String aux = "";
        InputStream input = null;

        try
        {
            URL url = new URL(connectionUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            input = new BufferedInputStream(connection.getInputStream());
        }
        catch(Exception e)
        { throw new ConnectionFailedException(); }

        //convert response to string
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {

                sb.append(line + "\n");

            }

            input.close();
            aux=sb.toString();
        }
        catch(Exception e)
        { throw new CouldNotConvertFormatException(); }

        //parse json data
        try
        {
            Evento ev;
            JSONArray jArray = new JSONArray(aux);

            for(int i=0; i<jArray.length();i++)
            {
                JSONObject json = jArray.getJSONObject(i);

                ev = new Evento(json.getString("id"), json.getString("nombre"), json.getString("lugar"), json.getString("persona_destacada"),
                        json.getString("fecha_inicio"), json.getString("fecha_fin"));

                resultado.add(ev);
            }
        }
        catch (Exception e)
        { throw new CouldNotGetInformationException(); }

        return resultado;
    }
}
