package com.hevs.y1617.valaisstudygrp2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Acer on 18.11.2016.
 */

public class ValaisStudyDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ValaisStudy.db";

    public ValaisStudyDBHelper(Context context) {
        //super(context, name, factory, version);
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Destination and Town
        db.execSQL(ValaisStudySQLCommands.SQL_CREATE_DESTINATION);
        db.execSQL(ValaisStudySQLCommands.SQL_CREATE_TOWN);

        //Create tables for quiz
        db.execSQL(ValaisStudySQLCommands.SQL_CREATE_QUIZTHEME);
        db.execSQL(ValaisStudySQLCommands.SQL_CREATE_QUIZLEVEL);
        db.execSQL(ValaisStudySQLCommands.SQL_CREATE_QUIZUSERTYPE);
        db.execSQL(ValaisStudySQLCommands.SQL_CREATE_QUIZ);
        db.execSQL(ValaisStudySQLCommands.SQL_CREATE_QUIZANSWER);

        //Create LearningOffline
        db.execSQL(ValaisStudySQLCommands.SQL_CREATE_LEARNINGOFFLINE);

        //Remarks
        db.execSQL(ValaisStudySQLCommands.SQL_CREATE_REMARK);

        //Statistics
        db.execSQL(ValaisStudySQLCommands.SQL_CREATE_StatisticQuizDest);
        db.execSQL(ValaisStudySQLCommands.SQL_CREATE_StatisticQuizTopic);
        db.execSQL(ValaisStudySQLCommands.SQL_CREATE_StatisticLearningDest);
        db.execSQL(ValaisStudySQLCommands.SQL_CREATE_StatisticLearningTopic);

        //lastupdate
        db.execSQL(ValaisStudySQLCommands.SQL_CREATE_LastUpdate);
        db.execSQL(ValaisStudySQLCommands.SQL_INSERT_LastUpdate);
        Log.i("Database", "db created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ValaisStudySQLCommands.SQL_DELETE_TOWN);
        db.execSQL(ValaisStudySQLCommands.SQL_DELETE_DESTINATION);
        db.execSQL(ValaisStudySQLCommands.SQL_DELETE_THEME);
        db.execSQL(ValaisStudySQLCommands.SQL_DELETE_LEVEL);
        db.execSQL(ValaisStudySQLCommands.SQL_DELETE_TYPE);
        db.execSQL(ValaisStudySQLCommands.SQL_DELETE_QUIZANSWER);
        db.execSQL(ValaisStudySQLCommands.SQL_DELETE_QUIZ);
        db.execSQL(ValaisStudySQLCommands.SQL_DELETE_LEARNINGOFFLINE);
        db.execSQL(ValaisStudySQLCommands.SQL_DELETE_REMARK);
        db.execSQL(ValaisStudySQLCommands.SQL_DELETE_StatisticQuizDest);
        db.execSQL(ValaisStudySQLCommands.SQL_DELETE_StatisticQuizTopic);
        db.execSQL(ValaisStudySQLCommands.SQL_DELETE_StatisticLearningDest);
        db.execSQL(ValaisStudySQLCommands.SQL_DELETE_StatisticLearningTopic);
        db.execSQL(ValaisStudySQLCommands.SQL_DELETE_LastUpdated);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    /* OPTIONAL - Called when the database needs to be downgraded!!
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    **/

}
