package com.hevs.y1617.valaisstudygrp2.db;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.acer.myapplication.backend.remarkApi.model.Remark;
import com.example.acer.myapplication.backend.statisticDestLearnApi.model.StatisticDestLearn;
import com.example.acer.myapplication.backend.statisticDestQuizApi.model.StatisticDestQuiz;
import com.example.acer.myapplication.backend.statisticTopLearnApi.model.StatisticTopLearn;
import com.example.acer.myapplication.backend.statisticTopQuizApi.model.StatisticTopQuiz;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Acer on 07.12.2016.
 */

public class GetDataDB {

    public static SQLiteOpenHelper dbHelper = null;
    public static ProgressDialog mDialog = null;

    public static List<Remark> getRemarks(Context context){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();
        String [] projection = {
                ValaisStudyContract.Remark.COLUMN_Remark_ID_DESTINATION,
                ValaisStudyContract.Remark.COLUMN_Remark_ID_THEME,
                ValaisStudyContract.Remark.COLUMN_RemarkPoints,
                ValaisStudyContract.Remark.COLUMN_RemarkText
        };

        Cursor c = db.query(
                ValaisStudyContract.Remark.TABLE_Remark,         // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        List<Remark> remarks = new ArrayList<Remark>();

        while (c.moveToNext()){
            String remarkText = c.getString(c.getColumnIndex(ValaisStudyContract.Remark.COLUMN_RemarkText));
            int idDestination = c.getInt(c.getColumnIndex(ValaisStudyContract.Remark.COLUMN_Remark_ID_DESTINATION));
            int idTopic = c.getInt(c.getColumnIndex(ValaisStudyContract.Remark.COLUMN_Remark_ID_THEME));
            double remarkPoints = c.getDouble(c.getColumnIndex(ValaisStudyContract.Remark.COLUMN_RemarkPoints));

            Remark remark = new Remark();
            remark.setIdDestination(idDestination);
            remark.setIdTheme(idTopic);
            remark.setRemarkText(remarkText);
            remark.setRemarkPoints(remarkPoints);

            remarks.add(remark);
        }
        return remarks;

    }

    public static List<StatisticDestQuiz> getStatisticDestQuiz(Context context){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();
        String [] projection = {
                ValaisStudyContract.StatisticQuizDest.COLUMN_Statistics_ID_DESTINATION,
                ValaisStudyContract.StatisticQuizDest.COLUMN_Counter
        };

        Cursor c = db.query(
                ValaisStudyContract.StatisticQuizDest.TABLE_Statistics,         // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        List<StatisticDestQuiz> statisticDestQuizs = new ArrayList<StatisticDestQuiz>();

        while (c.moveToNext()){
            int counter = c.getInt(c.getColumnIndex(ValaisStudyContract.StatisticQuizDest.COLUMN_Counter));
            if(counter != 0){
                int idDestination = c.getInt(c.getColumnIndex(ValaisStudyContract.StatisticQuizDest.COLUMN_Statistics_ID_DESTINATION));
                StatisticDestQuiz statisticDestQuiz = new StatisticDestQuiz();
                statisticDestQuiz.setIdDestination(idDestination);
                statisticDestQuiz.setStatCounter(counter);

                statisticDestQuizs.add(statisticDestQuiz);
            }
        }
        return statisticDestQuizs;

    }

    public static List<StatisticDestLearn> getStatisticDestLearn(Context context){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();
        String [] projection = {
                ValaisStudyContract.StatisticLearningDest.COLUMN_Statistics_ID_DESTINATION,
                ValaisStudyContract.StatisticLearningDest.COLUMN_Counter
        };

        Cursor c = db.query(
                ValaisStudyContract.StatisticLearningDest.TABLE_Statistics,         // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        List<StatisticDestLearn> statisticDestLearns = new ArrayList<StatisticDestLearn>();

        while (c.moveToNext()){
            int counter = c.getInt(c.getColumnIndex(ValaisStudyContract.StatisticLearningDest.COLUMN_Counter));
            if(counter != 0){
                int idDestination = c.getInt(c.getColumnIndex(ValaisStudyContract.StatisticLearningDest.COLUMN_Statistics_ID_DESTINATION));
                StatisticDestLearn statisticLearnQuiz = new StatisticDestLearn();
                statisticLearnQuiz.setIdDestination(idDestination);
                statisticLearnQuiz.setStatCounter(counter);

                statisticDestLearns.add(statisticLearnQuiz);
            }
        }
        return statisticDestLearns;

    }

    public static List<StatisticTopQuiz> getStatisticTopicQuiz(Context context){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();
        String [] projection = {
                ValaisStudyContract.StatisticQuizTopic.COLUMN_Statistics_ID_THEME,
                ValaisStudyContract.StatisticQuizTopic.COLUMN_Counter
        };

        Cursor c = db.query(
                ValaisStudyContract.StatisticQuizTopic.TABLE_Statistics,         // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        List<StatisticTopQuiz> statisticTopQuizs = new ArrayList<StatisticTopQuiz>();

        while (c.moveToNext()){
            int counter = c.getInt(c.getColumnIndex(ValaisStudyContract.StatisticQuizTopic.COLUMN_Counter));

            if(counter != 0){
                int idTopic = c.getInt(c.getColumnIndex(ValaisStudyContract.StatisticQuizTopic.COLUMN_Statistics_ID_THEME));
                StatisticTopQuiz statisticTopQuiz = new StatisticTopQuiz();
                statisticTopQuiz.setIdTopic(idTopic);
                statisticTopQuiz.setStatCounter(counter);

                statisticTopQuizs.add(statisticTopQuiz);
            }
        }
        return statisticTopQuizs;
    }

    public static List<StatisticTopLearn> getStatisticTopicLearn(Context context){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();
        String [] projection = {
                ValaisStudyContract.StatisticLearningTopic.COLUMN_Statistics_ID_THEME,
                ValaisStudyContract.StatisticLearningTopic.COLUMN_Counter
        };

        Cursor c = db.query(
                ValaisStudyContract.StatisticLearningTopic.TABLE_Statistics,         // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        List<StatisticTopLearn> statisticTopLearns = new ArrayList<StatisticTopLearn>();

        while (c.moveToNext()){
            int counter = c.getInt(c.getColumnIndex(ValaisStudyContract.StatisticLearningTopic.COLUMN_Counter));

            if(counter != 0){
                int idTopic = c.getInt(c.getColumnIndex(ValaisStudyContract.StatisticLearningTopic.COLUMN_Statistics_ID_THEME));
                StatisticTopLearn statisticTopLearn = new StatisticTopLearn();
                statisticTopLearn.setIdTopic(idTopic);
                statisticTopLearn.setStatCounter(counter);

                statisticTopLearns.add(statisticTopLearn);
            }
        }
        return statisticTopLearns;
    }

    public static int getCounterStatQuizDest(Context context, int idDestination){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();

        int currentCounter = 0;

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String [] projection = {
                ValaisStudyContract.StatisticQuizDest.COLUMN_Statistics_ID_DESTINATION,
                ValaisStudyContract.StatisticQuizDest.COLUMN_Counter
        };

        String whereClause = ValaisStudyContract.StatisticQuizDest.COLUMN_Statistics_ID_DESTINATION + "=?";
        String [] argsWhere = {idDestination + ""};

        Cursor c = db.query(
                ValaisStudyContract.StatisticQuizDest.TABLE_Statistics,         // The table to query
                projection,                               // The columns to return
                whereClause,                                // The columns for the WHERE clause
                argsWhere,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        while (c.moveToNext()){
            currentCounter = c.getInt(c.getColumnIndex(ValaisStudyContract.StatisticQuizDest.COLUMN_Counter));
            break;
        }
        return currentCounter;
    }

    public static int getCounterStatLearningDest(Context context, int idDestination){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();

        int currentCounter = 0;

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String [] projection = {
                ValaisStudyContract.StatisticLearningDest.COLUMN_Statistics_ID_DESTINATION,
                ValaisStudyContract.StatisticLearningDest.COLUMN_Counter
        };

        String whereClause = ValaisStudyContract.StatisticLearningDest.COLUMN_Statistics_ID_DESTINATION + "=?";
        String [] argsWhere = {idDestination + ""};

        Cursor c = db.query(
                ValaisStudyContract.StatisticLearningDest.TABLE_Statistics,         // The table to query
                projection,                               // The columns to return
                whereClause,                                // The columns for the WHERE clause
                argsWhere,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        while (c.moveToNext()){
            currentCounter = c.getInt(c.getColumnIndex(ValaisStudyContract.StatisticLearningDest.COLUMN_Counter));
            break;
        }
        return currentCounter;
    }

    public static int getCounterStatQuizTopic(Context context, int idTopic){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();

        int currentCounter = 0;

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String [] projection = {
                ValaisStudyContract.StatisticQuizTopic.COLUMN_Statistics_ID_THEME,
                ValaisStudyContract.StatisticQuizTopic.COLUMN_Counter
        };

        String whereClause = ValaisStudyContract.StatisticQuizTopic.COLUMN_Statistics_ID_THEME + "=?";
        String [] argsWhere = {idTopic + ""};

        Cursor c = db.query(
                ValaisStudyContract.StatisticQuizTopic.TABLE_Statistics,         // The table to query
                projection,                               // The columns to return
                whereClause,                                // The columns for the WHERE clause
                argsWhere,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        while (c.moveToNext()){
            currentCounter = c.getInt(c.getColumnIndex(ValaisStudyContract.StatisticQuizTopic.COLUMN_Counter));
            break;
        }
        return currentCounter;
    }

    public static int getCounterStatLearningTopic(Context context, int idTopic){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();

        int currentCounter = 0;

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String [] projection = {
                ValaisStudyContract.StatisticLearningTopic.COLUMN_Statistics_ID_THEME,
                ValaisStudyContract.StatisticLearningTopic.COLUMN_Counter
        };

        String whereClause = ValaisStudyContract.StatisticLearningTopic.COLUMN_Statistics_ID_THEME + "=?";
        String [] argsWhere = {idTopic + ""};

        Cursor c = db.query(
                ValaisStudyContract.StatisticLearningTopic.TABLE_Statistics,         // The table to query
                projection,                               // The columns to return
                whereClause,                                // The columns for the WHERE clause
                argsWhere,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        while (c.moveToNext()){
            currentCounter = c.getInt(c.getColumnIndex(ValaisStudyContract.StatisticLearningTopic.COLUMN_Counter));
            break;
        }
        return currentCounter;
    }


    public static boolean updateReq(Context context){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String [] projection = {
                ValaisStudyContract.LastUpdate.COLUMN_LastUpdate_NAME_ENTRY_ID,
                ValaisStudyContract.LastUpdate.COLUMN_UPDATE
        };

        String whereClause = ValaisStudyContract.LastUpdate.COLUMN_LastUpdate_NAME_ENTRY_ID + "=?";
        String [] argsWhere = {1 + ""};

        Cursor c = db.query(
                ValaisStudyContract.LastUpdate.TABLE_LastUpdate,         // The table to query
                projection,                               // The columns to return
                whereClause,                                // The columns for the WHERE clause
                argsWhere,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        String oldDateString = "";

        while (c.moveToNext()){
            oldDateString = c.getString(c.getColumnIndex(ValaisStudyContract.LastUpdate.COLUMN_UPDATE));
            break;
        }

        Date oldDate = stringToDate(oldDateString);

        Date newDate = Calendar.getInstance().getTime();

        long diff = oldDate.getTime() - newDate.getTime();
        long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        Log.d("Days", days + "");
        if((days*-1) > 13) {
            UpdateDataToDB.updateDateNew(context);
            return true;
        }
        return false;
    }

    private static Date stringToDate(String stringInstanceRepresentingDate){
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal  = Calendar.getInstance();
        try {
            cal.setTime(df.parse(stringInstanceRepresentingDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal.getTime();
    }

    //singleton pattern for sqllitehelper
    private static SQLiteOpenHelper getSQLiteOpenHelper(Context context){
        if(GetDataDB.dbHelper == null){
            GetDataDB.dbHelper = new ValaisStudyDBHelper(context);
        }
        return GetDataDB.dbHelper;
    }

}
