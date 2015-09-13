package com.jsadevtech.jsa.vgc.auxiliars;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by shirkam on 13/09/2015.
 */
public class FileWriter
{
    private Context context;
    private static final String FILE_PATH = "settings.txt";

    public FileWriter(Context con) {
        this.context = con;

        try {
            // Creamos un objeto OutputStreamWriter, que será el que nos permita
            // escribir en el archivo de texto. Si el archivo no existía se creará
            // automáticamente.
            OutputStreamWriter outSWMensaje = new OutputStreamWriter(
                    context.openFileOutput(FILE_PATH, Context.MODE_PRIVATE));
            // Escribimos los 5 tiempos iniciales en el archivo.
            outSWMensaje.write("lastId=0");
            // Cerramos el flujo de escritura del archivo, este paso es obligatorio,
            // de no hacerlo no se podrá acceder posteriormente al archivo.
            outSWMensaje.close();
        } catch (Exception e) {
            Log.e("ERROR CREATING FILE", e.getMessage());
        }
    }

    public void setLastId(int id) {
        try {
            OutputStreamWriter outSWMensaje = new OutputStreamWriter(
                    context.openFileOutput(FILE_PATH, Context.MODE_PRIVATE));

            outSWMensaje.write("lastId="+id);

            outSWMensaje.close();
        } catch (Exception e) {
            Log.e("ERROR CREATING FILE", e.getMessage());
            Toast.makeText(context, "No se pudo crear el archivo de alarmas", Toast.LENGTH_LONG).show();
        }
    }

    private boolean existsFile(String file) {
        for(String tmp: context.fileList()) {
            if (tmp.equals(file))
                return true;
        }
        return false;
    }
}
