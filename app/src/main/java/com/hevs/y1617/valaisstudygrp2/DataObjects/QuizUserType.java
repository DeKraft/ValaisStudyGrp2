package com.hevs.y1617.valaisstudygrp2.DataObjects;

/**
 * Created by Acer on 24.11.2016.
 */

public class QuizUserType {

    private int idUserType;
    private String nameUserTypeFR;
    private String nameUserTypeDE;

    public QuizUserType(int idUserType, String nameUserTypeFR) {
        this.idUserType = idUserType;
        this.nameUserTypeFR = nameUserTypeFR;
    }

    public QuizUserType(int idUserType, String nameUserTypeFR, String nameUserTypeDE) {
        this.idUserType = idUserType;
        this.nameUserTypeFR = nameUserTypeFR;
        this.nameUserTypeDE = nameUserTypeDE;
    }

    public int getIdUserType() {
        return idUserType;
    }

    public void setIdUserType(int idUserType) {
        this.idUserType = idUserType;
    }

    public String getNameUserTypeFR() {
        return nameUserTypeFR;
    }

    public void setNameUserTypeFR(String nameUserTypeFR) {
        this.nameUserTypeFR = nameUserTypeFR;
    }

    public String getNameUserTypeDE() {
        return nameUserTypeDE;
    }

    public void setNameUserTypeDE(String nameUserTypeDE) {
        this.nameUserTypeDE = nameUserTypeDE;
    }

    @Override
    public String toString() {
        return "QuizUserType{" +
                "idUserType=" + idUserType +
                ", nameUserTypeFR='" + nameUserTypeFR + '\'' +
                ", nameUserTypeDE='" + nameUserTypeDE + '\'' +
                '}';
    }
}
