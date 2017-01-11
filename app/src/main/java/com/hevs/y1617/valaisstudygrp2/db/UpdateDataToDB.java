package com.hevs.y1617.valaisstudygrp2.db;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Acer on 05.12.2016.
 */

public class UpdateDataToDB {

    public static SQLiteOpenHelper dbHelper = null;
    public static ProgressDialog mDialog = null;

    public static void updateStatisticDest(Context context, int idChoice, int idDestination){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();

        /** new values from different or one column **/
        ContentValues values = new ContentValues();

        switch (idChoice){
            case 0:
                values.put(ValaisStudyContract.StatisticQuizDest.COLUMN_Counter, (GetDataDB.getCounterStatQuizDest(context, idDestination)+1));
                // which row to update - based on id from current list object
                String selection = ValaisStudyContract.StatisticQuizDest.COLUMN_Statistics_ID_DESTINATION + " LIKE ?";
                String [] selectionArgs = { String.valueOf(idDestination)};

                db.update(ValaisStudyContract.StatisticQuizDest.TABLE_Statistics, values, selection, selectionArgs);
                break;
            case 1:
                values.put(ValaisStudyContract.StatisticLearningDest.COLUMN_Counter, (GetDataDB.getCounterStatLearningDest(context, idDestination)+1));
                // which row to update - based on id from current list object
                String selection1 = ValaisStudyContract.StatisticLearningDest.COLUMN_Statistics_ID_DESTINATION + " LIKE ?";
                String [] selectionArgs1 = { String.valueOf(idDestination)};

                db.update(ValaisStudyContract.StatisticLearningDest.TABLE_Statistics, values, selection1, selectionArgs1);
                break;
        }

        db.close();

        Log.i("update statDest", "updated");
    }

    public static void updateStatisticTopic(Context context, int idChoice, int idTopic){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();

        /** new values from different or one column **/
        ContentValues values = new ContentValues();

        switch (idChoice){
            case 0:
                values.put(ValaisStudyContract.StatisticQuizTopic.COLUMN_Counter, (GetDataDB.getCounterStatQuizTopic(context, idTopic)+1));
                // which row to update - based on id from current list object
                String selection = ValaisStudyContract.StatisticQuizTopic.COLUMN_Statistics_ID_THEME + " LIKE ?";
                String [] selectionArgs = { String.valueOf(idTopic)};

                db.update(ValaisStudyContract.StatisticQuizTopic.TABLE_Statistics, values, selection, selectionArgs);
                break;
            case 1:
                values.put(ValaisStudyContract.StatisticLearningTopic.COLUMN_Counter, (GetDataDB.getCounterStatLearningTopic(context, idTopic)+1));
                // which row to update - based on id from current list object
                String selection1 = ValaisStudyContract.StatisticLearningTopic.COLUMN_Statistics_ID_THEME + " LIKE ?";
                String [] selectionArgs1 = { String.valueOf(idTopic)};

                db.update(ValaisStudyContract.StatisticLearningTopic.TABLE_Statistics, values, selection1, selectionArgs1);
                break;
        }
        db.close();

        Log.i("update statTopic", "updated");
    }

    public static void updateDateNew(Context context) {
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();

        /** new values from different or one column **/
        ContentValues values = new ContentValues();

        values.put(ValaisStudyContract.LastUpdate.COLUMN_UPDATE, getFirstDate());
        // which row to update - based on id from current list object
        String selection = ValaisStudyContract.LastUpdate.COLUMN_LastUpdate_NAME_ENTRY_ID + " LIKE ?";
        String [] selectionArgs = { 1 + ""};

        db.update(ValaisStudyContract.LastUpdate.TABLE_LastUpdate, values, selection, selectionArgs);
    }

    private static String getFirstDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    //singleton pattern for sqllitehelper
    private static SQLiteOpenHelper getSQLiteOpenHelper(Context context){
        if(UpdateDataToDB.dbHelper == null){
            UpdateDataToDB.dbHelper = new ValaisStudyDBHelper(context);
        }
        return UpdateDataToDB.dbHelper;
    }

}
