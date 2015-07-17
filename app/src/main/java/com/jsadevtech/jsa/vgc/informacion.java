package com.jsadevtech.jsa.vgc;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;


public class informacion extends Activity {
    // more efficient than HashMap for mapping integers to objects
    private SparseArray<Group> groups = new SparseArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        //StrictMode.enableDefaults();//modo stricto necesario para la conexion a internet

        createData();
        //Linkamos con el objeto del xml
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.zonas_vgc);
        //Creamos su adaptador de datos
        InvitadosAdapter adapter = new InvitadosAdapter(this,
                groups);
        //Y se lo ponemos
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_informacion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createData()
    {
        String[] pantallas = {"Zona Gamer", "Zona Market", "Zona Hablamos", "Zona Talleres", "Zona Estrategia", "Zona Conciertos"};
        String[] descripciones1 = new String[]{ "Torneos, Zona LAN PARTY y todo lo que necesitas para jugar sin descanso.",
                                    "Espacio donde las mejores tiendas del sector nos ofrecerán sus productos.",
                                    "No te pierdas la oportunidad de estar cerca de tus ídolos.",
                                    "Para que puedas aprender de los profesionales. ¡No te lo pierdas!",
                                    "¡Vente con tus amigos y juega con nosotros!",
                                    "Si te va la música también tendremos para ti.",
                                    };

        String[] descripciones2 = new String[]{ "Aquí podrás descubrir y jugar a las últimas novedades pero también a los clásicos del videojuego. " +
                                            "Además se realizarán distintos torneos de los videojuegos más importantes: " +
                                            "FIFA, Street Fighter, etc… Inscríbete y participa!!",
                                    "Aquí podrás encontrar desde las últimas novedades en videojuegos a los clásicos del cómic, " +
                                            "merchandising, figuras, juguetes, coleccionismo… y mucho más.",
                                    "Conferencias, debates y mesas redondas donde autores, críticos, profesionales del sector y " +
                                            "cualquier aficionado pueda hablar y escuchar sobre los temas que nos interesan, " +
                                            "tratando los mismos con la seriedad y la pasión que se merecen.",
                                    "Espacio dedicado al aprendizaje, donde se realizarán multitud de talleres interesantes: " +
                                            "cómo hacer un cómic, autoedición, manualidades, estructuras de guion, pintura de miniaturas, " +
                                            "creación de escenarios, dibujo, etc.",
                                    "Conoce y prueba las últimas novedades en juegos que acaban de salir al mercado. " +
                                        "También se llevarán a cabo distintos campeonatos de los juegos más importantes: " +
                                        "Magic, Heroclix, Dominion, etc…",
                                    "Para que no tengas ni un minuto de respiro tendremos un escenario principal " +
                                        "donde podrás encontrar música en directo, " +
                                        "dj, conciertos y mucho más!"};


        for(int i=0; i< pantallas.length; i++)
        {
            //Le ponemos la cabecera al grupo
            Group group = new Group(pantallas[i]);

            //Y los datos a mostrar
            group.children.add(descripciones1[i]);
            group.children.add(descripciones2[i]);

            //Por ultimo lo ponemos junto con el resto de grupos.
            groups.append(i, group);
        }

    }
}
