package com.uottawa.gradetogo;

import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by shanelgauthier on 2017-07-09.
 */

public class Course {

    //variables
    private String name;
    private String goal;
    private String grade;
    private Uri iconId;

    //list pour mettre les notes
    private ArrayList<String> midterms;
    private ArrayList<String> finals;
    private ArrayList<String> quizzes;
    private ArrayList<String> laboratories;
    private ArrayList<String> projects;
    private ArrayList<String> oralPresentations;
    private ArrayList<String> others;

    //liste pour mettre les ponderations
    private ArrayList<Double> midtermsPonderation;
    private ArrayList<Double> finalsPonderation;
    private ArrayList<Double> quizzesPonderation;
    private ArrayList<Double> laboratoriesPonderation;
    private ArrayList<Double> projectsPonderation;
    private ArrayList<Double> oralPresentationsPonderation;
    private ArrayList<Double> othersPonderation;

    public Course(String name, String goal, Uri iconId) {
        this.name = name;
        this.goal = goal;
        grade = "N/A";
        this.iconId = iconId;
        midterms = new ArrayList<String>();
        finals = new ArrayList<String>();
        quizzes = new ArrayList<String>();
        laboratories = new ArrayList<String>();
        projects = new ArrayList<String>();
        oralPresentations = new ArrayList<String>();
        others = new ArrayList<String>();

        midtermsPonderation = new ArrayList<Double>();
        finalsPonderation = new ArrayList<Double>();
        quizzesPonderation = new ArrayList<Double>();
        laboratoriesPonderation = new ArrayList<Double>();
        projectsPonderation = new ArrayList<Double>();
        oralPresentationsPonderation = new ArrayList<Double>();
        othersPonderation = new ArrayList<Double>();


    }

    public ArrayList<Double> getMidtermsPonderation() {
        return midtermsPonderation;
    }

    public ArrayList<Double> getFinalsPonderation() {
        return finalsPonderation;
    }

    public ArrayList<Double> getQuizzesPonderation() {
        return quizzesPonderation;
    }

    public ArrayList<Double> getLaboratoriesPonderation() {
        return laboratoriesPonderation;
    }

    public ArrayList<Double> getProjectsPonderation() {
        return projectsPonderation;
    }

    public ArrayList<Double> getOralPresentationsPonderation() {
        return oralPresentationsPonderation;
    }

    public ArrayList<Double> getOthersPonderation() {
        return othersPonderation;
    }

    public ArrayList<String> getMidterms() {
        return midterms;
    }

    public ArrayList<String> getFinals() {
        return finals;
    }

    public ArrayList<String> getQuizzes() {
        return quizzes;
    }

    public ArrayList<String> getLaboratories() {
        return laboratories;
    }

    public ArrayList<String> getProjects() {
        return projects;
    }

    public ArrayList<String> getOralPresentations() {
        return oralPresentations;
    }

    public ArrayList<String> getOthers() {
        return others;
    }

    public String getGrade() {
        return grade;
    }

    public String getGoalLetter() {
        if (grade.equals("N/A")) {
            return "N/A";}
        return Singleton.getSingleton().getGrade(Integer.parseInt(goal));
    }

    public Uri getIconId() {
        return iconId;
    }

    public String getName() {
        return name;
    }

    public String getGradeLetter() {

        if (grade.equals("N/A")) {
            return "N/A";
        }else return Singleton.getSingleton().getGrade(Integer.parseInt(grade));
    }

    public String getGoal() {
        return goal;
    }
}
