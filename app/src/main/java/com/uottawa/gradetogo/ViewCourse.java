package com.uottawa.gradetogo;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ViewCourse extends AppCompatActivity {

    private String gradeLetter;
    private int position;
    private int positionSemestre;
    private String name;
    private ArrayAdapter<Integer> adapter;
    private ListView list;
    private static Context mContext;
    int deletetime = 0;
    Course currentCourse;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //populatelistView
       populateListView();


        // take the course info position
        Intent intent = getIntent();
        position =  Integer.parseInt(intent.getStringExtra("position"));
        positionSemestre =  Integer.parseInt(intent.getStringExtra("semestrePosition"));

        //find the course in question
        currentCourse = Singleton.getSingleton().getSemesters().get(positionSemestre).getCourse().get(position);

        // This is how to change TextView dynamically
        //populate the goal
        TextView goalText = (TextView)findViewById(R.id.txt_goal);
        goalText.setText(currentCourse.getGoal() + " ");

        //populate the grade
        TextView gradeText = (TextView)findViewById(R.id.txt_grade);
        gradeText.setText(currentCourse.getGrade() + " ");

        //populate the goalLetter
        TextView goalLetterText = (TextView)findViewById(R.id.txt_goal_letter);
        goalLetterText.setText(currentCourse.getGoalLetter() + " ");

        //populate the gradeLetter
        TextView gradeLetterText = (TextView)findViewById(R.id.txt_grade_letter);
        gradeLetterText.setText(currentCourse.getGradeLetter() + "");

        //change the name of each recipe
        name= currentCourse.getName();
        TextView courseName = (TextView)findViewById(R.id.txt_name_course);
        courseName.setText(name);

        //change the picture of each recipe
        ImageView img= (ImageView) findViewById(R.id.defaultRecipeImage);
        img.setImageURI(currentCourse.getIconId());



        // ------------------------------------------

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("");
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    private void populateListView() {

        adapter = new MyListAdapter();
        list = (ListView) findViewById(R.id.listViewEvaluations);
        list.setAdapter(adapter);

    }

    private class MyListAdapter extends ArrayAdapter<Integer> {

        public MyListAdapter() {
            super(ViewCourse.this, R.layout.list_evaluation, Singleton.getSingleton().getSemesters().get(positionSemestre).getCourse().get(position).getMidterms());
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public @NonNull
        View getView(int position, View convertView, @NonNull ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.list_evaluation, parent, false);
            }


            // Make name Text
            TextView nameText = (TextView) itemView.findViewById(R.id.txt_evaluation);
            nameText.setText("Midterm "+ (position+1) +":");

            //TextView gradeText = (TextView) itemView.findViewById(R.id.txt_evulation_grade);
            //gradeText.setText(""+ currentCourse.getMidterms().get(position)+"%");
            return itemView;


        }

    }
    }





