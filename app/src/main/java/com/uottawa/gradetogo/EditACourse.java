package com.uottawa.gradetogo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class EditACourse extends AppCompatActivity {

    private int positionSemestre;
    private int positionCours;
    private Course currentCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_acourse);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //prendre info necessaire
        Intent intent = getIntent();
        positionSemestre =  Integer.parseInt(intent.getStringExtra("semesterPosition"));
        positionSemestre =  Integer.parseInt(intent.getStringExtra("coursPosition"));

        //cours qui sera mofifie
        currentCourse = Singleton.getSingleton().getSemesters().get(positionSemestre).getCourse().get(positionCours);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
