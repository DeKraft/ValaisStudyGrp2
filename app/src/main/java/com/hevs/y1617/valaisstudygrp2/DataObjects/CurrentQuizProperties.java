package com.hevs.y1617.valaisstudygrp2.DataObjects;

import android.content.Context;

import com.hevs.y1617.valaisstudygrp2.db.GetRestDataDB;

import java.util.ArrayList;

/**
 * Created by Acer on 25.11.2016.
 */

public class CurrentQuizProperties {

    public static ArrayList<Quiz> questionList = null;
    public static ArrayList<QuizAnswer> answerList = null;
    private static Destination quizDestination = null;
    private static int idTopic = 0;
    private static int idDiff = 0;

    private static int quizNumber = 0;
    private static int points = 0;
    private static int progress = 1;

    //singleton pattern for questionList
    public static ArrayList<Quiz> getQuestionList(Context context){
        if(questionList == null){
            questionList = GetRestDataDB.getQuizFromDB(context);
        }
        return questionList;
    }

    //singleton pattern for answerList
    public static ArrayList<QuizAnswer> getQuestionAnswerList(Context context){
        if(answerList == null){
            answerList = GetRestDataDB.getQuizAnswersFromDB(context);
        }
        return answerList;
    }

    public static Destination getQuizDestination() {
        return quizDestination;
    }

    public static void setQuizDestination(Destination quizDestination) {
        CurrentQuizProperties.quizDestination = quizDestination;
    }

    public static int getIdTopic() {
        return idTopic;
    }

    public static void setIdTopic(int idTopic) {
        CurrentQuizProperties.idTopic = idTopic;
    }

    public static int getIdDiff() {
        return idDiff;
    }

    public static void setIdDiff(int idDiff) {
        CurrentQuizProperties.idDiff = idDiff;
    }

    public static int getQuizNumber() {
        return quizNumber;
    }

    public static void setQuizNumber(int quizNumber) {
        CurrentQuizProperties.quizNumber = quizNumber;
    }

    public static int getPoints() {
        return points;
    }

    public static void setPoints(int points) {
        CurrentQuizProperties.points = points;
    }

    public static int getProgress() {
        return progress;
    }

    public static void setProgress(int progress) {
        CurrentQuizProperties.progress = progress;
    }
}
