package com.uottawa.gradetogo;

import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by shanelgauthier on 2017-07-08.
 */

public class Singleton {

    //variables
    private static Singleton singleton; // l'instance du Singleton
    private ArrayList<Semester> semesters;

    //les photos par defauts
    private Uri defaultImageA = Uri.parse("android.resource://com.uottawa.gradetogo/drawable/img_default");
    private Uri defaultImageB = Uri.parse("android.resource://com.uottawa.gradetogo/drawable/img_default2");
    private Uri defaultImageC = Uri.parse("android.resource://com.uottawa.gradetogo/drawable/img_default3");


    private Singleton() {
        semesters = new ArrayList<Semester>();
        semesters.add(new Semester("FALL", "2017"));
        semesters.add(new Semester("SUMMER", "2017"));
        semesters.add(new Semester("Winter", "2017"));


        semesters.get(0).addCourse(new Course("CSI 3581", 90, defaultImageA ));
        semesters.get(0).addCourse(new Course("SEG 3685", 100, defaultImageB));
        semesters.get(0).addCourse(new Course("SEG 3585", 70, defaultImageC));


    }

    // methode qui permet d'obtenir une seule instance de CookHelper
    public static Singleton getSingleton() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;

    }



    public ArrayList<Semester> getSemesters() {
        return semesters;
    }

    public void addSemester(Semester semester) {
        semesters.add(0,semester);
    }

    public boolean isSemesterExisting(Semester semester) {

        String saison = semester.getSeason();
        String year = semester.getYear();
        boolean flag= false;

        for (int i = 0; i < semesters.size(); i++) {
            if (saison.equals(semesters.get(i).getSeason()) && year.equals(semesters.get(i).getYear())) {
                return flag;
            }
        }
        return true;
    }

}
