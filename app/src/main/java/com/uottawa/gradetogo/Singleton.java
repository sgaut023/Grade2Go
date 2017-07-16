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
    private int lastSemesterPosition;
    private int lastCoursePosition;
    //les photos par defauts
    private Uri defaultImageA = Uri.parse("android.resource://com.uottawa.gradetogo/drawable/img_default");
    private Uri defaultImageB = Uri.parse("android.resource://com.uottawa.gradetogo/drawable/img_default2");
    private Uri defaultImageC = Uri.parse("android.resource://com.uottawa.gradetogo/drawable/img_default3");
    private Uri defaultImageD = Uri.parse("android.resource://com.uottawa.gradetogo/drawable/img_default4");
    private Uri defaultImageE = Uri.parse("android.resource://com.uottawa.gradetogo/drawable/img_default5");
    private Uri defaultImageF = Uri.parse("android.resource://com.uottawa.gradetogo/drawable/img_default6");


    private Singleton() {
        semesters = new ArrayList<Semester>();
        semesters.add(new Semester("FALL", "2017"));
        semesters.add(new Semester("SUMMER", "2017"));
        semesters.add(new Semester("Winter", "2017"));
        lastSemesterPosition=0;
        lastCoursePosition=0;


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

    public String getGrade(double perc){
        int uni = getUniversity();
        switch (uni) {
            case 0 :
                if(perc >= 90){
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
                if(perc >= 90){
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
 /*   public int[] getGradeofCompenent(Course cours, String compenent){
        ArrayList<String> copenentPen ;
        int[] grade = new int[10];
        switch (compenent){
            case "midterms" :
                copenentPen
                break;
            case "finals":
                copenentPen
                break;
            case "quizzes":
                copenentPen
                break;
            case "laboratories":
                copenentPen
                break;
            case "projects":
                copenentPen
                break;
            case "oralPresentations":
                copenentPen
                break;
            case "others":
                copenentPen
                break;
        }
        return grade;
    }*/
    //fonction qui retourne une image pour un cours
   public Uri getPhoto(int position){

        if(position %5 == 0){
            return defaultImageF;
        } else if (position %4 == 0){
            return defaultImageE;
        }else if( position%3 == 0){
            return defaultImageD;
        }else if (position % 2 == 0){
            return defaultImageC;
        }else if (position %1 == 0){
            return defaultImageB;
        }else return defaultImageA;

    }

    public int getLastSemesterPosition(){
        return lastSemesterPosition;
    }

    public void setLastSemesterPosition(int position){
        lastSemesterPosition =position;
    }

    public int getLastCoursePosition(){
        return lastSemesterPosition;
    }

    public void setLastCoursePosition(int position){
        lastSemesterPosition =position;
    }

}
