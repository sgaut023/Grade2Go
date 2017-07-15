package com.uottawa.gradetogo;

import java.util.ArrayList;

/**
 * Created by shanelgauthier on 2017-07-08.
 */

public class Semester {

    //variables
    private String season;
    private String year;
    private int numCourse;
    private ArrayList<Course> courses;



    public Semester(String season, String year) {
        this.season = season.toUpperCase();
        this.year = year.toUpperCase();
        numCourse= 0;
        courses = new ArrayList<Course>();
    }

    public ArrayList<Course> getCourse() {
        return courses;
    }

    public void addCourse(Course course){
        courses.add(course);
        numCourse++;

    }

    public void deleteNumCourse( ){
        numCourse--;
    }

    public String getSeason() {
        return season;
    }

    public String getYear() {
        return year;
    }

    public int getNumCourse(){
        return numCourse;
    }
}
