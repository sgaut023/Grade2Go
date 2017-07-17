package com.uottawa.gradetogo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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
import android.widget.Toast;

import java.util.ArrayList;

import static android.support.design.widget.Snackbar.make;

public class AddCourse extends AppCompatActivity {
    Button mShowDialog;
    private ArrayAdapter<String> adapter;
    private ListView list;

    //variables
    private int numQuiz;
    private int numMidterm;
    private int numProject;
    private int numOralPresentation;
    private int numFinalExamen;
    private int numLaboratory;
    private int numOther;
    private EditText editText;
    private ArrayList<String> ponderation;

    //info a propos du cours
    private String NameCourse;
    private double goal;
    private ArrayList<String> evaluationsName;
    private int semesterPosition;
    private Uri picture;
    private Semester currentSemester;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //il ny a pas encore d'evaluation
        numQuiz = 0;
        numMidterm = 0;
        numProject = 0;
        numOralPresentation = 0;
        numFinalExamen = 0;
        numLaboratory = 0;
        numOther = 0;
        evaluationsName = new ArrayList<String>();

        // pass the semester info
        Intent intent = getIntent();
        semesterPosition = Integer.parseInt(intent.getStringExtra("semestrePosition"));
        currentSemester = Singleton.getSingleton().getSemesters().get(semesterPosition);
        picture = Singleton.getSingleton().getPhoto(currentSemester.getNumCourse());



        //set picture
        ImageView img= (ImageView) findViewById(R.id.imageViewAddCourse);
        img.setImageURI(picture);

