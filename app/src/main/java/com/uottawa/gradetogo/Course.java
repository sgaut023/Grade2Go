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
        if (goal.equals("N/A")) {
            return "N/A";}
        else{
        return Singleton.getSingleton().getGrade( Double.parseDouble(goal));}
    }

    public Uri getIconId() {
        return iconId;
    }

    public String getName() {
        return name.toUpperCase();
    }

    public String getGradeLetter() {

        if (grade.equals("N/A")) {
            return "N/A";
        }else return Singleton.getSingleton().getGrade(Double.parseDouble(grade));
    }
    public boolean isGradesEmpty(){
        ArrayList<String> grades = getGrades();
        int i = 0 ;
        while (i<grades.size()){
            if(!grades.get(i).equals("N/A")){
                return false;
            }
            i++;
        }
        return true;
    }

    public String getGoal() {
        return goal;
    }

    public ArrayList<String> getGrades(){
        ArrayList<String> newList = new ArrayList<String>();
        newList.addAll(quizzes);
        newList.addAll(midterms);
        newList.addAll(projects);
        newList.addAll(oralPresentations);
        newList.addAll(finals);
        newList.addAll(laboratories);
        newList.addAll(others);
        return newList;
    }

    public ArrayList<Double> getPonderations(){
        ArrayList<Double> newList = new ArrayList<Double>();
        newList.addAll(quizzesPonderation);
        newList.addAll(midtermsPonderation);
        newList.addAll(projectsPonderation);
        newList.addAll(oralPresentationsPonderation);
        newList.addAll(finalsPonderation);
        newList.addAll(laboratoriesPonderation);
        newList.addAll(othersPonderation);
        return newList;

    }

    public ArrayList<String> getEvaluationsName(){
        ArrayList<String> newList = new ArrayList<String>();
        for(int i=0; i<quizzes.size(); i++){
            newList.add("Quiz " +(i+1));
        }
        for(int i=0; i<midterms.size(); i++){
            newList.add("Midterm " +(i+1));
        }
        for(int i=0; i<projects.size(); i++){
            newList.add("Project " +(i+1));
        }
        for(int i=0; i<oralPresentations.size(); i++){
            newList.add("Oral " +(i+1));
        }
        for(int i=0; i<finals.size(); i++){
            newList.add("Final " +(i+1));
        }
        for(int i=0; i<laboratories.size(); i++){
            newList.add("Laboratory " +(i+1));
        }
        for(int i=0; i<others.size(); i++){
            newList.add("Other " +(i+1));
        }

        return newList;

    }

    public int getNumQuiz(){return quizzes.size();}
    public int getNumMidterm(){return midterms.size();}
    public int getNumProject(){return projects.size();}
    public int getNumOral(){return oralPresentations.size();}
    public int getNumFinal(){return finals.size();}
    public int getNumOther(){return others.size();}
    public int getNumLaboratory(){return laboratories.size();}


}
