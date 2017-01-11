package com.hevs.y1617.valaisstudygrp2.DataObjects;

/**
 * Created by Acer on 24.11.2016.
 */

public class QuizAnswer {

    private int fk_QuestionId;
    private int fk_DestinationId;
    private String goodAnswerFR;
    private String goodAnswerDE;
    private String badAnswerFR;
    private String badAnswerDE;
    private String infoAnswerFR;
    private String infoAnswerDE;

    //for creating the general quizanswer with fr as language
    public QuizAnswer(int fk_QuestionId, int fk_DestinationId, String goodAnswerFR, String badAnswerFR, String infoAnswerFR) {
        this.fk_QuestionId = fk_QuestionId;
        this.fk_DestinationId = fk_DestinationId;
        this.goodAnswerFR = goodAnswerFR;
        this.badAnswerFR = badAnswerFR;
        this.infoAnswerFR = infoAnswerFR;
    }

    public QuizAnswer(int fk_QuestionId, int fk_DestinationId, String goodAnswerFR, String goodAnswerDE,
                      String badAnswerFR, String badAnswerDE, String infoAnswerFR, String infoAnswerDE) {
        this.fk_QuestionId = fk_QuestionId;
        this.fk_DestinationId = fk_DestinationId;
        this.goodAnswerFR = goodAnswerFR;
        this.goodAnswerDE = goodAnswerDE;
        this.badAnswerFR = badAnswerFR;
        this.badAnswerDE = badAnswerDE;
        this.infoAnswerFR = infoAnswerFR;
        this.infoAnswerDE = infoAnswerDE;
    }

    //for creating a quiz qith de as language
    public QuizAnswer(String goodAnswerDE, String badAnswerDE, String infoAnswerDE) {
        this.goodAnswerDE = goodAnswerDE;
        this.badAnswerDE = badAnswerDE;
        this.infoAnswerDE = infoAnswerDE;
    }

    public int getFk_QuestionId() {
        return fk_QuestionId;
    }

    public void setFk_QuestionId(int fk_QuestionId) {
        this.fk_QuestionId = fk_QuestionId;
    }

    public int getFk_DestinationId() {
        return fk_DestinationId;
    }

    public void setFk_DestinationId(int fk_DestinationId) {
        this.fk_DestinationId = fk_DestinationId;
    }

    public String getGoodAnswerFR() {
        return goodAnswerFR;
    }

    public void setGoodAnswerFR(String goodAnswerFR) {
        this.goodAnswerFR = goodAnswerFR;
    }

    public String getGoodAnswerDE() {
        return goodAnswerDE;
    }

    public void setGoodAnswerDE(String goodAnswerDE) {
        this.goodAnswerDE = goodAnswerDE;
    }

    public String getBadAnswerFR() {
        return badAnswerFR;
    }

    public void setBadAnswerFR(String badAnswerFR) {
        this.badAnswerFR = badAnswerFR;
    }

    public String getBadAnswerDE() {
        return badAnswerDE;
    }

    public void setBadAnswerDE(String badAnswerDE) {
        this.badAnswerDE = badAnswerDE;
    }

    public String getInfoAnswerFR() {
        return infoAnswerFR;
    }

    public void setInfoAnswerFR(String infoAnswerFR) {
        this.infoAnswerFR = infoAnswerFR;
    }

    public String getInfoAnswerDE() {
        return infoAnswerDE;
    }

    public void setInfoAnswerDE(String infoAnswerDE) {
        this.infoAnswerDE = infoAnswerDE;
    }

    @Override
    public String toString() {
        return "QuizAnswer{" +
                "fk_QuestionId=" + fk_QuestionId +
                ", fk_DestinationId=" + fk_DestinationId +
                ", goodAnswerFR='" + goodAnswerFR + '\'' +
                ", goodAnswerDE='" + goodAnswerDE + '\'' +
                ", badAnswerFR='" + badAnswerFR + '\'' +
                ", badAnswerDE='" + badAnswerDE + '\'' +
                ", infoAnswerFR='" + infoAnswerFR + '\'' +
                ", infoAnswerDE='" + infoAnswerDE + '\'' +
                '}';
    }
}
