package com.hevs.y1617.valaisstudygrp2.DataObjects;

/**
 * Created by Acer on 23.11.2016.
 */

public class Quiz {

    private int idQuestion;
    private int fk_Theme;
    private int fk_Level;
    private int fk_userType;
    private String questionDE;
    private String questionFR;
    private String imageLink;
    private String imageSource;

    public Quiz(int idQuestion, int fk_Theme, int fk_Level, int fk_userType, String imageLink, String imageSource) {
        this.idQuestion = idQuestion;
        this.fk_Theme = fk_Theme;
        this.fk_Level = fk_Level;
        this.fk_userType = fk_userType;
        this.imageLink = imageLink;
        this.imageSource = imageSource;
    }

    public Quiz(int idQuestion, int fk_Theme, int fk_Level, int fk_userType, String questionDE, String questionFR, String imageLink, String imageSource) {
        this.idQuestion = idQuestion;
        this.fk_Theme = fk_Theme;
        this.fk_Level = fk_Level;
        this.fk_userType = fk_userType;
        this.questionDE = questionDE;
        this.questionFR = questionFR;
        this.imageLink = imageLink;
        this.imageSource = imageSource;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public int getFk_Theme() {
        return fk_Theme;
    }

    public void setFk_Theme(int fk_Theme) {
        this.fk_Theme = fk_Theme;
    }

    public int getFk_Level() {
        return fk_Level;
    }

    public void setFk_Level(int fk_Level) {
        this.fk_Level = fk_Level;
    }

    public int getFk_userType() {
        return fk_userType;
    }

    public void setFk_userType(int fk_userType) {
        this.fk_userType = fk_userType;
    }

    public String getQuestionDE() {
        return questionDE;
    }

    public void setQuestionDE(String questionDE) {
        this.questionDE = questionDE;
    }

    public String getQuestionFR() {
        return questionFR;
    }

    public void setQuestionFR(String questionFR) {
        this.questionFR = questionFR;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "idQuestion=" + idQuestion +
                ", fk_Theme=" + fk_Theme +
                ", fk_Level=" + fk_Level +
                ", fk_userType=" + fk_userType +
                ", questionDE='" + questionDE + '\'' +
                ", questionFR='" + questionFR + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", imageSource='" + imageSource + '\'' +
                '}';
    }
}
