package com.hevs.y1617.valaisstudygrp2.db;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Acer on 18.11.2016.
 */

public class ValaisStudySQLCommands {

    private static final String NOTNULL = " TEXT NOT NULL";
    private static final String NULL = " TEXT NULL";
    private static final String NULLREAL = " REAL NULL";
    private static final String COMMA = ", ";

    /******************* CREATING TABLES *******************/
    //Create Destination table
    public static final String SQL_CREATE_DESTINATION =
            "CREATE TABLE " + ValaisStudyContract.Destination.TABLE_Destination + " (" +
                    ValaisStudyContract.Destination.COLUMN_Destination_NAME_ENTRY_ID + " INTEGER PRIMARY KEY, " +
                    ValaisStudyContract.Destination.COLUMN_Destination_NAME_DESTINATION + NOTNULL + ");";

    //Create Town table
    public static final String SQL_CREATE_TOWN =
            "CREATE TABLE " + ValaisStudyContract.Town.TABLE_Town + " (" +
                    ValaisStudyContract.Town.COLUMN_Town_NAME_ENTRY_ID + " INTEGER PRIMARY KEY, " +
                    ValaisStudyContract.Town.COLUMN_Town_ID_DESTINATION + " INTEGER" + COMMA +
                    ValaisStudyContract.Town.COLUMN_Town_NAME_TOWN + NOTNULL + COMMA +
                    ValaisStudyContract.Town.COLUMN_Town_NO_OFS + " INTEGER, " + "FOREIGN KEY (" +
                    ValaisStudyContract.Town.COLUMN_Town_ID_DESTINATION + ") REFERENCES " + ValaisStudyContract.Destination.TABLE_Destination +
                    "(" + ValaisStudyContract.Destination.COLUMN_Destination_NAME_ENTRY_ID + ") ON DELETE CASCADE );";


    //Create QuizTheme table
    public static final String SQL_CREATE_QUIZTHEME =
            "CREATE TABLE " + ValaisStudyContract.QuizTheme.TABLE_QuizTheme + " (" +
                    ValaisStudyContract.QuizTheme.COLUMN_QuizTheme_NAME_ENTRY_ID + " INTEGER PRIMARY KEY, " +
                    ValaisStudyContract.QuizTheme.COLUMN_QuizTheme_NAME_THEME_DE + NOTNULL + COMMA +
                    ValaisStudyContract.QuizTheme.COLUMN_QuizTheme_NAME_THEME_FR + NOTNULL + COMMA +
                    ValaisStudyContract.QuizTheme.COLUMN_QuizTheme_USE_FOR_QUESTION + " INTEGER " + NOTNULL +");";


    //Create QuizLevel table
    public static final String SQL_CREATE_QUIZLEVEL =
            "CREATE TABLE " + ValaisStudyContract.QuizLevel.TABLE_QuizLevel + " (" +
                    ValaisStudyContract.QuizLevel.COLUMN_QuizLevel_NAME_ENTRY_ID + " INTEGER PRIMARY KEY, " +
                    ValaisStudyContract.QuizLevel.COLUMN_QuizLevel_NAME_LEVEL_DE + NOTNULL + COMMA +
                    ValaisStudyContract.QuizLevel.COLUMN_QuizLevel_NAME_LEVEL_FR + NOTNULL + ");";


    //Create QuizLevel table
    public static final String SQL_CREATE_QUIZUSERTYPE =
            "CREATE TABLE " + ValaisStudyContract.QuizUserType.TABLE_QuizUserType + " (" +
                    ValaisStudyContract.QuizUserType.COLUMN_QuizUserType_NAME_ENTRY_ID + " INTEGER PRIMARY KEY, " +
                    ValaisStudyContract.QuizUserType.COLUMN_QuizUserType_NAME_USERTYPE_DE + NOTNULL + COMMA +
                    ValaisStudyContract.QuizUserType.COLUMN_QuizUserType_NAME_USERTYPE_FR + NOTNULL + ");";


