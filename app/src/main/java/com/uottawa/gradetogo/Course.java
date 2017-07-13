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
    private ArrayList<Integer> midterms;
    private ArrayList<Integer> finals;
    private ArrayList<Integer> quizzes;
    private ArrayList<Integer> laboratories;
    private ArrayList<Integer> projects;
    private ArrayList<Integer> oralPresentations;
    private ArrayList<Integer> others;

    public Course(String name, int goal, Uri iconId) {
        this.name = name;
        this.goal = goal;
        this.iconId=iconId;
        midterms = new ArrayList<Integer>();
        midterms.add(54);
        midterms.add(67);
        finals =new ArrayList<Integer>();
        finals.add(45);
        quizzes = new ArrayList<Integer>();
        laboratories =new ArrayList<Integer>();
        projects =new ArrayList<Integer>();
        oralPresentations=new ArrayList<Integer>();
        others=new ArrayList<Integer>();
        gradeLetter="---";

    }

    public ArrayList<Integer> getMidterms() {
        return midterms;
    }

    public ArrayList<Integer> getFinals() {
        return finals;
    }

    public ArrayList<Integer> getQuizzes() {
        return quizzes;
    }

    public ArrayList<Integer> getLaboratories() {
        return laboratories;
    }

    public ArrayList<Integer> getProjects() {
        return projects;
    }

    public ArrayList<Integer> getOralPresentations() {
        return oralPresentations;
    }

    public ArrayList<Integer> getOthers() {
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

    public int getGoal() {
        return goal;
    }
}
