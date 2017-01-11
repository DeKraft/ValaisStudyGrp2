package com.hevs.y1617.valaisstudygrp2.db;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.hevs.y1617.valaisstudygrp2.AsyncObjects.RestDestination;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.RestLearningOffline;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.RestQuiz;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.RestQuizAnswer;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.RestQuizLevel;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.RestQuizTheme;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.RestQuizUserType;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.RestTown;
import com.hevs.y1617.valaisstudygrp2.DataObjects.Destination;
import com.hevs.y1617.valaisstudygrp2.DataObjects.LearningOffline;
import com.hevs.y1617.valaisstudygrp2.DataObjects.Quiz;
import com.hevs.y1617.valaisstudygrp2.DataObjects.QuizAnswer;
import com.hevs.y1617.valaisstudygrp2.DataObjects.QuizLevel;
import com.hevs.y1617.valaisstudygrp2.DataObjects.QuizTheme;
import com.hevs.y1617.valaisstudygrp2.DataObjects.QuizUserType;
import com.hevs.y1617.valaisstudygrp2.DataObjects.Town;

/**
 * Created by Acer on 24.11.2016.
 */

public class InsertRestDataDB {

    public static SQLiteOpenHelper dbHelper = null;
    public static ProgressDialog mDialog = null;

    public static void insertDestinationToDB(Context context){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();
        ContentValues values = new ContentValues();

        //Set mDialog
        mDialog = ProgressDialog.show(context,
                "Initialize Data", "Wait ...", true, true);

        for(Destination d : RestDestination.destinationList){
            values.put(ValaisStudyContract.Destination.COLUMN_Destination_NAME_ENTRY_ID, d.getIdDestination());
            values.put(ValaisStudyContract.Destination.COLUMN_Destination_NAME_DESTINATION, d.getName_destination());

            db.insert(ValaisStudyContract.Destination.TABLE_Destination, null, values);
            Log.i("insert ","Destination " + d.getIdDestination());
        }

        values.put(ValaisStudyContract.Destination.COLUMN_Destination_NAME_ENTRY_ID, 50);
        values.put(ValaisStudyContract.Destination.COLUMN_Destination_NAME_DESTINATION, "all");

        db.insert(ValaisStudyContract.Destination.TABLE_Destination, null, values);

        db.close();
        mDialog.dismiss();
    }

    public static void insertTown(Context context){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();
        ContentValues values = new ContentValues();

        //Set mDialog
        mDialog = ProgressDialog.show(context,
                "Initialize Data", "Wait ...", true, true);

        for(Town t : RestTown.townList){
            values.put(ValaisStudyContract.Town.COLUMN_Town_NAME_ENTRY_ID, t.getIdTown());
            values.put(ValaisStudyContract.Town.COLUMN_Town_NO_OFS, t.getNo_ofs());
            values.put(ValaisStudyContract.Town.COLUMN_Town_NAME_TOWN, t.getName_town());
            values.put(ValaisStudyContract.Town.COLUMN_Town_ID_DESTINATION, t.getFk_idDestination());

            db.insert(ValaisStudyContract.Town.TABLE_Town, null, values);
            Log.i("insert ","Town " + t.getIdTown());
        }
        db.close();
        mDialog.dismiss();
    }

    public static void insertQuizTheme(Context context){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();
        ContentValues values = new ContentValues();

        //Set mDialog
        mDialog = ProgressDialog.show(context,
                "Initialize Data", "Wait ...", true, true);

        for(QuizTheme t : RestQuizTheme.themeList){
            values.put(ValaisStudyContract.QuizTheme.COLUMN_QuizTheme_NAME_ENTRY_ID, t.getIdTheme());
            values.put(ValaisStudyContract.QuizTheme.COLUMN_QuizTheme_NAME_THEME_DE, t.getNameThemeDE());
            values.put(ValaisStudyContract.QuizTheme.COLUMN_QuizTheme_NAME_THEME_FR, t.getNameThemeFR());
            values.put(ValaisStudyContract.QuizTheme.COLUMN_QuizTheme_USE_FOR_QUESTION, t.isUserForQuetion());

            db.insert(ValaisStudyContract.QuizTheme.TABLE_QuizTheme, null, values);
            Log.i("insert ","QuizTheme " + t.getIdTheme());
        }
        db.close();
        mDialog.dismiss();
    }

    public static void insertQuizLevel(Context context){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();
        ContentValues values = new ContentValues();

        //Set mDialog
        mDialog = ProgressDialog.show(context,
                "Initialize Data", "Wait ...", true, true);

        for(QuizLevel l : RestQuizLevel.levelList){
            values.put(ValaisStudyContract.QuizLevel.COLUMN_QuizLevel_NAME_ENTRY_ID, l.getIdLevel());
            values.put(ValaisStudyContract.QuizLevel.COLUMN_QuizLevel_NAME_LEVEL_DE, l.getNameLevelDE());
            values.put(ValaisStudyContract.QuizLevel.COLUMN_QuizLevel_NAME_LEVEL_FR, l.getNameLevelFR());

            db.insert(ValaisStudyContract.QuizLevel.TABLE_QuizLevel, null, values);
            Log.i("insert ","QuizLevel " + l.getIdLevel());
        }
        db.close();
        mDialog.dismiss();
    }

