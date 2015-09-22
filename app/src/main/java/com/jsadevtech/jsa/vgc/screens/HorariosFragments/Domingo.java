package com.jsadevtech.jsa.vgc.screens.HorariosFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jsadevtech.jsa.vgc.R;

/**
 * Created by shirkam on 22/09/2015.
 */
public class Domingo extends Fragment {

    // newInstance constructor for creating fragment with arguments
    public static Domingo newInstance() {
        Domingo domingo = new Domingo();
        return domingo;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_domingo_screen, container, false);
        TextView tvLabel = (TextView) view.findViewById(R.id.textDomingo);
        tvLabel.setText("Texto de prueba en domingo.");
        return view;
    }
}
