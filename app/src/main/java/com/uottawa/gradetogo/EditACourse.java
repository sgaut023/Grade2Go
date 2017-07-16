package com.uottawa.gradetogo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
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
    private double goal;
    private ListView list;
    private int positionSemestre;
    private int positionCours;
    private Course currentCourse;
    //variables
    private int numQuiz;
    private String NameCourse;
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
        evaluationsName = currentCourse.getEvaluationsName();
        grades =currentCourse.getGrades();
        ponderations =currentCourse.getPonderations();
        populateListView();

        final Button button_save = (Button) findViewById(R.id.btn_editsave_semester);
        button_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickOnSave(v);
            }

        });

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
                        numQuiz = Integer.parseInt(mSpinner.getSelectedItem().toString());
                        numMidterm = Integer.parseInt(mSpinner2.getSelectedItem().toString());
                        numProject = Integer.parseInt(mSpinner3.getSelectedItem().toString());
                        numOralPresentation = Integer.parseInt(mSpinner4.getSelectedItem().toString());
                        numFinalExamen = Integer.parseInt(mSpinner5.getSelectedItem().toString());
                        numLaboratory = Integer.parseInt(mSpinner6.getSelectedItem().toString());
                        numOther = Integer.parseInt(mSpinner7.getSelectedItem().toString());

                        evaluationsName = new ArrayList<String>();
                        grades = new ArrayList<String>();
                        ponderations = new ArrayList<Double>();

                        //mettre les noms des evaluations dans un tableau
                        for (int j = 0; j < numQuiz; j++) {
                            evaluationsName.add("Quiz " + (j + 1) + ":");
                            grades.add("N/A");
                            ponderations.add(0.0);
                        }

                        for (int j = 0; j < numMidterm; j++) {
                            evaluationsName.add("Midterm " + (j + 1) + ":");
                            grades.add("N/A");
                            ponderations.add(0.0);
                        }

                        for (int j = 0; j < numProject; j++) {
                            evaluationsName.add("Project " + (j + 1) + ":");
                            grades.add("N/A");
                            ponderations.add(0.0);
                        }

                        for (int j = 0; j < numOralPresentation; j++) {
                            evaluationsName.add("Oral Presentation " + (j + 1) + ":");
                            grades.add("N/A");
                            ponderations.add(0.0);
                        }

                        for (int j = 0; j < numFinalExamen; j++) {
                            evaluationsName.add("Final Exam " + (j + 1) + ":");
                            grades.add("N/A");
                            ponderations.add(0.0);
                        }

                        for (int j = 0; j < numLaboratory; j++) {
                            evaluationsName.add("Laboratory " + (j + 1) + ":");
                            grades.add("N/A");
                            ponderations.add(0.0);
                        }

                        for (int j = 0; j < numOther; j++) {
                            evaluationsName.add("Other " + (j + 1) + ":");
                            grades.add("N/A");
                            ponderations.add(0.0);
                        }



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

    public void clickOnSave(View v) {

        //prendre le texte pour le nom du cours
        EditText textName = (EditText) findViewById(R.id.course_edit_name);
        NameCourse = textName.getText().toString();

        //condition pour ne pas avoir un nom vide
        if (NameCourse.length() == 0) {// si l'utilisateur n'entre pas de texte, on doit ecrire un message derreur
            Snackbar snackbar = Snackbar
                    .make(v, "You must enter a name for the course.", Snackbar.LENGTH_LONG);

            snackbar.show();
            return;
        }

        //prendre le texte pour le but de letudiant

        //prendre le texte pour le nom du cours
        EditText textGoal = (EditText) findViewById(R.id.course_edit_goal);
        String goalString = (textGoal.getText().toString());


        if (goalString.isEmpty()) {
            Snackbar snackbar = Snackbar
                    .make(v, "You must enter a goal for the course.", Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }
        //condition pour avoir un goal entre 0 et 100
        else {
            goal = Double.parseDouble(goalString);

            if (goal <= 0) {
                Snackbar snackbar = Snackbar
                        .make(v, "The goal must be higher than 0%.", Snackbar.LENGTH_LONG);
                snackbar.show();
                return;
            } else if (goal > 100) {
                Snackbar snackbar = Snackbar
                        .make(v, "The goal must be lower than 100%.", Snackbar.LENGTH_LONG);
                snackbar.show();
                return;
            }
        }
        //user muster enter evaluations
        if (numQuiz == 0 && numLaboratory == 0 && numProject == 0 && numFinalExamen == 0 && numOralPresentation == 0 && numMidterm == 0 && numOther == 0) {
            Snackbar snackbar = Snackbar
                    .make(v, "You must at least add one evaluation for this course.", Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        } else {

            /** get all values of the EditText-Fields
             find your ListView local variable you had created for example :
             private ListView listview;
             You also need to catch null value of EditText first. */
            ArrayList<String> result = new ArrayList<String>();
            EditText et;
            double total = 0;

            for (int i = 0; i < list.getCount(); i++) {
                et = (EditText) list.getChildAt(i).findViewById(R.id.txt_ponderation_evaluation_edit);
                if (et != null) {
                    result.add(String.valueOf(et.getText()));
                    //le total de la ponderation
                    if (!String.valueOf(et.getText()).isEmpty()) {
                        total = total + Double.parseDouble(String.valueOf(et.getText()));

                    }
                }
            }
            ArrayList<String> result2 = new ArrayList<String>();
            EditText et2;

            for (int i = 0; i < list.getCount(); i++) {
                et2 = (EditText) list.getChildAt(i).findViewById(R.id.txt_ponderation_evaluation_edit);
                if (et2 != null) {
                    if (Double.parseDouble(String.valueOf(et2.getText()))>0){
                        Snackbar snackbar = Snackbar
                                .make(v, "A grade must be higher that 0%", Snackbar.LENGTH_LONG);
                        snackbar.show();

                    }else result2.add(String.valueOf(et2.getText()));
                }}



            if (result.size() != evaluationsName.size()) {

                Snackbar snackbar = Snackbar
                        .make(v, "You must enter a weighting for each evaluation", Snackbar.LENGTH_LONG);
                snackbar.show();
                return;
            } else if (total != 100) {
                Snackbar snackbar = Snackbar
                        .make(v, "The total of all weights must be 100%. Currently, the total is " + (int) total + " %.", Snackbar.LENGTH_LONG);
                snackbar.show();
                return;

            } else {

              /*  //sauver le cours
                Course currentCourse = new Course(NameCourse, Double.toString(goal), picture);
                currentSemester.addCourse(currentCourse);

                for (int j = 0; j < evaluationsName.size(); j++) {
                    String currentName = evaluationsName.get(j);
                    Double currentPonderation = Double.parseDouble(result.get(j));

                    if (currentName.contains("Quiz")) {
                        currentCourse.getQuizzes().add("N/A");
                        currentCourse.getQuizzesPonderation().add(currentPonderation);
                    } else if (currentName.contains("Midterm")) {
                        currentCourse.getMidterms().add("N/A");
                        currentCourse.getMidtermsPonderation().add(currentPonderation);
                    } else if (currentName.contains("Project")) {
                        currentCourse.getProjects().add("N/A");
                        currentCourse.getProjectsPonderation().add(currentPonderation);
                    } else if (currentName.contains("Oral")) {
                        currentCourse.getOralPresentations().add("N/A");
                        currentCourse.getOralPresentationsPonderation().add(currentPonderation);
                    } else if (currentName.contains("Final")) {
                        currentCourse.getFinals().add("N/A");
                        currentCourse.getFinalsPonderation().add(currentPonderation);
                    } else if (currentName.contains("Laboratory")) {
                        currentCourse.getLaboratories().add("N/A");
                        currentCourse.getLaboratoriesPonderation().add(currentPonderation);
                    } else if (currentName.contains("Other")) {
                        currentCourse.getOthers().add("N/A");
                        currentCourse.getOthersPonderation().add(currentPonderation);
                    }
                }


                Toast.makeText(AddCourse.this, "The course was saved successfully", Toast.LENGTH_LONG).show();
                Intent i = new Intent(AddCourse.this, Courses.class);

                //retour page cours
                i.putExtra("semesterSeason", currentSemester.getSeason());
                i.putExtra("semesterYear", currentSemester.getYear());
                i.putExtra("Position", semesterPosition + "");
                //Toast.makeText( MainActivity.this, ""+position , Toast.LENGTH_LONG).show();
                //  start the activity
                startActivity(i);*/


            }
        }
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
            if(ponderations.get(position)!= 0){
            ponderationText.setText(ponderations.get(position) +"");}

            //populate the grade
            EditText gradeText = (EditText) itemView.findViewById(R.id.txt_grade_evaluation_edit);
            if(!grades.get(position).equals("N/A")){
            gradeText.setText(grades.get(position) +"");}

            return itemView;


        }


    }
}


