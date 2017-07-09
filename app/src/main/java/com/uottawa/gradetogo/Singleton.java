package com.uottawa.gradetogo;

import java.util.ArrayList;

/**
 * Created by shanelgauthier on 2017-07-08.
 */

public class Singleton {

    //variables
    private static Singleton singleton; // l'instance du Singleton
    private ArrayList<Semester> semesters;

    private Singleton() {
        semesters = new ArrayList<Semester>();
        semesters.add(new Semester("FALL", "2017"));
        semesters.add(new Semester("SUMMER", "2017"));
        semesters.add(new Semester("Winter", "2017"));


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
