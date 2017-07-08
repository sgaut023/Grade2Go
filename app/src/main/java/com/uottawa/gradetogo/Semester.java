package com.uottawa.gradetogo;

/**
 * Created by shanelgauthier on 2017-07-08.
 */

public class Semester {

    //variables
    private String season;
    private String year;
    private int numCourse;

    public Semester(String season, String year) {
        this.season = season;
        this.year = year;
        numCourse= 0;
    }

    public String getSeason() {
        return season;
    }

    public String getYear() {
        return year;
    }

    public int getnumCourse(){
        return numCourse;
    }
}
