package com.jsadevtech.jsa.vgc.screens.HorariosFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jsadevtech.jsa.vgc.R;

/**
 * Created by shirkam on 22/09/2015.
 */
public class Sabado extends Fragment {

    // newInstance constructor for creating fragment with arguments
    public static Sabado newInstance() {
        Sabado domingo = new Sabado();
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
        View view = inflater.inflate(R.layout.fragment_sabado_screen, container, false);
        return view;
    }
}