    //Create Quiz table
    public static final String SQL_CREATE_QUIZ =
            "CREATE TABLE " + ValaisStudyContract.Quiz.TABLE_Quiz + " (" +
                    ValaisStudyContract.Quiz.COLUMN_Quiz_NAME_ENTRY_ID + " INTEGER PRIMARY KEY, " +
                    ValaisStudyContract.Quiz.COLUMN_Quiz_ID_THEME + " INTEGER" + COMMA +
                    ValaisStudyContract.Quiz.COLUMN_Quiz_ID_LEVEL + " INTEGER" + COMMA +
                    ValaisStudyContract.Quiz.COLUMN_Quiz_ID_USER_TYPE + " INTEGER" + COMMA +
                    ValaisStudyContract.Quiz.COLUMN_Quiz_QUESTION_DE + NOTNULL + COMMA +
                    ValaisStudyContract.Quiz.COLUMN_Quiz_QUESTION_FR + NOTNULL + COMMA +
                    ValaisStudyContract.Quiz.COLUMN_Quiz_IMAGE_LINK + NULL + COMMA +
                    ValaisStudyContract.Quiz.COLUMN_Quiz_IMAGE_SOURCE + NULL + COMMA +
                    "FOREIGN KEY (" + ValaisStudyContract.Quiz.COLUMN_Quiz_ID_THEME + ") REFERENCES " + ValaisStudyContract.QuizTheme.TABLE_QuizTheme +
                    "(" + ValaisStudyContract.QuizTheme.COLUMN_QuizTheme_NAME_ENTRY_ID  + ") ON DELETE CASCADE , " +
                    "FOREIGN KEY (" + ValaisStudyContract.Quiz.COLUMN_Quiz_ID_LEVEL + ") REFERENCES " + ValaisStudyContract.QuizLevel.TABLE_QuizLevel +
                    "(" + ValaisStudyContract.QuizLevel.COLUMN_QuizLevel_NAME_ENTRY_ID + ") ON DELETE CASCADE , " +
                    "FOREIGN KEY (" + ValaisStudyContract.Quiz.COLUMN_Quiz_ID_USER_TYPE + ") REFERENCES " + ValaisStudyContract.QuizUserType.TABLE_QuizUserType +
                    "(" + ValaisStudyContract.QuizUserType.COLUMN_QuizUserType_NAME_ENTRY_ID + ") ON DELETE CASCADE);";


    //Create QuizLevel table
    public static final String SQL_CREATE_QUIZANSWER =
            "CREATE TABLE " + ValaisStudyContract.QuizAnswer.TABLE_QuizAnswer + " (" +
                    ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_ID_QUESTION + " INTEGER" + COMMA +
                    ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_ID_DESTINATION + " INTEGER" + COMMA +
                    ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_GOOD_ANSWER_DE + NOTNULL + COMMA +
                    ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_GOOD_ANSWER_FR + NOTNULL + COMMA +
                    ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_BAD_ANSWER_DE + NOTNULL + COMMA +
                    ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_BAD_ANSWER_FR + NOTNULL + COMMA +
                    ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_INFO_ANSWER_DE + NOTNULL + COMMA +
                    ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_INFO_ANSWER_FR + NOTNULL + COMMA +
                    "FOREIGN KEY (" + ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_ID_QUESTION + ") REFERENCES " + ValaisStudyContract.Quiz.TABLE_Quiz +
                    "(" + ValaisStudyContract.Quiz.COLUMN_Quiz_NAME_ENTRY_ID + ") ON DELETE CASCADE , " +
                    "FOREIGN KEY (" + ValaisStudyContract.QuizAnswer.COLUMN_QuizAnswer_ID_DESTINATION + ") REFERENCES " + ValaisStudyContract.Destination.TABLE_Destination +
                    "(" + ValaisStudyContract.Destination.COLUMN_Destination_NAME_ENTRY_ID + ") ON DELETE CASCADE);";


    //Create LearningOffline table
    public static final String SQL_CREATE_LEARNINGOFFLINE =
            "CREATE TABLE " + ValaisStudyContract.LearningOffline.TABLE_LearningOffline + " (" +
                    ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_ID_DESTINATION + " INTEGER" + COMMA +
                    ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_ID_THEME + " INTEGER" + COMMA +
                    ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERBIG_DE + NULL + COMMA +
                    ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERBIG_FR + NULL + COMMA +
                    ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERLITTLE1_DE + NULL + COMMA +
                    ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERLITTLE1_FR + NULL + COMMA +
                    ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_PARAGRAPH1_DE + NULL + COMMA +
                    ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_PARAGRAPH1_FR + NULL + COMMA +
                    ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERLITTLE2_DE + NULL + COMMA +
                    ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERLITTLE2_FR + NULL + COMMA +
                    ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_LIST_DE + NULL + COMMA +
                    ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_LIST_FR + NULL + COMMA +
                    ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERLITTLE3_DE + NULL + COMMA +
                    ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_HEADERLITTLE3_FR + NULL + COMMA +
                    ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_PARAGRAPH2_DE + NULL + COMMA +
                    ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_PARAGRAPH2_FR + NULL + COMMA +
                    "FOREIGN KEY (" + ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_ID_DESTINATION + ") REFERENCES " + ValaisStudyContract.Destination.TABLE_Destination +
                    "(" + ValaisStudyContract.Destination.COLUMN_Destination_NAME_ENTRY_ID + ") ON DELETE CASCADE , " +
                    "FOREIGN KEY (" + ValaisStudyContract.LearningOffline.COLUMN_LearningOffline_ID_THEME + ") REFERENCES " + ValaisStudyContract.QuizTheme.TABLE_QuizTheme +
                    "(" + ValaisStudyContract.QuizTheme.COLUMN_QuizTheme_NAME_ENTRY_ID + ") ON DELETE CASCADE);";


