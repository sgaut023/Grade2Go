package com.uottawa.gradetogo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Courses extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    // variable d'instances
    private ArrayAdapter<Course> adapter;
    private ListView list;
    int deletetime = 0;
    int semesterPosition ;
    private ActionBarDrawerToggle toggle;
    public DrawerLayout drawer ;
    public int show =0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button waste_btn = (Button) findViewById(R.id.toolbar_waste_button);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_courses);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Courses.this, AddCourse.class);
                startActivity(i);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // pass the semester info
        Intent intent = getIntent();
        semesterPosition = Integer.parseInt(intent.getStringExtra("Position"));

        registerClickCallBack();

        //populate the listview
        populateListView();
        waste_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //SelectSemestertoRemove();
                deletetime = 1;
                Singleton.getSingleton().setUniversity(0);

                Snackbar snackbar = Snackbar
                        .make(v, "Select the courses to remove", Snackbar.LENGTH_LONG);
                snackbar.show();
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        final int postion = list.getPositionForView(view);
                        System.out.println("postion selected is : "+postion);
                        if(deletetime == 1) {
                            Delete(postion);
                            registerClickCallBack();
                            if(show == 1) {
                                Snackbar snackbar = Snackbar
                                        .make(view, "The courses was successfully removed", Snackbar.LENGTH_LONG);
                                snackbar.show();
                            }
                            deletetime = 0;
                        }
                        else{
                            System.out.println("postion selected is : "+postion);
                            registerClickCallBack();
                        }

                    }
                });
            }
        });

    }
    public void SelectSemestertoRemove(){
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), "Select the semester to remove", duration);
        new View.OnClickListener() {
            public void onClick(View v) {
                int postion = list.getPositionForView(v);
                System.out.println("postion selected is : "+postion);
                Delete(postion);
            }
        };
    }

    public void Delete(final int position) {
        if (adapter.getCount() > 0) {

            //Log.d("largest no is",""+largestitemno);
            //deleting the latest added by subtracting one 1
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Courses.this);
            builder1.setMessage("Do you want to delete the selected semester ?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Course Sem_remove = (Course) adapter.getItem(position);
                            adapter.remove(Sem_remove);
                            adapter.notifyDataSetChanged();
                            dialog.cancel();
                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
            ;
        } else {
            int duration = Toast.LENGTH_SHORT;
            //for showing nothing is left in the list
            Toast toast = Toast.makeText(getApplicationContext(), "Db is empty", duration);


            toast.show();
        }
        show = 1;
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_settings) {
            Intent i = new Intent(this, Settings.class);
            startActivity(i);

        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_about) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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




    }

}
