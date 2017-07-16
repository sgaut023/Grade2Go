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
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewCourse extends AppCompatActivity {

    private String gradeLetter;
    private int position;
    private int positionSemestre;
    private String name;
    private ArrayAdapter<String> adapter;
    private ListView list;
    private static Context mContext;
    int deletetime = 0;
    public Course currentCourse;
    private ArrayList<String> midterms;
    public Button graphe_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // take the course info position
        Intent intent = getIntent();
        position =  Integer.parseInt(intent.getStringExtra("position"));
        positionSemestre =  Integer.parseInt(intent.getStringExtra("semestrePosition"));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // envoie a lautre activity les info necessaire
                Intent i = new Intent(ViewCourse.this, EditACourse.class);
                i.putExtra("semesterPosition", positionSemestre+"");
                i.putExtra("coursPosition", position+"");
                startActivity(i);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //find the course in question
        currentCourse = Singleton.getSingleton().getSemesters().get(positionSemestre).getCourse().get(position);
        midterms = currentCourse.getMidterms();

        //populatelistView
        populateListView();


        // This is how to change TextView dynamically
        //populate the goal
        TextView goalText = (TextView)findViewById(R.id.txt_goal);
        if(currentCourse.getGoal().equals("N/A")){
        goalText.setText(currentCourse.getGrade() );}
        else{ goalText.setText(currentCourse.getGrade() +"");}

        //populate the grade
        TextView gradeText = (TextView)findViewById(R.id.txt_grade);
        if(currentCourse.getGrade().equals("N/A")){
        gradeText.setText(currentCourse.getGoal() );}
        else{gradeText.setText(currentCourse.getGoal() + "%");}

        //populate the goalLetter
        TextView goalLetterText = (TextView)findViewById(R.id.txt_goal_letter);
       goalLetterText.setText(currentCourse.getGoalLetter() + "");

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

        graphe_btn = (Button) findViewById(R.id.Vgraphe);
        graphe_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!currentCourse.isGradesEmpty()) {
                    Intent i = new Intent(ViewCourse.this, BarChartActivity.class);
                    i.putExtra("semesterPosition", positionSemestre + "");
                    i.putExtra("coursPosition", position + "");
                    startActivity(i);
                }else{

                    Snackbar snackbar = Snackbar
                            .make(v, "please add grade before showing the graphe", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
        //Toast.makeText( ViewCourse.this, ""+ midterms.get(1), Toast.LENGTH_LONG).show();




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
        list = (ListView) findViewById(R.id.ListViewQuiz);
        list.setAdapter(adapter);

        adapter = new MyListAdapter1();
        ListView list1 = (ListView) findViewById(R.id.ListViewFinal);
        list1.setAdapter(adapter);

       adapter = new MyListAdapter2();
        ListView  list2 = (ListView) findViewById(R.id.ListViewMidterm);
       list2.setAdapter(adapter);

        adapter = new MyListAdapter3();
        ListView list3 = (ListView) findViewById(R.id.ListViewProject);
        list3.setAdapter(adapter);

        adapter = new MyListAdapter4();
        ListView list4 = (ListView) findViewById(R.id.ListViewOralPresentation);
        list4.setAdapter(adapter);

        adapter = new MyListAdapter5();
        ListView list5 = (ListView) findViewById(R.id.ListViewLaboratory);
        list5.setAdapter(adapter);


        adapter = new MyListAdapter6();
        ListView list6 = (ListView) findViewById(R.id.ListViewOther);
        list6.setAdapter(adapter);

        Utils.setListViewHeightBasedOnChildren(list);
        Utils.setListViewHeightBasedOnChildren(list1);
        Utils.setListViewHeightBasedOnChildren(list2);
        Utils.setListViewHeightBasedOnChildren(list3);
        Utils.setListViewHeightBasedOnChildren(list4);
        Utils.setListViewHeightBasedOnChildren(list5);
        Utils.setListViewHeightBasedOnChildren(list6);




       //mettre espace entre les listview
        if(currentCourse.getQuizzes().size()!=0){
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) list
                    .getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 20);}

        if(currentCourse.getFinals().size()!=0){
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) list1
                    .getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 20);}

        if(currentCourse.getMidterms().size()!=0){
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) list2
                    .getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 20);}

        if(currentCourse.getProjects().size()!=0){
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) list3
                    .getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 20);}

        if(currentCourse.getOralPresentations().size()!=0){
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) list4
                    .getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 20);}

        if(currentCourse.getLaboratories().size()!=0){
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) list5
                    .getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 20);}

        if(currentCourse.getOthers().size()!=0){
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) list6
                    .getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 20);}



    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent i = new Intent(ViewCourse.this, Courses.class);
        i.putExtra("semesterPosition", positionSemestre+"");
        startActivity(i);
        finish();
    }

    private class MyListAdapter extends ArrayAdapter<String> {

        public MyListAdapter() {
            super(ViewCourse.this, R.layout.list_evaluation, currentCourse.getQuizzes());
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
            nameText.setText("Quiz " + (position + 1) + " :");

            String currentGrade = currentCourse.getQuizzes().get(position);

            TextView gradeLetterText = (TextView) itemView.findViewById(R.id.txt_evaluation_letter);
            if (currentGrade.equals("N/A")) {
                gradeLetterText.setText("N/A");

            } else {
                gradeLetterText.setText("" + Singleton.getSingleton().getGrade(Integer.parseInt(currentGrade)));
            }

            TextView gradeText = (TextView) itemView.findViewById(R.id.txt_evulation_grade);
            if (currentGrade.equals("N/A")) {
                gradeText.setText("N/A");
            } else {
                gradeText.setText(currentGrade + "%");
            }


            return itemView;
        }

    }

    private class MyListAdapter1 extends ArrayAdapter<String> {

        public MyListAdapter1() {
            super(ViewCourse.this, R.layout.list_evaluation, currentCourse.getFinals());
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
            nameText.setText("Final "+ (position+1) +" :");

            String currentGrade = currentCourse.getFinals().get(position);

            TextView gradeLetterText = (TextView) itemView.findViewById(R.id.txt_evaluation_letter);
            if(currentGrade.equals("N/A")){
                gradeLetterText.setText("N/A");

            }else {
                gradeLetterText.setText("" + Singleton.getSingleton().getGrade(Integer.parseInt(currentGrade)));
            }

            TextView gradeText = (TextView) itemView.findViewById(R.id.txt_evulation_grade);
            if(currentGrade.equals("N/A")){
                gradeText.setText("N/A");}
            else{
                gradeText.setText(currentGrade + "%");}
            return itemView;


        }

    }

    private class MyListAdapter2 extends ArrayAdapter<String> {

        public MyListAdapter2() {
            super(ViewCourse.this, R.layout.list_evaluation, midterms);
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
            nameText.setText("Midterm "+ (position+1) +" :");

            String currentGrade = currentCourse.getMidterms().get(position);

            TextView gradeLetterText = (TextView) itemView.findViewById(R.id.txt_evaluation_letter);
            if(currentGrade.equals("N/A")){
                gradeLetterText.setText("N/A");

            }else {
                gradeLetterText.setText("" + Singleton.getSingleton().getGrade(Integer.parseInt(currentGrade)));
            }

            TextView gradeText = (TextView) itemView.findViewById(R.id.txt_evulation_grade);
            if(currentGrade.equals("N/A")){
                gradeText.setText("N/A");}
            else{
                gradeText.setText(currentGrade + "%");}
            return itemView;

        }


    }

    private class MyListAdapter3 extends ArrayAdapter<String> {

        public MyListAdapter3() {
            super(ViewCourse.this, R.layout.list_evaluation, currentCourse.getProjects());
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
            nameText.setText("Project "+ (position+1) +" :");

            String currentGrade = currentCourse.getProjects().get(position);

            TextView gradeLetterText = (TextView) itemView.findViewById(R.id.txt_evaluation_letter);
            if(currentGrade.equals("N/A")){
                gradeLetterText.setText("N/A");

            }else {
                gradeLetterText.setText("" + Singleton.getSingleton().getGrade(Integer.parseInt(currentGrade)));
            }

            TextView gradeText = (TextView) itemView.findViewById(R.id.txt_evulation_grade);
            if(currentGrade.equals("N/A")){
                gradeText.setText("N/A");}
            else{
                gradeText.setText(currentGrade + "%");}
            return itemView;


        }

    }

    private class MyListAdapter4 extends ArrayAdapter<String> {

        public MyListAdapter4() {
            super(ViewCourse.this, R.layout.list_evaluation, currentCourse.getOralPresentations());
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
            nameText.setText("Oral "+ (position+1) +" :");

            String currentGrade = currentCourse.getOralPresentations().get(position);

            TextView gradeLetterText = (TextView) itemView.findViewById(R.id.txt_evaluation_letter);
            if(currentGrade.equals("N/A")){
                gradeLetterText.setText("N/A");

            }else {
                gradeLetterText.setText("" + Singleton.getSingleton().getGrade(Integer.parseInt(currentGrade)));
            }

            TextView gradeText = (TextView) itemView.findViewById(R.id.txt_evulation_grade);
            if(currentGrade.equals("N/A")){
                gradeText.setText("N/A");}
            else{
                gradeText.setText(currentGrade + "%");}

            return itemView;




        }

    }

    private class MyListAdapter5 extends ArrayAdapter<String> {

        public MyListAdapter5() {
            super(ViewCourse.this, R.layout.list_evaluation, currentCourse.getLaboratories());
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
            nameText.setText("Laboratory "+ (position+1) +" :");

            String currentGrade = currentCourse.getLaboratories().get(position);

            TextView gradeLetterText = (TextView) itemView.findViewById(R.id.txt_evaluation_letter);
            if(currentGrade.equals("N/A")){
                gradeLetterText.setText("N/A");

            }else {
                gradeLetterText.setText("" + Singleton.getSingleton().getGrade(Integer.parseInt(currentGrade)));
            }

            TextView gradeText = (TextView) itemView.findViewById(R.id.txt_evulation_grade);
            if(currentGrade.equals("N/A")){
                gradeText.setText("N/A");}
            else{
                gradeText.setText(currentGrade + "%");}

            return itemView;




        }

    }

    private class MyListAdapter6 extends ArrayAdapter<String> {

        public MyListAdapter6() {
            super(ViewCourse.this, R.layout.list_evaluation, currentCourse.getOthers());
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
            nameText.setText("Other "+ (position+1) +" :");

            String currentGrade = currentCourse.getOthers().get(position);

            TextView gradeLetterText = (TextView) itemView.findViewById(R.id.txt_evaluation_letter);
            if(currentGrade.equals("N/A")){
                gradeLetterText.setText("N/A");

            }else {
                gradeLetterText.setText("" + Singleton.getSingleton().getGrade(Integer.parseInt(currentGrade)));
            }

            TextView gradeText = (TextView) itemView.findViewById(R.id.txt_evulation_grade);
            if(currentGrade.equals("N/A")){
                gradeText.setText("N/A");}
            else{
                gradeText.setText(currentGrade + "%");}

            return itemView;


        }

    }


    /**
     * Set listview height based on listview children
     *
     * @param listView
     */
    public static void setDynamicHeight(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        //check adapter if null
        if (adapter == null) {
            return;
        }
        int height = 0;
        int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
            height += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.height = height + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(layoutParams);
        listView.requestLayout();
    }

    }





