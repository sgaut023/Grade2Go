package com.uottawa.gradetogo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Courses extends AppCompatActivity {
    // variable d'instances
    private ArrayAdapter<Course> adapter;
    private ListView list;
    int deletetime = 0;
    int semesterPosition ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_courses);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //onNavigationItemSelected(this);

        // pass the semester info
        Intent intent = getIntent();
        semesterPosition = Integer.parseInt(intent.getStringExtra("Position"));

        registerClickCallBack();

        //populate the listview
        populateListView();

    }


    private void populateListView() {

        adapter = new MyListAdapter();
        list = (ListView) findViewById(R.id.listViewCourse);
        list.setAdapter(adapter);

    }

    //methode ajouter pour clicker sur les items sune liste
    private void registerClickCallBack(){

        ListView list = (ListView) findViewById(R.id.listViewCourse);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View viewClick, int position, long id){

                Course clickedCourse = Singleton.getSingleton().getSemesters().get(semesterPosition).getCourse().get(position);

                Intent i = new Intent(Courses.this, ViewCourse.class);
                i.putExtra("semestrePosition", semesterPosition+"");
                i.putExtra("position", position+"");


                //  start the activity
                startActivity(i);


            }
        });

    }


    private class MyListAdapter extends ArrayAdapter<Course> {

        public MyListAdapter() {
            super(Courses.this, R.layout.courses_list, Singleton.getSingleton().getSemesters().get(semesterPosition).getCourse());
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public @NonNull
        View getView(int position, View convertView, @NonNull ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.courses_list, parent, false);
            }


            //find the semester
            Course course = Singleton.getSingleton().getSemesters().get(semesterPosition).getCourse().get(position);


            // Make name Text
            TextView nameText = (TextView) itemView.findViewById(R.id.txt_cours_name);
            nameText.setText(course.getName());

            // Make grade Text
            TextView gradeLetterText = (TextView) itemView.findViewById(R.id.txt_grade);
            gradeLetterText.setText(Singleton.getSingleton().getGrade((int)course.getGrade())+"");

            // Make grade Text
            TextView gradeText = (TextView) itemView.findViewById(R.id. txt_list_grade);
            gradeText.setText(course.getGradeLetter()+"");

            // Make grade Text
            TextView goalText = (TextView) itemView.findViewById(R.id.txt_goal);
            goalText.setText(Singleton.getSingleton().getGrade((int)course.getGoal())+"");

            // Make grade Text
            TextView goalLetterText = (TextView) itemView.findViewById(R.id. txt_list_grade_goal);
            goalLetterText.setText(course.getGoal()+"");


            ImageView imageView = (ImageView) itemView.findViewById(R.id.item_icon);
            imageView.setImageURI(course.getIconId());



            return itemView;


        }

        @SuppressWarnings("StatementWithEmptyBody")
        public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if (id == R.id.nav_camera) {
                Intent a = new Intent(Courses.this, MainActivity.class);
                startActivity(a);
            } else if (id == R.id.nav_settings) {

            } else if (id == R.id.nav_help) {

            } else if (id == R.id.nav_about) {


            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

    }

}
