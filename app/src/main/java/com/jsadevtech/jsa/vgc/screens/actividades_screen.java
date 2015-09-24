package com.jsadevtech.jsa.vgc.screens;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.jsadevtech.jsa.vgc.R;
import com.jsadevtech.jsa.vgc.auxiliars.ExpandableListAdapter;
import com.jsadevtech.jsa.vgc.auxiliars.Group;

public class actividades_screen extends AppCompatActivity {
    private SparseArray<Group> groups = new SparseArray<>();
    //private final String[] tipoActividades = {"CAMPEONATOS", "CONCURSOS", "LAN PARTY","TORNEOS"};
    //private final String[][] nombreActividades = {{"Magic", "Infinity", "Krosmaster"}, {"Cosplay", "Adivina la cancion"},
    //        {"LoL 5vs5", "Minecraft"}, {"Ultra Street Fighter IV", "Street Fighter II Champion Edition", "Super Smash Bros", "Naruto Suns Revolution",
    //          "Mortal Kombat X", "Torneos Fusion Freak"}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades_screeen);

        setTitle("Actividades");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Button magicButton = (Button)findViewById(R.id.button_magic);
        Button infinityButton = (Button) findViewById(R.id.button_infinity);
        Button krosmasterButton = (Button) findViewById(R.id.button_krosmaster);
        Button cosplayButton = (Button) findViewById(R.id.button_cosplay);
        Button adivinaCacionButton = (Button) findViewById(R.id.button_adivina_cancion);
        Button lolButton = (Button) findViewById(R.id.button_lol_5vs5);
        Button minecraftButton = (Button) findViewById(R.id.button_minecraft);
        Button usfIVButton = (Button) findViewById(R.id.button_USFIV);
        Button sfIIButton = (Button) findViewById(R.id.button_SFII);
        Button ssbButton = (Button) findViewById(R.id.button_SSB);
        Button narutoButton = (Button) findViewById(R.id.button_narutoSunsRevolution);
        Button mkXButton = (Button) findViewById(R.id.button_mkX);
        Button fusionFreakButton = (Button) findViewById(R.id.button_toneosFusionFreak);
        Button portfolioButton = (Button) findViewById(R.id.button_portafolios);
        Button youtubersButton = (Button) findViewById(R.id.button_youtubers);
        Button heroclixButton = (Button) findViewById(R.id.button_heroclix);
        Button diceMasterButton = (Button) findViewById(R.id.button_dice_master);
        Button hearthstoneButton = (Button) findViewById(R.id.button_hearthstone);
        Button fifa15Button = (Button) findViewById(R.id.button_fifa15);

        magicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(actividades_screen.this, webActividades_screen.class);
                i.putExtra("url", parseURL(getString(R.string.campeonatos_magic)));
                i.putExtra("title", getString(R.string.campeonatos_magic));
                startActivity(i);
            }
        });
        magicButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final String magicGif = "http://www.reactiongifs.com/r/mgc.gif";
                Intent i = new Intent(actividades_screen.this, webActividades_screen.class);
                i.putExtra("url", magicGif);
                i.putExtra("title", getString(R.string.campeonatos_magic));
                startActivity(i);
                return true;
            }
        });
        infinityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(actividades_screen.this, webActividades_screen.class);
                i.putExtra("url", parseURL(getString(R.string.campeonatos_infinity)));
                i.putExtra("title", getString(R.string.campeonatos_infinity));
                startActivity(i);
            }
        });
        krosmasterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(actividades_screen.this, webActividades_screen.class);
                i.putExtra("url", parseURL(getString(R.string.campeonatos_krosmaster)));
                i.putExtra("title", getString(R.string.campeonatos_krosmaster));
                startActivity(i);
            }
        });
        cosplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(actividades_screen.this, webActividades_screen.class);
                i.putExtra("url", parseURL(getString(R.string.concursos_cosplay)));
                i.putExtra("title", getString(R.string.concursos_cosplay));
                startActivity(i);
            }
        });
        adivinaCacionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(actividades_screen.this, webActividades_screen.class);
                i.putExtra("url", parseURL(getString(R.string.concursos_adivina_cancion)+ " 2"));
                i.putExtra("title", (getString(R.string.concursos_adivina_cancion)));
                startActivity(i);
            }
        });
        lolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(actividades_screen.this, webActividades_screen.class);
                i.putExtra("url", parseURL(getString(R.string.lan_party_lol5vs5_forURL))); //http://www.vgcomic.com/league-of-legends-5vs5/
                i.putExtra("title", getString(R.string.lan_party_lol5vs5));
                startActivity(i);
            }
        });
        minecraftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(actividades_screen.this, webActividades_screen.class);
                i.putExtra("url", parseURL(getString(R.string.lan_party_minecraft)));
                i.putExtra("title", getString(R.string.lan_party_minecraft));
                startActivity(i);
            }
        });
        usfIVButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(actividades_screen.this, webActividades_screen.class);
                i.putExtra("url", parseURL(getString(R.string.torneos_USFIV)));
                i.putExtra("title", getString(R.string.torneos_USFIV));
                startActivity(i);
            }
        });
        sfIIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(actividades_screen.this, webActividades_screen.class);
                i.putExtra("url", "http://www.vgcomic.com/street-figther-ii-champion-edition/");
                i.putExtra("title", getString(R.string.torneos_SFII));
                startActivity(i);
            }
        });
        ssbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(actividades_screen.this, webActividades_screen.class);
                i.putExtra("url", parseURL(getString(R.string.torneos_SSB)));
                i.putExtra("title", getString(R.string.torneos_SSB));
                startActivity(i);
            }
        });
        narutoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(actividades_screen.this, webActividades_screen.class);
                i.putExtra("url", parseURL(getString(R.string.torneos_narutoSunsRevolution)));
                i.putExtra("title", getString(R.string.torneos_narutoSunsRevolution));
                startActivity(i);
            }
        });
        mkXButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(actividades_screen.this, webActividades_screen.class);
                i.putExtra("url", parseURL(getString(R.string.torneos_mkX)));
                i.putExtra("title", getString(R.string.torneos_mkX));
                startActivity(i);
            }
        });
        fusionFreakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(actividades_screen.this, webActividades_screen.class);
                i.putExtra("url", parseURL(getString(R.string.torneos_fusion_freak)));
                i.putExtra("title", getString(R.string.torneos_fusion_freak));
                startActivity(i);
            }
        });
        portfolioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(actividades_screen.this, webActividades_screen.class);
                i.putExtra("url", parseURL(getString(R.string.revision_portafolios)));
                i.putExtra("title", getString(R.string.revision_portafolios));
                startActivity(i);
            }
        });
        youtubersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(actividades_screen.this, webActividades_screen.class);
                i.putExtra("url", parseURL(getString(R.string.youtubers)));
                i.putExtra("title", getString(R.string.youtubers));
                startActivity(i);
            }
        });
        heroclixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(actividades_screen.this, webActividades_screen.class);
                i.putExtra("url", parseURL(getString(R.string.campeonatos_heroclix)));
                i.putExtra("title", getString(R.string.campeonatos_heroclix));
                startActivity(i);
            }
        });
        diceMasterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(actividades_screen.this, webActividades_screen.class);
                i.putExtra("url", parseURL(getString(R.string.campeonatos_dice_master)));
                i.putExtra("title", getString(R.string.campeonatos_dice_master));
                startActivity(i);
            }
        });
        hearthstoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(actividades_screen.this, webActividades_screen.class);
                i.putExtra("url", parseURL(getString(R.string.torneos_hearthstone)));
                i.putExtra("title", getString(R.string.torneos_hearthstone));
                startActivity(i);
            }
        });
        fifa15Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(actividades_screen.this, webActividades_screen.class);
                i.putExtra("url", parseURL(getString(R.string.torneos_fifa15)));
                i.putExtra("title", getString(R.string.torneos_fifa15));
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actividades_screeen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_colaboradores) {
            startActivity(new Intent(actividades_screen.this, informacion.class));
            return true;
        }
        if(id == R.id.action_zonas) {
            startActivity(new Intent(actividades_screen.this, zonas_screen.class));
            return true;
        }
        if(id == R.id.action_about) {
            startActivity(new Intent(actividades_screen.this, about_screen.class));
            return true;
        }
        if(id == R.id.action_principal) {
            startActivity(new Intent(actividades_screen.this, main_screen.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String parseURL(String activity)
    {
        String url = "http://www.vgcomic.com/";
        String[] partes;

        partes = activity.toLowerCase().split(" ");

        for(int i=0; i<partes.length; i++)
        {
            if(i == partes.length-1)
                url += partes[i]+"/";
            else
                url += (partes[i]+"-");
        }

        return url;
    }
}