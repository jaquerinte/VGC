package com.jsadevtech.jsa.vgc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class InvitadosAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> values;

    public InvitadosAdapter(Context context, ArrayList<String> values) {
        super(context, R.layout.activity_invitados_screen, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_invitados_screen, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.invitadoTipo);
        ListView listView = (ListView) rowView.findViewById(R.id.invitadosList);

        textView.setText(values.get(position));

        ArrayList<Invitado> invitados;
        ArrayList<String> invitadosNombres = new ArrayList<>();
        try {
            invitados = InvitadoBD.getInvitadosByTipo(values.get(position));

            for(int i=0; i<invitados.size(); i++)
            {
                invitadosNombres.add(invitados.get(i).getNombre());
            }
        }
        catch(Exception e)
        { invitadosNombres.add(""); }

        ArrayAdapter<String> nombresAdapter = new ArrayAdapter<String>(context,
                R.id.invitadosList, invitadosNombres);

        listView.setAdapter(nombresAdapter);



        return rowView;
    }
}