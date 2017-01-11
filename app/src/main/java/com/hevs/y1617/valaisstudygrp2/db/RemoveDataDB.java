package com.hevs.y1617.valaisstudygrp2.db;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Acer on 11.12.2016.
 */

public class RemoveDataDB {


    public static SQLiteOpenHelper dbHelper = null;
    public static ProgressDialog mDialog = null;


    public static void removeDestination(Context context) {
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();
        ContentValues values = new ContentValues();

        /** delete selected listitem **/
        db.delete(ValaisStudyContract.Destination.TABLE_Destination, "", new String[0]);

    }

    public static void removeQuiz(Context context) {
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();
        ContentValues values = new ContentValues();

        /** delete selected listitem **/
        db.delete(ValaisStudyContract.Quiz.TABLE_Quiz, "", new String[0]);

    }

    public static void removeQuizProperties(Context context) {
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();
        ContentValues values = new ContentValues();

        /** delete selected listitem **/
        db.delete(ValaisStudyContract.QuizLevel.TABLE_QuizLevel, "", new String[0]);
        db.delete(ValaisStudyContract.QuizTheme.TABLE_QuizTheme, "", new String[0]);
        db.delete(ValaisStudyContract.QuizUserType.TABLE_QuizUserType, "", new String[0]);
    }

    public static void removeLearningOffline(Context context) {
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();
        ContentValues values = new ContentValues();

        /** delete selected listitem **/
        db.delete(ValaisStudyContract.LearningOffline.TABLE_LearningOffline, "", new String[0]);
    }

    public static void removeRemarks(Context context) {
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();
        ContentValues values = new ContentValues();

        /** delete selected listitem **/
        db.delete(ValaisStudyContract.Remark.TABLE_Remark, "", new String[0]);
    }

    public static void removeStatistics(Context context) {
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();
        ContentValues values = new ContentValues();

        /** delete selected listitem **/
        db.delete(ValaisStudyContract.StatisticQuizDest.TABLE_Statistics, "", new String[0]);
        db.delete(ValaisStudyContract.StatisticQuizTopic.TABLE_Statistics, "", new String[0]);
        db.delete(ValaisStudyContract.StatisticLearningDest.TABLE_Statistics, "", new String[0]);
        db.delete(ValaisStudyContract.StatisticLearningTopic.TABLE_Statistics, "", new String[0]);
    }

    //singleton pattern for sqllitehelper
    private static SQLiteOpenHelper getSQLiteOpenHelper(Context context){
        if(RemoveDataDB.dbHelper == null){
            RemoveDataDB.dbHelper = new ValaisStudyDBHelper(context);
        }
        return RemoveDataDB.dbHelper;
    }

}
