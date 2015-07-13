package com.jsadevtech.jsa.vgc;

import com.jsadevtech.jsa.vgc.exceptions.ConnectionFailedException;
import com.jsadevtech.jsa.vgc.exceptions.CouldNotConvertFormatException;
import com.jsadevtech.jsa.vgc.exceptions.CouldNotGetInformationException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Shirkam on 19/06/2015.
 * Devuelve los invitados pedidos de la base de datos
 */
public class InvitadoBD
{
    private static final String phpFile = "http://jsadevtech.site40.net/getInvitados.php";
    private static final String phpFileTipos = "http://jsadevtech.site40.net/getInvitadosTipos.php";
    private static final String phpFileByTipo = "http://jsadevtech.site40.net/getInvitadosbYTipos.php";

    public InvitadoBD()
    {}

    public static ArrayList<Invitado> getAllInvitados() throws ConnectionFailedException, CouldNotConvertFormatException,
            CouldNotGetInformationException
    {
        ArrayList<Invitado> resultado;
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

    public static ArrayList<String> getTipos() throws ConnectionFailedException, CouldNotConvertFormatException,
            CouldNotGetInformationException
    {
        ArrayList<String> resultado;
        try
        {
            resultado = getTiposAux(phpFileTipos);
            return resultado;
        }
        catch(ConnectionFailedException ex)
        { throw new ConnectionFailedException(); }
        catch(CouldNotConvertFormatException ex)
        { throw new CouldNotConvertFormatException(); }
        catch(CouldNotGetInformationException ex)
        { throw new CouldNotGetInformationException(); }
    }

    public static ArrayList<Invitado> getInvitadosByTipo(String tipo) throws ConnectionFailedException, CouldNotConvertFormatException,
            CouldNotGetInformationException
    {
        ArrayList<Invitado> resultado;
        try
        {
            resultado = getDatos(phpFileByTipo+tipo);
            return resultado;
        }
        catch(ConnectionFailedException ex)
        { throw new ConnectionFailedException(); }
        catch(CouldNotConvertFormatException ex)
        { throw new CouldNotConvertFormatException(); }
        catch(CouldNotGetInformationException ex)
        { throw new CouldNotGetInformationException(); }
    }

    private static ArrayList<Invitado> getDatos(String connectionUrl) throws ConnectionFailedException, CouldNotConvertFormatException,
            CouldNotGetInformationException
    {
        ArrayList<Invitado> resultado = new ArrayList<>();
        String aux;
        InputStream input;

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
            Invitado in;
            JSONArray jArray = new JSONArray(aux);

            for(int i=0; i<jArray.length();i++)
            {
                JSONObject json = jArray.getJSONObject(i);

                in = new Invitado(json.getString("nombre"), json.getString("telefono"), json.getString("email"), json.getString("informacion"),json.getString("anotaciones"),
                    json.getString("tipo"));

                resultado.add(in);
            }
        }
        catch (Exception e)
        { throw new CouldNotGetInformationException(); }

        return resultado;
    }

    private static ArrayList<String> getTiposAux(String connectionUrl) throws ConnectionFailedException, CouldNotConvertFormatException,
            CouldNotGetInformationException
    {
        ArrayList<String> resultado = new ArrayList<>();
        String aux;
        InputStream input;

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
            String in;
            JSONArray jArray = new JSONArray(aux);

            for(int i=0; i<jArray.length();i++)
            {
                JSONObject json = jArray.getJSONObject(i);

                in = json.getString("tipo");

                resultado.add(in);
            }
        }
        catch (Exception e)
        { throw new CouldNotGetInformationException(); }

        return resultado;
    }
}