    //Create QuizLevel table
    public static final String SQL_CREATE_REMARK =
            "CREATE TABLE " + ValaisStudyContract.Remark.TABLE_Remark + " (" +
                    ValaisStudyContract.Remark.COLUMN_Remark_ID_DESTINATION + " INTEGER" + COMMA +
                    ValaisStudyContract.Remark.COLUMN_Remark_ID_THEME + " INTEGER" + COMMA +
                    ValaisStudyContract.Remark.COLUMN_RemarkText + NULL + COMMA +
                    ValaisStudyContract.Remark.COLUMN_RemarkPoints + NULLREAL + COMMA +
                    "FOREIGN KEY (" + ValaisStudyContract.Remark.COLUMN_Remark_ID_DESTINATION + ") REFERENCES " + ValaisStudyContract.Destination.TABLE_Destination +
                    "(" + ValaisStudyContract.Destination.COLUMN_Destination_NAME_ENTRY_ID + ") ON DELETE CASCADE , " +
                    "FOREIGN KEY (" + ValaisStudyContract.Remark.COLUMN_Remark_ID_THEME + ") REFERENCES " + ValaisStudyContract.QuizTheme.TABLE_QuizTheme +
                    "(" + ValaisStudyContract.QuizTheme.COLUMN_QuizTheme_NAME_ENTRY_ID + ") ON DELETE CASCADE);";

    //Create Statistics table
    public static final String SQL_CREATE_StatisticQuizDest =
            "CREATE TABLE " + ValaisStudyContract.StatisticQuizDest.TABLE_Statistics + " (" +
                    ValaisStudyContract.StatisticQuizDest.COLUMN_Statistics_ID_DESTINATION + " INTEGER" + COMMA +
                    ValaisStudyContract.StatisticQuizDest.COLUMN_Counter +" INTEGER"+ COMMA +
                    "FOREIGN KEY (" + ValaisStudyContract.StatisticQuizDest.COLUMN_Statistics_ID_DESTINATION + ") REFERENCES " + ValaisStudyContract.Destination.TABLE_Destination +
                    "(" + ValaisStudyContract.Destination.COLUMN_Destination_NAME_ENTRY_ID + ") ON DELETE CASCADE);";

    public static final String SQL_CREATE_StatisticLearningDest =
            "CREATE TABLE " + ValaisStudyContract.StatisticLearningDest.TABLE_Statistics + " (" +
                    ValaisStudyContract.StatisticLearningDest.COLUMN_Statistics_ID_DESTINATION + " INTEGER" + COMMA +
                    ValaisStudyContract.StatisticLearningDest.COLUMN_Counter +" INTEGER"+ COMMA +
                    "FOREIGN KEY (" + ValaisStudyContract.StatisticLearningDest.COLUMN_Statistics_ID_DESTINATION + ") REFERENCES " + ValaisStudyContract.Destination.TABLE_Destination +
                    "(" + ValaisStudyContract.Destination.COLUMN_Destination_NAME_ENTRY_ID + ") ON DELETE CASCADE);";

    public static final String SQL_CREATE_StatisticQuizTopic =
            "CREATE TABLE " + ValaisStudyContract.StatisticQuizTopic.TABLE_Statistics + " (" +
                    ValaisStudyContract.StatisticQuizTopic.COLUMN_Statistics_ID_THEME + " INTEGER" + COMMA +
                    ValaisStudyContract.StatisticQuizTopic.COLUMN_Counter +" INTEGER"+ COMMA +
                    "FOREIGN KEY (" + ValaisStudyContract.StatisticQuizTopic.COLUMN_Statistics_ID_THEME + ") REFERENCES " + ValaisStudyContract.QuizTheme.TABLE_QuizTheme +
                    "(" + ValaisStudyContract.QuizTheme.COLUMN_QuizTheme_NAME_ENTRY_ID + ") ON DELETE CASCADE);";

