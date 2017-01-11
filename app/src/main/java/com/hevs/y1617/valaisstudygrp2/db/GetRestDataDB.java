package com.hevs.y1617.valaisstudygrp2.db;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.hevs.y1617.valaisstudygrp2.DataObjects.Destination;
import com.hevs.y1617.valaisstudygrp2.DataObjects.LearningOffline;
import com.hevs.y1617.valaisstudygrp2.DataObjects.Quiz;
import com.hevs.y1617.valaisstudygrp2.DataObjects.QuizAnswer;
import com.hevs.y1617.valaisstudygrp2.DataObjects.QuizUserType;

import java.util.ArrayList;

/**
 * Created by Acer on 25.11.2016.
 */

public class GetRestDataDB {

    public static SQLiteOpenHelper dbHelper = null;
    public static ProgressDialog mDialog = null;

    public static ArrayList<Destination> getDestinationFromDB(Context context){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ValaisStudyContract.Destination.COLUMN_Destination_NAME_ENTRY_ID,
                ValaisStudyContract.Destination.COLUMN_Destination_NAME_DESTINATION
        };

        Cursor c = db.query(
                ValaisStudyContract.Destination.TABLE_Destination,         // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        ArrayList<Destination> destinations = new ArrayList<>();

        while (c.moveToNext()) {
            int idDestionation = c.getInt(c.getColumnIndex(ValaisStudyContract.Destination.COLUMN_Destination_NAME_ENTRY_ID));
            String destinationName = c.getString(c.getColumnIndex(ValaisStudyContract.Destination.COLUMN_Destination_NAME_DESTINATION));
            destinations.add(new Destination(idDestionation, destinationName));
        }
        if(destinations.size() != 0) {
            destinations.remove(destinations.size() - 1);
        }

        return destinations;
    }

    public static Destination getDestinationWithIDFromDB(Context context, int curidDestination){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ValaisStudyContract.Destination.COLUMN_Destination_NAME_ENTRY_ID,
                ValaisStudyContract.Destination.COLUMN_Destination_NAME_DESTINATION
        };

