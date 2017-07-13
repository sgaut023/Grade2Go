package com.uottawa.gradetogo;

import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by shanelgauthier on 2017-07-09.
 */

public class Course {

    //variables
    private String name;
    private int goal;
    private String goalLetter;
    private double grade;
    private String gradeLetter ;
    private Uri iconId;
    private ArrayList<Double> midterms;
    private ArrayList<Double> finals;
    private ArrayList<Double> quizzes;
    private ArrayList<Double> laboratories;
    private ArrayList<Double> projects;
    private ArrayList<Double> oralPresentations;
    private ArrayList<Double> others;

    public Course(String name, int goal, Uri iconId) {
        this.name = name;
        this.goal = goal;
        this.iconId=iconId;
        midterms = new ArrayList<Double>();
        finals =new ArrayList<Double>();
        quizzes = new ArrayList<Double>();
        laboratories =new ArrayList<Double>();
        projects =new ArrayList<Double>();
        oralPresentations=new ArrayList<Double>();
        others=new ArrayList<Double>();
        gradeLetter="N/A";

    }

    public ArrayList<Double> getMidterms() {
        return midterms;
    }

    public ArrayList<Double> getFinals() {
        return finals;
    }

    public ArrayList<Double> getQuizzes() {
        return quizzes;
    }

    public ArrayList<Double> getLaboratories() {
        return laboratories;
    }

    public ArrayList<Double> getProjects() {
        return projects;
    }

    public ArrayList<Double> getOralPresentations() {
        return oralPresentations;
    }

    public ArrayList<Double> getOthers() {
        return others;
    }

    public double getGrade() {
        return grade;
    }

    public String getGoalLetter(){
        return goalLetter;
    }
    public Uri getIconId(){
        return iconId;
    }
    public String getName() {
        return name;
    }

    public String getGradeLetter(){
        return gradeLetter;
    }

    public double getGoal() {
        return goal;
    }
}
