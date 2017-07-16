package com.uottawa.gradetogo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class EditACourse extends AppCompatActivity {

    // variable d'instances
    private ArrayAdapter<String> adapter;
    Button mShowDialog;
    private ListView list;
    private int positionSemestre;
    private int positionCours;
    private Course currentCourse;
    //variables
    private int numQuiz;
    private int numMidterm;
    private int numProject;
    private int numOralPresentation;
    private int numFinalExamen;
    private int numLaboratory;
    private int numOther;
    private ArrayList<String> evaluationsName;
    private ArrayList<String> grades;
    private ArrayList<Double> ponderations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_acourse);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //prendre info necessaire
        Intent intent = getIntent();
        positionSemestre = Integer.parseInt(intent.getStringExtra("semesterPosition"));
        positionCours = Integer.parseInt(intent.getStringExtra("coursPosition"));

        //cours qui sera mofifie


        Semester currentSemestre = Singleton.getSingleton().getSemesters().get(positionSemestre);
        currentCourse = currentSemestre.getCourse().get(positionCours);
        //variables
        numQuiz =currentCourse.getNumQuiz();
        numMidterm =currentCourse.getNumMidterm();
        numProject=currentCourse.getNumProject();
        numOralPresentation=currentCourse.getNumOral();
        numFinalExamen=currentCourse.getNumFinal();
        numLaboratory=currentCourse.getNumLaboratory();
        numOther=currentCourse.getNumOther();
        evaluationsName = currentCourse.getEvaluationsName();
        grades =currentCourse.getGrades();
        ponderations =currentCourse.getPonderations();

        //populate the activity with the information
        //set picture
        ImageView img = (ImageView) findViewById(R.id.imageViewEditCourse);
        img.setImageURI(currentCourse.getIconId());

        //set name
        EditText name = (EditText) findViewById(R.id.course_edit_name);
        name.setText(currentCourse.getName());

        //set the goal
        EditText goal = (EditText) findViewById(R.id.course_edit_goal);
        goal.setText(currentCourse.getGoal());

        //populate the listView
        populateListView();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mShowDialog = (Button) findViewById(R.id.btn_edit_evaluation);

        mShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(EditACourse.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_spinner, null);
                mBuilder.setTitle("Select the number of evaluation:");
                //les 7 spinners
                final Spinner mSpinner = (Spinner) mView.findViewById(R.id.spinner_add_evaluation);
                final Spinner mSpinner2 = (Spinner) mView.findViewById(R.id.spinner_add_evaluation2);
                final Spinner mSpinner3 = (Spinner) mView.findViewById(R.id.spinner_add_evaluation3);
                final Spinner mSpinner4 = (Spinner) mView.findViewById(R.id.spinner_add_evaluation4);
                final Spinner mSpinner5 = (Spinner) mView.findViewById(R.id.spinner_add_evaluation5);
                final Spinner mSpinner6 = (Spinner) mView.findViewById(R.id.spinner_add_evaluation6);
                final Spinner mSpinner7 = (Spinner) mView.findViewById(R.id.spinner_add_evaluation7);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditACourse.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.evaluationNUmber));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(adapter);
                mSpinner2.setAdapter(adapter);
                mSpinner3.setAdapter(adapter);
                mSpinner4.setAdapter(adapter);
                mSpinner5.setAdapter(adapter);
                mSpinner6.setAdapter(adapter);
                mSpinner7.setAdapter(adapter);

                //set the default according to value
                mSpinner.setSelection(numQuiz);
                mSpinner2.setSelection(numMidterm);
                mSpinner3.setSelection(numProject);
                mSpinner4.setSelection(numOralPresentation);
                mSpinner5.setSelection(numFinalExamen);
                mSpinner6.setSelection(numLaboratory);
                mSpinner7.setSelection(numOther);



                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //savoir combien il y a de chacun des evaluations
                        int nouveauNumQuiz = Integer.parseInt(mSpinner.getSelectedItem().toString())-numQuiz;
                        int nouveauNumMidterm = Integer.parseInt(mSpinner2.getSelectedItem().toString())-numMidterm;
                        int nouveauNumProject = Integer.parseInt(mSpinner3.getSelectedItem().toString())-numProject;
                        int nonveauOralPresentation = Integer.parseInt(mSpinner4.getSelectedItem().toString())-numOralPresentation;
                        int nouveauNumFinalExamen = Integer.parseInt(mSpinner5.getSelectedItem().toString())-numFinalExamen;
                        int nouveauNumLaboratory = Integer.parseInt(mSpinner6.getSelectedItem().toString())-numLaboratory;
                        int nouveauNumOther = Integer.parseInt(mSpinner7.getSelectedItem().toString())-numOther;




                        //populate the listview
                        populateListView();


                    }

                });

                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }

                });


                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();

                dialog.show();
                Button bq = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                bq.setTextColor(Color.parseColor("#044175"));
                Button bn = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                bn.setTextColor(Color.parseColor("#044175"));


            }
        });
    }



    private void populateListView() {

        adapter = new MyListAdapter();
        list = (ListView) findViewById(R.id.ListViewEditCourse);
        list.setAdapter(adapter);
        Utils.setListViewHeightBasedOnChildren(list);

    }

    private class MyListAdapter extends ArrayAdapter<String> {

        public MyListAdapter() {
            super(EditACourse.this, R.layout.list_edit_course, evaluationsName);
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public
        @NonNull
        View getView(int position, View convertView, @NonNull ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.list_edit_course, parent, false);
            }


            // Make name of the evaluation
            TextView nameText = (TextView) itemView.findViewById(R.id.txt_name__edit_evaluation);
            nameText.setText(evaluationsName.get(position));


            //populate the weight
            EditText ponderationText = (EditText) itemView.findViewById(R.id.txt_ponderation_evaluation_edit);
            ponderationText.setText(ponderations.get(position) +"");

            //populate the grade
            EditText gradeText = (EditText) itemView.findViewById(R.id.txt_grade_evaluation_edit);
            if(!currentCourse.getGrades().get(position).equals("N/A")){
            gradeText.setText(grades.get(position) +"");}

            return itemView;


        }


    }
}


