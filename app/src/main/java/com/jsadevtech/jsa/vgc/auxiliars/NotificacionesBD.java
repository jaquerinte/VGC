package com.jsadevtech.vgc.servicetest;

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
 * Created by shirkam on 05/09/2015.
 */
public class NotificacionesBD
{
    private static final String phpFile = "http://jsadevtech.site40.net/getNotificaciones.php";
    private static final String phpFileFromFecha = "http://jsadevtech.site40.net/getNotificacionesFromFecha.php?argument1=";
    private static final String phpFileFirstFromFecha = "http://jsadevtech.site40.net/getFirstNotificacionFromFecha.php?argument1=";

    public NotificacionesBD(){} //Does Nothing

    public static Vector<Notificacion> getAllNotificaciones() throws ConnectionFailedException, CouldNotConvertFormatException, CouldNotGetInformationException
    {
        Vector<Notificacion> resultado;
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

    public static Vector<Notificacion> getNotificacionesDesdeFecha(String fecha) throws ConnectionFailedException, CouldNotConvertFormatException, CouldNotGetInformationException
    {
        Vector<Notificacion> resultado;

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

    public static Notificacion getFirstNotificacionFromFecha(String fecha) throws ConnectionFailedException, CouldNotConvertFormatException, CouldNotGetInformationException
    {
        Notificacion resultado;

        try
        {
            resultado = getDatos(phpFileFromFecha+fecha).firstElement();
            return resultado;
        }
        catch(ConnectionFailedException ex)
        { throw new ConnectionFailedException(); }
        catch(CouldNotConvertFormatException ex)
        { throw new CouldNotConvertFormatException(); }
        catch(CouldNotGetInformationException ex)
        { throw new CouldNotGetInformationException(); }
    }

    private static Vector<Notificacion> getDatos(String connectionUrl) throws ConnectionFailedException, CouldNotConvertFormatException, CouldNotGetInformationException {
        Vector<Notificacion> resultado = new Vector<>();
        String aux = "";
        InputStream input = null;

        try {
            URL url = new URL(connectionUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            input = new BufferedInputStream(connection.getInputStream());
        } catch (Exception e) {
            throw new ConnectionFailedException();
        }

        //convert response to string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {

                sb.append(line + "\n");

            }

            input.close();
            aux = sb.toString();
        } catch (Exception e) {
            throw new CouldNotConvertFormatException();
        }

        //parse json data
        try {
            Notificacion ev;
            JSONArray jArray = new JSONArray(aux);

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json = jArray.getJSONObject(i);

                ev = new Notificacion(json.getString("id"), json.getString("nombre"), json.getString("lugar"),
                        json.getString("fecha_inicio"), json.getString("fecha_fin"));

                resultado.add(ev);
            }
        } catch (Exception e) {
            throw new CouldNotGetInformationException();
        }

        return resultado;
    }
}
