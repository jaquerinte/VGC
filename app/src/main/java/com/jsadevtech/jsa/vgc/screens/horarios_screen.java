package com.jsadevtech.jsa.vgc.screens;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;

import com.jsadevtech.jsa.vgc.R;
import com.jsadevtech.jsa.vgc.screens.HorariosFragments.Domingo;
import com.jsadevtech.jsa.vgc.screens.HorariosFragments.Firmas;
import com.jsadevtech.jsa.vgc.screens.HorariosFragments.Sabado;

public class horarios_screen extends AppCompatActivity {
    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios_screen);

        setTitle("Horarios");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
    }


    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return Sabado.newInstance();
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return Domingo.newInstance();
                case 2:
                    return Firmas.newInstance();
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0:
                    return "Sabado";
                case 1:
                    return "Domingo";
                case 2:
                    return "Firmas";
                default:
                    return "404: NOT FOUND";
            }
        }

    }

}
