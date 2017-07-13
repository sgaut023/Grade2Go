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
    private int University;
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
        University  = 0;

    }

    // methode qui permet d'obtenir une seule instance de CookHelper
    public static Singleton getSingleton() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;

    }
    public void setUniversity(int university) {
        this.University = university;
    }

    public int getUniversity() {
        return University;
    }

    public String getGrade(int perc){
        int uni = getUniversity();
        switch (uni) {
            case 0 :
                if(perc > 90){
                    return "A+";
                }
                else if(perc >= 85 && perc <90){return "A";}
                else if(perc >= 80 && perc <85){return "A-";}
                else if(perc >= 75 && perc <80){return "B+";}
                else if(perc >= 70 && perc <75){return "B";}
                else if(perc >= 65 && perc <70){return "C+";}
                else if(perc >= 60 && perc <65){return "C";}
                else if(perc >= 55 && perc <60){return "D";}
                else if(perc >= 50 && perc <55){return "F";}
                else{return "E";}
            case 1 :
                if(perc > 90){
                    return "A+";
                }
                else if(perc >= 85 && perc <90){return "A";}
                else if(perc >= 80 && perc <85){return "A-";}
                else if(perc >= 75 && perc <80){return "B+";}
                else if(perc >= 70 && perc <75){return "B";}
                else if(perc >= 65 && perc <70){return "B-";}
                else if(perc >= 60 && perc <65){return "C+";}
                else if(perc >= 55 && perc <60){return "C";}
                else if(perc >= 50 && perc <55){return "D";}
                else{return "E";}
            case  2 :
                break;
        }
        return "error";
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
