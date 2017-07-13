package com.uottawa.gradetogo;

import android.content.Context;
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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // variable d'instances
    private ArrayAdapter<Semester> adapter;
    private ListView list;
    private static Context mContext;
    int deletetime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button waste_btn = (Button) findViewById(R.id.toolbar_overflow_menu_button);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this, AddSemester.class);
                startActivity(a);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //initialisation du singleton
        Singleton.getSingleton();
        registerClickCallBack();

        //populate the listview
        populateListView();
        waste_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //SelectSemestertoRemove();
                deletetime = 1;
                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(getApplicationContext(), "Select the semester to remove", duration).show();
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        int postion = list.getPositionForView(view);
                        System.out.println("postion selected is : "+postion);
                        if(deletetime == 1) {
                            Delete(postion);
                            deletetime = 0;
                        }
                        else{
                            System.out.println("postion selected is : "+postion);
                        }

                    }
                });
            }
        });

    }




    //methode ajouter pour clicker sur les items sune liste
    private void registerClickCallBack(){

        ListView list = (ListView) findViewById(R.id.listViewSemester);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View viewClick, int position, long id){
                Semester clickedSemester = Singleton.getSingleton().getSemesters().get(position);
                Intent i = new Intent(MainActivity.this, Courses.class);

                //envoyer info a activity qui affiche cours
                i.putExtra("semesterSeason", clickedSemester.getSeason());
                i.putExtra("semesterYear", clickedSemester.getYear());
                i.putExtra("Position", position+"");
                Toast.makeText( MainActivity.this, ""+position , Toast.LENGTH_LONG).show();
                //  start the activity
                startActivity(i);


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

    public void Delete(int position) {
        if (adapter.getCount() > 0) {

            //Log.d("largest no is",""+largestitemno);
            //deleting the latest added by subtracting one 1
            Semester Sem_remove = (Semester) adapter.getItem(position);
            adapter.remove(Sem_remove);
            adapter.notifyDataSetChanged();
        } else {
            int duration = Toast.LENGTH_SHORT;
            //for showing nothing is left in the list
            Toast toast = Toast.makeText(getApplicationContext(), "Db is empty", duration);


            toast.show();
        }
    }

    private void populateListView() {

        adapter = new MyListAdapter();
        list = (ListView) findViewById(R.id.listViewSemester);
        list.setAdapter(adapter);

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



        return super.onOptionsItemSelected(item);
    }


    private class MyListAdapter extends ArrayAdapter<Semester> {

        public MyListAdapter() {
            super(MainActivity.this, R.layout.semesters_list, Singleton.getSingleton().getSemesters());
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public @NonNull
        View getView(int position, View convertView, @NonNull ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.semesters_list, parent, false);
            }


            //find the semester
            Semester currentSemester = Singleton.getSingleton().getSemesters().get(position);


            if(currentSemester.getSeason().equals("SUMMER")) {
                itemView.setBackgroundResource(R.drawable.app_splash_green);
            }
            else if(currentSemester.getSeason().equals("FALL")) {
                itemView.setBackgroundResource(R.drawable.app_splash_orange);
            }else if (currentSemester.getSeason().equals("WINTER"))  {
                itemView.setBackgroundResource(R.drawable.app_splash_blue);
            }

            // Make name Text
            TextView nameText = (TextView) itemView.findViewById(R.id.txt_semester);
            nameText.setText(currentSemester.getSeason()+ " " +currentSemester.getYear());


            TextView courseText = (TextView) itemView.findViewById(R.id.txt_course_number);
            courseText.setText("Number of course: "+currentSemester.getNumCourse());
            return itemView;


        }

    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_about) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
