package com.hevs.y1617.valaisstudygrp2.DataObjects;

/**
 * Created by Acer on 24.11.2016.
 */

public class QuizTheme {

    private int idTheme;
    private String nameThemeFR;
    private String nameThemeDE;
    private boolean userForQuetion;

    public QuizTheme(int idTheme, String nameThemeFR, boolean userForQuetion) {
        this.idTheme = idTheme;
        this.nameThemeFR = nameThemeFR;
        this.userForQuetion = userForQuetion;
    }

    public int getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(int idTheme) {
        this.idTheme = idTheme;
    }

    public String getNameThemeFR() {
        return nameThemeFR;
    }

    public void setNameThemeFR(String nameThemeFR) {
        this.nameThemeFR = nameThemeFR;
    }

    public String getNameThemeDE() {
        return nameThemeDE;
    }

    public void setNameThemeDE(String nameThemeDE) {
        this.nameThemeDE = nameThemeDE;
    }

    public boolean isUserForQuetion() {
        return userForQuetion;
    }

    public void setUserForQuetion(boolean userForQuetion) {
        this.userForQuetion = userForQuetion;
    }

    @Override
    public String toString() {
        return "QuizTheme{" +
                "idTheme=" + idTheme +
                ", nameThemeFR='" + nameThemeFR + '\'' +
                ", nameThemeDE='" + nameThemeDE + '\'' +
                ", userForQuetion=" + userForQuetion +
                '}';
    }
}