    public static final String SQL_CREATE_StatisticLearningTopic =
            "CREATE TABLE " + ValaisStudyContract.StatisticLearningTopic.TABLE_Statistics + " (" +
                    ValaisStudyContract.StatisticLearningTopic.COLUMN_Statistics_ID_THEME + " INTEGER" + COMMA +
                    ValaisStudyContract.StatisticLearningTopic.COLUMN_Counter +" INTEGER"+ COMMA +
                    "FOREIGN KEY (" + ValaisStudyContract.StatisticLearningTopic.COLUMN_Statistics_ID_THEME + ") REFERENCES " + ValaisStudyContract.QuizTheme.TABLE_QuizTheme +
                    "(" + ValaisStudyContract.QuizTheme.COLUMN_QuizTheme_NAME_ENTRY_ID + ") ON DELETE CASCADE);";


    public static final String SQL_CREATE_LastUpdate =
            "CREATE TABLE " + ValaisStudyContract.LastUpdate.TABLE_LastUpdate+ " (" +
                    ValaisStudyContract.LastUpdate.COLUMN_LastUpdate_NAME_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ValaisStudyContract.LastUpdate.COLUMN_UPDATE + NULL +
                    ");";

    //Admins Login
    public static final String SQL_INSERT_LastUpdate =
            "INSERT INTO " + ValaisStudyContract.LastUpdate.TABLE_LastUpdate + " (" + ValaisStudyContract.LastUpdate.COLUMN_UPDATE + ") VALUES " +
                    "('"+ getFirstDate() +"');";

    private static String getFirstDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -14);
        return dateFormat.format(cal.getTime());
    }

    /******************* DROPPING TABLES *******************/
    // Team - DROP
    public static final String SQL_DELETE_DESTINATION =
            "DROP TABLE IF EXISTS " + ValaisStudyContract.Destination.TABLE_Destination;

    public static final String SQL_DELETE_TOWN =
            "DROP TABLE IF EXISTS " + ValaisStudyContract.Town.TABLE_Town;

    public static final String SQL_DELETE_THEME =
            "DROP TABLE IF EXISTS " + ValaisStudyContract.QuizTheme.TABLE_QuizTheme;

    public static final String SQL_DELETE_LEVEL =
            "DROP TABLE IF EXISTS " + ValaisStudyContract.QuizLevel.TABLE_QuizLevel;

    public static final String SQL_DELETE_TYPE =
            "DROP TABLE IF EXISTS " + ValaisStudyContract.QuizUserType.TABLE_QuizUserType;

    public static final String SQL_DELETE_QUIZ =
            "DROP TABLE IF EXISTS " + ValaisStudyContract.Quiz.TABLE_Quiz;

    public static final String SQL_DELETE_QUIZANSWER =
            "DROP TABLE IF EXISTS " + ValaisStudyContract.QuizAnswer.TABLE_QuizAnswer;

    public static final String SQL_DELETE_LEARNINGOFFLINE =
            "DROP TABLE IF EXISTS " + ValaisStudyContract.LearningOffline.TABLE_LearningOffline;

    public static final String SQL_DELETE_REMARK =
            "DROP TABLE IF EXISTS " + ValaisStudyContract.Remark.TABLE_Remark;

    public static final String SQL_DELETE_StatisticQuizDest =
            "DROP TABLE IF EXISTS " + ValaisStudyContract.StatisticQuizDest.TABLE_Statistics;

    public static final String SQL_DELETE_StatisticQuizTopic =
            "DROP TABLE IF EXISTS " + ValaisStudyContract.StatisticQuizDest.TABLE_Statistics;

    public static final String SQL_DELETE_StatisticLearningDest =
            "DROP TABLE IF EXISTS " + ValaisStudyContract.StatisticLearningDest.TABLE_Statistics;

    public static final String SQL_DELETE_StatisticLearningTopic =
            "DROP TABLE IF EXISTS " + ValaisStudyContract.StatisticLearningTopic.TABLE_Statistics;

    public static final String SQL_DELETE_LastUpdated =
            "DROP TABLE IF EXISTS " + ValaisStudyContract.LastUpdate.TABLE_LastUpdate;
}
