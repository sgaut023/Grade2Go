package com.uottawa.gradetogo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class AddSemester extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_semester);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button button = (Button) findViewById(R.id.btn_save_semester);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //take the information from the spinner

                Spinner season_spinner = (Spinner) findViewById(R.id.spinner_season);
                String season = (String) season_spinner.getSelectedItem();

                Spinner year_spinner = (Spinner) findViewById(R.id.spinner_year);
                String year = (String) year_spinner.getSelectedItem();

                //ajouter le semestre au singleton
                Semester currentSemester = new Semester(season, year);
                if(Singleton.getSingleton().isSemesterExisting(currentSemester)) {
                    Singleton.getSingleton().addSemester(currentSemester);
                    //afficher que le semestre a ete sauver

                    Toast.makeText( AddSemester.this, "Semester was saved successfully" , Toast.LENGTH_LONG).show();
                    Intent a = new Intent(AddSemester.this, MainActivity.class);
                    startActivity(a);
                }else{

                    Snackbar snackbar = Snackbar
                            .make(v, "The semester already exists." , Snackbar.LENGTH_LONG);

                    snackbar.show();

                }




            }});}




    }