        Cursor c = db.query(
                ValaisStudyContract.Destination.TABLE_Destination,         // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        while (c.moveToNext()) {
            int idDestination = c.getInt(c.getColumnIndex(ValaisStudyContract.Destination.COLUMN_Destination_NAME_ENTRY_ID));
            if(idDestination == curidDestination) {
                String destinationName = c.getString(c.getColumnIndex(ValaisStudyContract.Destination.COLUMN_Destination_NAME_DESTINATION));
                return new Destination(idDestination, destinationName);
            }
        }
        return null;
    }

    public static ArrayList<QuizUserType> getUserTypeFromDB(Context context){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ValaisStudyContract.QuizUserType.COLUMN_QuizUserType_NAME_ENTRY_ID,
                ValaisStudyContract.QuizUserType.COLUMN_QuizUserType_NAME_USERTYPE_DE,
                ValaisStudyContract.QuizUserType.COLUMN_QuizUserType_NAME_USERTYPE_FR
        };

        Cursor c = db.query(
                ValaisStudyContract.QuizUserType.TABLE_QuizUserType,         // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        ArrayList<QuizUserType> quizUserTypes = new ArrayList<>();

        while (c.moveToNext()) {
            int idUserType = c.getInt(c.getColumnIndex(ValaisStudyContract.QuizUserType.COLUMN_QuizUserType_NAME_ENTRY_ID));
            String userTypeDE = c.getString(c.getColumnIndex(ValaisStudyContract.QuizUserType.COLUMN_QuizUserType_NAME_USERTYPE_DE));
            String userTypeFR = c.getString(c.getColumnIndex(ValaisStudyContract.QuizUserType.COLUMN_QuizUserType_NAME_USERTYPE_FR));
            quizUserTypes.add(new QuizUserType(idUserType, userTypeFR, userTypeDE));
        }
        return quizUserTypes;
    }

    public static QuizUserType getUserTypeWithIdFromDB(Context context, int userTypeId){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ValaisStudyContract.QuizUserType.COLUMN_QuizUserType_NAME_ENTRY_ID,
                ValaisStudyContract.QuizUserType.COLUMN_QuizUserType_NAME_USERTYPE_DE,
                ValaisStudyContract.QuizUserType.COLUMN_QuizUserType_NAME_USERTYPE_FR
        };

        Cursor c = db.query(
                ValaisStudyContract.QuizUserType.TABLE_QuizUserType,         // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        while (c.moveToNext()) {
            int idUserType = c.getInt(c.getColumnIndex(ValaisStudyContract.QuizUserType.COLUMN_QuizUserType_NAME_ENTRY_ID));
            if(idUserType == userTypeId){
                String userTypeDE = c.getString(c.getColumnIndex(ValaisStudyContract.QuizUserType.COLUMN_QuizUserType_NAME_USERTYPE_DE));
                String userTypeFR = c.getString(c.getColumnIndex(ValaisStudyContract.QuizUserType.COLUMN_QuizUserType_NAME_USERTYPE_FR));
                return new QuizUserType(idUserType, userTypeFR, userTypeDE);
            }
        }
        return null;
    }

    public static ArrayList<Quiz> getQuizFromDB(Context context){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ValaisStudyContract.Quiz.COLUMN_Quiz_NAME_ENTRY_ID,
                ValaisStudyContract.Quiz.COLUMN_Quiz_ID_THEME,
                ValaisStudyContract.Quiz.COLUMN_Quiz_ID_LEVEL,
                ValaisStudyContract.Quiz.COLUMN_Quiz_ID_USER_TYPE,
                ValaisStudyContract.Quiz.COLUMN_Quiz_QUESTION_DE,
                ValaisStudyContract.Quiz.COLUMN_Quiz_QUESTION_FR,
                ValaisStudyContract.Quiz.COLUMN_Quiz_IMAGE_LINK,
                ValaisStudyContract.Quiz.COLUMN_Quiz_IMAGE_SOURCE
        };

        Cursor c = db.query(
                ValaisStudyContract.Quiz.TABLE_Quiz,         // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        ArrayList<Quiz> quizList = new ArrayList<>();

        while (c.moveToNext()) {
            int idQuiz = c.getInt(c.getColumnIndex(ValaisStudyContract.Quiz.COLUMN_Quiz_NAME_ENTRY_ID));
            int idTheme = c.getInt(c.getColumnIndex(ValaisStudyContract.Quiz.COLUMN_Quiz_ID_THEME));
            int idLevel = c.getInt(c.getColumnIndex(ValaisStudyContract.Quiz.COLUMN_Quiz_ID_LEVEL));
            int idUserType = c.getInt(c.getColumnIndex(ValaisStudyContract.Quiz.COLUMN_Quiz_ID_USER_TYPE));
            String questionDE = c.getString(c.getColumnIndex(ValaisStudyContract.Quiz.COLUMN_Quiz_QUESTION_DE));
            String questionFR = c.getString(c.getColumnIndex(ValaisStudyContract.Quiz.COLUMN_Quiz_QUESTION_FR));
            String imageLink = c.getString(c.getColumnIndex(ValaisStudyContract.Quiz.COLUMN_Quiz_IMAGE_LINK));
            String imageSource = c.getString(c.getColumnIndex(ValaisStudyContract.Quiz.COLUMN_Quiz_IMAGE_LINK));

            quizList.add(new Quiz(idQuiz, idTheme, idLevel, idUserType, questionDE, questionFR, imageLink, imageSource));
        }
        Log.i("", quizList.toString());
        return quizList;
    }

    public static ArrayList<QuizAnswer> getQuizAnswersFromDB(Context context){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_ID_QUESTION,
                ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_ID_DESTINATION,
                ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_GOOD_ANSWER_DE,
                ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_GOOD_ANSWER_FR,
                ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_BAD_ANSWER_DE,
                ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_BAD_ANSWER_FR,
                ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_INFO_ANSWER_DE,
                ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_INFO_ANSWER_FR
        };

        Cursor c = db.query(
                ValaisStudyContract.QuizAnswer.TABLE_QuizAnswer,         // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        ArrayList<QuizAnswer> quizAnswerList = new ArrayList<>();

        while (c.moveToNext()) {
            int idQuestion = c.getInt(c.getColumnIndex(ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_ID_QUESTION));
            int idDestination = c.getInt(c.getColumnIndex(ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_ID_DESTINATION));
            String goodAnswerDE = c.getString(c.getColumnIndex(ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_GOOD_ANSWER_DE));
            String goodAnswerFR = c.getString(c.getColumnIndex(ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_GOOD_ANSWER_FR));
            String badAnswerDE = c.getString(c.getColumnIndex(ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_BAD_ANSWER_DE));
            String badAnswerFR = c.getString(c.getColumnIndex(ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_BAD_ANSWER_FR));
            String infoAnswerDE = c.getString(c.getColumnIndex(ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_INFO_ANSWER_DE));
            String infoAnswerFR = c.getString(c.getColumnIndex(ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_INFO_ANSWER_FR));

            quizAnswerList.add(new QuizAnswer(idQuestion, idDestination, goodAnswerFR, goodAnswerDE, badAnswerFR, badAnswerDE,
                    infoAnswerFR, infoAnswerDE));
        }
        Log.i("", quizAnswerList.toString());
        return quizAnswerList;

    }

    public static ArrayList<LearningOffline> getLearningInfosFromDB(Context context){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_ID_DESTINATION,
                ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_ID_THEME,
                ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERBIG_DE,
                ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERBIG_FR,
                ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERLITTLE1_DE,
                ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERLITTLE1_FR,
                ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_PARAGRAPH1_DE,
                ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_PARAGRAPH1_FR,
                ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERLITTLE2_DE,
                ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERLITTLE2_FR,
                ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_LIST_DE,
                ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_LIST_FR,
                ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERLITTLE3_DE,
                ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERLITTLE3_FR,
                ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_PARAGRAPH2_DE,
                ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_PARAGRAPH2_FR
        };

        Cursor c = db.query(
                ValaisStudyContract.LearningOffline.TABLE_LearningOffline,         // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        ArrayList<LearningOffline> quizLearningList = new ArrayList<>();

        while (c.moveToNext()) {
            int idTheme = c.getInt(c.getColumnIndex(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_ID_THEME));
            int idDestination = c.getInt(c.getColumnIndex(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_ID_DESTINATION));
            String headerBigFR = c.getString(c.getColumnIndex(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERBIG_FR));
            String headerLittle1FR = c.getString(c.getColumnIndex(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERLITTLE1_FR));
            String paragraph1FR = c.getString(c.getColumnIndex(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_PARAGRAPH1_FR));
            String headerLittle2FR = c.getString(c.getColumnIndex(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERLITTLE2_FR));
            String listFR = c.getString(c.getColumnIndex(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_LIST_FR));
            String headerLittle3FR = c.getString(c.getColumnIndex(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERLITTLE3_FR));
            String paragraph2FR = c.getString(c.getColumnIndex(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_PARAGRAPH2_FR));
            String headerBigDE = c.getString(c.getColumnIndex(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERBIG_DE));
            String headerLittle1DE = c.getString(c.getColumnIndex(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERLITTLE1_DE));
            String paragraph1DE = c.getString(c.getColumnIndex(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_PARAGRAPH1_DE));
            String headerLittle2DE = c.getString(c.getColumnIndex(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERLITTLE2_DE));
            String listDE = c.getString(c.getColumnIndex(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_LIST_DE));
            String headerLittle3DE = c.getString(c.getColumnIndex(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERLITTLE3_DE));
            String paragraph2DE = c.getString(c.getColumnIndex(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_PARAGRAPH2_DE));

            quizLearningList.add(new LearningOffline(idDestination, idTheme, headerBigFR, headerLittle1FR, paragraph1FR, headerLittle2FR,
                    listFR, headerLittle3FR, paragraph2FR, headerBigDE, headerLittle1DE, paragraph1DE, headerLittle2DE, listDE, headerLittle3DE,
                    paragraph2DE));
        }
        Log.i("learningList", quizLearningList.toString());
        return quizLearningList;
    }


    //singleton pattern for sqllitehelper
    private static SQLiteOpenHelper getSQLiteOpenHelper(Context context){
        if(InsertRestDataDB.dbHelper == null){
            InsertRestDataDB.dbHelper = new ValaisStudyDBHelper(context);
        }
        return InsertRestDataDB.dbHelper;
    }

}
