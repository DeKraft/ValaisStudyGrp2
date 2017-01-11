package com.hevs.y1617.valaisstudygrp2.DataObjects;

/**
 * Created by Acer on 02.12.2016.
 */

public class LearningOffline {

    private int idDestination;
    private int idTheme;
    private String headerBigFR;
    private String headerLittle1FR;
    private String paragraph1FR;
    private String headerLittle2FR;
    private String listFR;
    private String headerLittle3FR;
    private String paragraph2FR;
    private String headerBigDE;
    private String headerLittle1DE;
    private String paragraph1DE;
    private String headerLittle2DE;
    private String listDE;
    private String headerLittle3DE;
    private String paragraph2DE;

    public LearningOffline(int idDestination, int idTheme, String headerBigFR, String headerLittle1FR,
                           String paragraph1FR, String headerLittle2FR, String listFR,
                           String headerLittle3FR, String paragraph2FR) {
        this.idDestination = idDestination;
        this.idTheme = idTheme;
        this.headerBigFR = headerBigFR;
        this.headerLittle1FR = headerLittle1FR;
        this.paragraph1FR = paragraph1FR;
        this.headerLittle2FR = headerLittle2FR;
        this.listFR = listFR;
        this.headerLittle3FR = headerLittle3FR;
        this.paragraph2FR = paragraph2FR;
    }

    public LearningOffline(String headerBigDE, String headerLittle1DE, String paragraph1DE,
                           String headerLittle2DE, String listDE, String headerLittle3DE, String paragraph2DE) {
        this.headerBigDE = headerBigDE;
        this.headerLittle1DE = headerLittle1DE;
        this.paragraph1DE = paragraph1DE;
        this.headerLittle2DE = headerLittle2DE;
        this.listDE = listDE;
        this.headerLittle3DE = headerLittle3DE;
        this.paragraph2DE = paragraph2DE;
    }

    public LearningOffline(int idDestination, int idTheme, String headerBigFR, String headerLittle1FR,
                           String paragraph1FR, String headerLittle2FR, String listFR, String headerLittle3FR,
                           String paragraph2FR, String headerBigDE, String headerLittle1DE, String paragraph1DE,
                           String headerLittle2DE, String listDE, String headerLittle3DE, String paragraph2DE) {
        this.idDestination = idDestination;
        this.idTheme = idTheme;
        this.headerBigFR = headerBigFR;
        this.headerLittle1FR = headerLittle1FR;
        this.paragraph1FR = paragraph1FR;
        this.headerLittle2FR = headerLittle2FR;
        this.listFR = listFR;
        this.headerLittle3FR = headerLittle3FR;
        this.paragraph2FR = paragraph2FR;
        this.headerBigDE = headerBigDE;
        this.headerLittle1DE = headerLittle1DE;
        this.paragraph1DE = paragraph1DE;
        this.headerLittle2DE = headerLittle2DE;
        this.listDE = listDE;
        this.headerLittle3DE = headerLittle3DE;
        this.paragraph2DE = paragraph2DE;
    }

    public int getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(int idDestination) {
        this.idDestination = idDestination;
    }

    public int getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(int idTheme) {
        this.idTheme = idTheme;
    }

    public String getHeaderBigFR() {
        return headerBigFR;
    }

    public void setHeaderBigFR(String headerBigFR) {
        this.headerBigFR = headerBigFR;
    }

    public String getHeaderLittle1FR() {
        return headerLittle1FR;
    }

    public void setHeaderLittle1FR(String headerLittle1FR) {
        this.headerLittle1FR = headerLittle1FR;
    }

    public String getParagraph1FR() {
        return paragraph1FR;
    }

    public void setParagraph1FR(String paragraph1FR) {
        this.paragraph1FR = paragraph1FR;
    }

    public String getHeaderLittle2FR() {
        return headerLittle2FR;
    }

    public void setHeaderLittle2FR(String headerLittle2FR) {
        this.headerLittle2FR = headerLittle2FR;
    }

    public String getListFR() {
        return listFR;
    }

    public void setListFR(String listFR) {
        this.listFR = listFR;
    }

    public String getHeaderLittle3FR() {
        return headerLittle3FR;
    }

    public void setHeaderLittle3FR(String headerLittle3FR) {
        this.headerLittle3FR = headerLittle3FR;
    }

    public String getParagraph2FR() {
        return paragraph2FR;
    }

    public void setParagraph2FR(String paragraph2FR) {
        this.paragraph2FR = paragraph2FR;
    }

    public String getHeaderBigDE() {
        return headerBigDE;
    }

    public void setHeaderBigDE(String headerBigDE) {
        this.headerBigDE = headerBigDE;
    }

    public String getHeaderLittle1DE() {
        return headerLittle1DE;
    }

    public void setHeaderLittle1DE(String headerLittle1DE) {
        this.headerLittle1DE = headerLittle1DE;
    }

    public String getParagraph1DE() {
        return paragraph1DE;
    }

    public void setParagraph1DE(String paragraph1DE) {
        this.paragraph1DE = paragraph1DE;
    }

    public String getHeaderLittle2DE() {
        return headerLittle2DE;
    }

    public void setHeaderLittle2DE(String headerLittle2DE) {
        this.headerLittle2DE = headerLittle2DE;
    }

    public String getListDE() {
        return listDE;
    }

    public void setListDE(String listDE) {
        this.listDE = listDE;
    }

    public String getHeaderLittle3DE() {
        return headerLittle3DE;
    }

    public void setHeaderLittle3DE(String headerLittle3DE) {
        this.headerLittle3DE = headerLittle3DE;
    }

    public String getParagraph2DE() {
        return paragraph2DE;
    }

    public void setParagraph2DE(String paragraph2DE) {
        this.paragraph2DE = paragraph2DE;
    }

    @Override
    public String toString() {
        return "LearningOffline{" +
                "idDestination=" + idDestination +
                ", idTheme=" + idTheme +
                ", headerBigFR='" + headerBigFR + '\'' +
                ", headerLittle1FR='" + headerLittle1FR + '\'' +
                ", paragraph1FR='" + paragraph1FR + '\'' +
                ", headerLittle2FR='" + headerLittle2FR + '\'' +
                ", listFR='" + listFR + '\'' +
                ", headerLittle3FR='" + headerLittle3FR + '\'' +
                ", paragraph2FR='" + paragraph2FR + '\'' +
                ", headerBigDE='" + headerBigDE + '\'' +
                ", headerLittle1DE='" + headerLittle1DE + '\'' +
                ", paragraph1DE='" + paragraph1DE + '\'' +
                ", headerLittle2DE='" + headerLittle2DE + '\'' +
                ", listDE='" + listDE + '\'' +
                ", headerLittle3DE='" + headerLittle3DE + '\'' +
                ", paragraph2DE='" + paragraph2DE + '\'' +
                '}';
    }
}