    public static void insertQuizUserType(Context context){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();
        ContentValues values = new ContentValues();

        //Set mDialog
        mDialog = ProgressDialog.show(context,
                "Initialize Data", "Wait ...", true, true);

        for(QuizUserType t : RestQuizUserType.usertypeList){
            values.put(ValaisStudyContract.QuizUserType.COLUMN_QuizUserType_NAME_ENTRY_ID, t.getIdUserType());
            values.put(ValaisStudyContract.QuizUserType.COLUMN_QuizUserType_NAME_USERTYPE_DE, t.getNameUserTypeDE());
            values.put(ValaisStudyContract.QuizUserType.COLUMN_QuizUserType_NAME_USERTYPE_FR, t.getNameUserTypeFR());

            db.insert(ValaisStudyContract.QuizUserType.TABLE_QuizUserType, null, values);
            Log.i("insert ","QuizUserType " + t.getIdUserType());
        }
        db.close();
        mDialog.dismiss();
    }

    public static void insertQuiz(Context context){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();
        ContentValues values = new ContentValues();

        //Set mDialog
        mDialog = ProgressDialog.show(context,
                "Initialize Data", "Wait ...", true, true);

        for(Quiz q : RestQuiz.quizList){
            values.put(ValaisStudyContract.Quiz.COLUMN_Quiz_NAME_ENTRY_ID, q.getIdQuestion());
            values.put(ValaisStudyContract.Quiz.COLUMN_Quiz_ID_THEME, q.getFk_Theme());
            values.put(ValaisStudyContract.Quiz.COLUMN_Quiz_ID_LEVEL, q.getFk_Level());
            values.put(ValaisStudyContract.Quiz.COLUMN_Quiz_ID_USER_TYPE, q.getFk_userType());
            values.put(ValaisStudyContract.Quiz.COLUMN_Quiz_QUESTION_DE, q.getQuestionDE());
            values.put(ValaisStudyContract.Quiz.COLUMN_Quiz_QUESTION_FR, q.getQuestionFR());
            values.put(ValaisStudyContract.Quiz.COLUMN_Quiz_IMAGE_LINK, q.getImageLink());
            values.put(ValaisStudyContract.Quiz.COLUMN_Quiz_IMAGE_SOURCE, q.getImageSource());

            db.insert(ValaisStudyContract.Quiz.TABLE_Quiz, null, values);
            Log.i("insert ","Quiz " + q.getIdQuestion());
        }
        db.close();
        mDialog.dismiss();
    }

    public static void insertQuizAnswer(Context context){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();
        ContentValues values = new ContentValues();

        //Set mDialog
        mDialog = ProgressDialog.show(context,
                "Initialize Data", "Wait ...", true, true);

        for(QuizAnswer q : RestQuizAnswer.quizAnswerList){
            values.put(ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_ID_QUESTION, q.getFk_QuestionId());
            values.put(ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_ID_DESTINATION, q.getFk_DestinationId());
            values.put(ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_GOOD_ANSWER_DE, q.getGoodAnswerDE());
            values.put(ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_GOOD_ANSWER_FR, q.getGoodAnswerFR());
            values.put(ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_BAD_ANSWER_DE, q.getBadAnswerDE());
            values.put(ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_BAD_ANSWER_FR, q.getBadAnswerFR());
            values.put(ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_INFO_ANSWER_DE, q.getInfoAnswerDE());
            values.put(ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_INFO_ANSWER_FR, q.getInfoAnswerFR());

            db.insert(ValaisStudyContract.QuizAnswer.TABLE_QuizAnswer, null, values);
            Log.i("insert ","QuizAnswer " + q.getFk_QuestionId() + " " + q.getFk_DestinationId());
        }
        db.close();
        mDialog.dismiss();
    }

    public static void insertLearningOffline(Context context){
        SQLiteDatabase db = getSQLiteOpenHelper(context).getReadableDatabase();
        ContentValues values = new ContentValues();

        //Set mDialog
        mDialog = ProgressDialog.show(context,
                "Initialize Data", "Wait ...", true, true);

        for(LearningOffline l : RestLearningOffline.learningList){
            values.put(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_ID_DESTINATION, l.getIdDestination());
            values.put(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_ID_THEME, l.getIdTheme());
            values.put(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERBIG_DE, l.getHeaderBigDE());
            values.put(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERBIG_FR, l.getHeaderBigFR());
            values.put(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERLITTLE1_DE, l.getHeaderLittle1DE());
            values.put(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERLITTLE1_FR, l.getHeaderLittle1FR());
            values.put(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_PARAGRAPH1_DE, l.getParagraph1DE());
            values.put(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_PARAGRAPH1_FR, l.getParagraph1FR());
            values.put(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERLITTLE2_DE, l.getHeaderLittle2DE());
            values.put(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERLITTLE2_FR, l.getHeaderLittle2FR());
            values.put(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_LIST_DE, l.getListDE());
            values.put(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_LIST_FR, l.getListFR());
            values.put(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERLITTLE3_DE, l.getHeaderLittle2DE());
            values.put(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERLITTLE3_FR, l.getHeaderLittle2FR());
            values.put(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_PARAGRAPH2_DE, l.getParagraph2DE());
            values.put(ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_PARAGRAPH2_FR, l.getParagraph2FR());

            db.insert(ValaisStudyContract.LearningOffline.TABLE_LearningOffline, null, values);
            Log.i("insert ","LearningOffline " + l.getIdDestination() + " " + l.getIdTheme());
        }
        db.close();
        mDialog.dismiss();

    }

    //singleton pattern for sqllitehelper
    private static SQLiteOpenHelper getSQLiteOpenHelper(Context context){
        if(InsertRestDataDB.dbHelper == null){
            InsertRestDataDB.dbHelper = new ValaisStudyDBHelper(context);
        }
        return InsertRestDataDB.dbHelper;
    }

}
