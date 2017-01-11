package com.hevs.y1617.valaisstudygrp2.DataObjects;

/**
 * Created by Acer on 24.11.2016.
 */

public class QuizLevel {

    private int idLevel;
    private String nameLevelFR;
    private String nameLevelDE;

    public QuizLevel(int idLevel, String nameLevelFR) {
        this.idLevel = idLevel;
        this.nameLevelFR = nameLevelFR;
    }

    public int getIdLevel() {
        return idLevel;
    }

    public void setIdLevel(int idLevel) {
        this.idLevel = idLevel;
    }

    public String getNameLevelFR() {
        return nameLevelFR;
    }

    public void setNameLevelFR(String nameLevelFR) {
        this.nameLevelFR = nameLevelFR;
    }

    public String getNameLevelDE() {
        return nameLevelDE;
    }

    public void setNameLevelDE(String nameLevelDE) {
        this.nameLevelDE = nameLevelDE;
    }

    @Override
    public String toString() {
        return "QuizLevel{" +
                "idLevel=" + idLevel +
                ", nameLevelFR='" + nameLevelFR + '\'' +
                ", nameLevelDE='" + nameLevelDE + '\'' +
                '}';
    }
}
