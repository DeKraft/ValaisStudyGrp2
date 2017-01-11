package com.hevs.y1617.valaisstudygrp2.db;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hevs.y1617.valaisstudygrp2.AsyncObjects.RestDestination;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.RestQuizTheme;
import com.hevs.y1617.valaisstudygrp2.DataObjects.Destination;
import com.hevs.y1617.valaisstudygrp2.DataObjects.QuizTheme;

/**
 * Created by Acer on 05.12.2016.
 */

public class InsertDataToDB {

    public static SQLiteOpenHelper dbHelper = null;
    public static ProgressDialog mDialog = null;

    public static void insertStatisticQuizDestToDB(Context context){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();
        ContentValues values = new ContentValues();

        //Set mDialog
        mDialog = ProgressDialog.show(context,
                "Initialize Data", "Wait ...", true, true);

        for(Destination d : RestDestination.destinationList){
            values.put(ValaisStudyContract.StatisticQuizDest.COLUMN_Statistics_ID_DESTINATION, d.getIdDestination());
            values.put(ValaisStudyContract.StatisticQuizDest.COLUMN_Counter, 0);

            db.insert(ValaisStudyContract.StatisticQuizDest.TABLE_Statistics, null, values);
        }
        db.close();
        mDialog.dismiss();
    }

    public static void insertStatisticLearningDestToDB(Context context){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();
        ContentValues values = new ContentValues();

        //Set mDialog
        mDialog = ProgressDialog.show(context,
                "Initialize Data", "Wait ...", true, true);

        for(Destination d : RestDestination.destinationList) {
            values.put(ValaisStudyContract.StatisticLearningDest.COLUMN_Statistics_ID_DESTINATION, d.getIdDestination());
            values.put(ValaisStudyContract.StatisticLearningDest.COLUMN_Counter, 0);

            db.insert(ValaisStudyContract.StatisticLearningDest.TABLE_Statistics, null, values);
        }
        db.close();
        mDialog.dismiss();
    }

    public static void insertStatisticQuizTopicToDB(Context context){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();
        ContentValues values = new ContentValues();

        //Set mDialog
        mDialog = ProgressDialog.show(context,
                "Initialize Data", "Wait ...", true, true);

        for(QuizTheme t : RestQuizTheme.themeList){
            values.put(ValaisStudyContract.StatisticQuizTopic.COLUMN_Statistics_ID_THEME, t.getIdTheme());
            values.put(ValaisStudyContract.StatisticQuizTopic.COLUMN_Counter, 0);

            db.insert(ValaisStudyContract.StatisticQuizTopic.TABLE_Statistics, null, values);
        }
        db.close();
        mDialog.dismiss();
    }

    public static void insertStatisticLearningTopicToDB(Context context){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();
        ContentValues values = new ContentValues();

        //Set mDialog
        mDialog = ProgressDialog.show(context,
                "Initialize Data", "Wait ...", true, true);

        for(QuizTheme t : RestQuizTheme.themeList){
            values.put(ValaisStudyContract.StatisticLearningTopic.COLUMN_Statistics_ID_THEME, t.getIdTheme());
            values.put(ValaisStudyContract.StatisticLearningTopic.COLUMN_Counter, 0);

            db.insert(ValaisStudyContract.StatisticLearningTopic.TABLE_Statistics, null, values);
        }
        db.close();
        mDialog.dismiss();
    }

    public static void insertRemarkToDB(Context context, String remarkText, int idTopic, int idDest, float remarkPoints){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();
        ContentValues values = new ContentValues();

        //Set mDialog
        mDialog = ProgressDialog.show(context,
                "Initialize Data", "Wait ...", true, true);

        values.put(ValaisStudyContract.Remark.COLUMN_Remark_ID_DESTINATION, idDest);
        values.put(ValaisStudyContract.Remark.COLUMN_Remark_ID_THEME, idTopic);
        values.put(ValaisStudyContract.Remark.COLUMN_RemarkPoints, remarkPoints);
        values.put(ValaisStudyContract.Remark.COLUMN_RemarkText, remarkText);
        db.insert(ValaisStudyContract.Remark.TABLE_Remark, null, values);

        db.close();
        mDialog.dismiss();
    }

    //singleton pattern for sqllitehelper
    private static SQLiteOpenHelper getSQLiteOpenHelper(Context context){
        if(InsertDataToDB.dbHelper == null){
            InsertDataToDB.dbHelper = new ValaisStudyDBHelper(context);
        }
        return InsertDataToDB.dbHelper;
    }

}
