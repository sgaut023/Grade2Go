package com.uottawa.gradetogo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;



public class Settings extends AppCompatActivity {
    public Button save_btn ;
    public Spinner spn_univ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        save_btn = (Button) findViewById(R.id.btn_save_setting);
        spn_univ = (Spinner) findViewById(R.id.spin_uni);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (spn_univ.getSelectedItem().toString()){
                    case "University of Ottawa" :
                        Singleton.getSingleton().setUniversity(0);
                        break;
                    case "Carelton University" :
                        Singleton.getSingleton().setUniversity(1);
                        break;
                    case "Université du Québec en Outaouais" :
                        Singleton.getSingleton().setUniversity(2);
                        break;
                }

            }

        });


    }


}