        final Button button_save = (Button) findViewById(R.id.btn_save_semester);
        button_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickOnSave(v);
            }

        });


        mShowDialog = (Button) findViewById(R.id.btn_add_evaluation);

        mShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AddCourse.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_spinner, null);
                mBuilder.setTitle("Select the number of evaluations:");
                //les 7 spinners
                final Spinner mSpinner = (Spinner) mView.findViewById(R.id.spinner_add_evaluation);
                final Spinner mSpinner2 = (Spinner) mView.findViewById(R.id.spinner_add_evaluation2);
                final Spinner mSpinner3 = (Spinner) mView.findViewById(R.id.spinner_add_evaluation3);
                final Spinner mSpinner4 = (Spinner) mView.findViewById(R.id.spinner_add_evaluation4);
                final Spinner mSpinner5 = (Spinner) mView.findViewById(R.id.spinner_add_evaluation5);
                final Spinner mSpinner6 = (Spinner) mView.findViewById(R.id.spinner_add_evaluation6);
                final Spinner mSpinner7 = (Spinner) mView.findViewById(R.id.spinner_add_evaluation7);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddCourse.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.evaluationNUmber));
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

                        //Toast.makeText(AddCourse.this, numMidterm + "", Toast.LENGTH_LONG).show();

                        evaluationsName = new ArrayList<String>();

                        //mettre les noms des evaluations dans un tableau
                        for (int j = 0; j < numQuiz; j++) {
                            evaluationsName.add("Quiz " + (j + 1) + ":");
                        }

                        for (int j = 0; j < numMidterm; j++) {
                            evaluationsName.add("Midterm " + (j + 1) + ":");
                        }

                        for (int j = 0; j < numProject; j++) {
                            evaluationsName.add("Project " + (j + 1) + ":");
                        }

                        for (int j = 0; j < numOralPresentation; j++) {
                            evaluationsName.add("Oral " + (j + 1) + ":");
                        }

                        for (int j = 0; j < numFinalExamen; j++) {
                            evaluationsName.add("Final Exam " + (j + 1) + ":");
                        }

                        for (int j = 0; j < numLaboratory; j++) {
                            evaluationsName.add("Laboratory " + (j + 1) + ":");
                        }

                        for (int j = 0; j < numOther; j++) {
                            evaluationsName.add("Other " + (j + 1) + ":");
                        }

                        if(numQuiz!=0 || numLaboratory!=0 || numProject!=0 ||numFinalExamen!=0 || numOralPresentation!=0 ||numMidterm!=0 ||numOther!=0){
                            //populate the text to give indication
                            TextView indicationText = (TextView)findViewById(R.id.txt_evaluations_order);
                            indicationText.setText("Enter the weighting for each evaluation: ");
                        }else{
                            //populate the text to give indication
                            TextView indicationText = (TextView)findViewById(R.id.txt_evaluations_order);
                            indicationText.setText("");

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
        EditText textName = (EditText) findViewById(R.id.course_add_name);
        NameCourse = textName.getText().toString();

        //condition pour ne pas avoir un nom vide
        if (NameCourse.length() == 0) {// si l'utilisateur n'entre pas de texte, on doit ecrire un message derreur
            Snackbar snackbar =
                    make(v, "You must enter a name for the course.", Snackbar.LENGTH_LONG);

            snackbar.show();
            return;
        }

        //prendre le texte pour le but de letudiant

        //prendre le texte pour le nom du cours
        EditText textGoal = (EditText) findViewById(R.id.course_add_goal);
        String goalString = (textGoal.getText().toString());

        if (goalString.isEmpty()) {
            Snackbar snackbar =
                    make(v, "You must enter a goal for the course.", Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }
        //condition pour avoir un goal entre 0 et 100
        else {
            goal = Double.parseDouble(goalString);

            if (goal < 0) {
                Snackbar snackbar =
                        make(v, "The objective must be higher or equal than 0%.", Snackbar.LENGTH_LONG);
                snackbar.show();
                return;
            } else if (goal > 100) {
                Snackbar snackbar =
                        make(v, "The objective must be less or equal than 100%.", Snackbar.LENGTH_LONG);
                snackbar.show();
                return;
            }
        }
        //user muster enter evaluations
        if (numQuiz == 0 && numLaboratory == 0 && numProject == 0 && numFinalExamen == 0 && numOralPresentation == 0 && numMidterm == 0 && numOther == 0) {
            Snackbar snackbar =
                    make(v, "The objective must be less or equal than 100%.", Snackbar.LENGTH_LONG);
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
                et = (EditText) list.getChildAt(i).findViewById(R.id.txt_ponderation_evaluation);
                if (et != null) {
                    result.add(String.valueOf(et.getText()));
                    if(String.valueOf(et.getText()).isEmpty()) {
                            Snackbar snackbar = make(v, "You must enter a weight for each evaluation.", Snackbar.LENGTH_LONG);
                            snackbar.show();
                            return;
                    }
                    //le total de la ponderation
                    if (!String.valueOf(et.getText()).isEmpty()) {
                        total = total + Double.parseDouble(String.valueOf(et.getText()));
                        if (Double.parseDouble(String.valueOf(et.getText()))>100 ||
                                Double.parseDouble(String.valueOf(et.getText()))<=0){
                            Snackbar snackbar = make(v, "Each weight mush be higher than 0% and lower or equal than 100% ", Snackbar.LENGTH_LONG);
                            snackbar.show();
                            return;
                        }

                    }
                }
            }
            if (result.size() != evaluationsName.size()) {

                Snackbar snackbar =
                        make(v, "You must enter a weighting for each evaluation", Snackbar.LENGTH_LONG);
                snackbar.show();
                return;
            } else if (total != 100) {
                Snackbar snackbar =
                        make(v, "The total of all weights must be 100%. Currently, the total is " + total + "%.", Snackbar.LENGTH_LONG);
                snackbar.show();
                return;

            } else {

                //sauver le cours
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


                Toast.makeText(AddCourse.this, "The course was successfully saved.", Toast.LENGTH_LONG).show();
                Intent i = new Intent(AddCourse.this, Courses.class);

                //retour page cours
                i.putExtra("semesterSeason", currentSemester.getSeason());
                i.putExtra("semesterYear", currentSemester.getYear());
                i.putExtra("Position", semesterPosition + "");
                Toast.makeText( AddCourse.this, "Size course"+Singleton.getSingleton().getSemesters().get(semesterPosition).getCourse().size(), Toast.LENGTH_LONG).show();
                //  start the activity
                startActivity(i);


            }
        }
    }



    private void populateListView() {

        adapter = new MyListAdapter();
        list = (ListView) findViewById(R.id.ListViewAddCourse);
        ponderation = new ArrayList<String>();
        list.setAdapter(adapter);
        Utils.setListViewHeightBasedOnChildren(list);
    }


    //adapter de ma list View
    private class MyListAdapter extends ArrayAdapter<String> {

        public MyListAdapter() {
            super(AddCourse.this, R.layout.list_evaluation_add_course, evaluationsName);
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public
        @NonNull
        View getView(int position, View convertView, @NonNull ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.list_evaluation_add_course, parent, false);
            }


            //find the semester
            String currentEvaluation = evaluationsName.get(position);
            final int pos=position;


            // Make name Text
            TextView nameText = (TextView) itemView.findViewById(R.id.txt_name_evaluation);
            nameText.setText(currentEvaluation);


            return itemView;
            }


        }}




