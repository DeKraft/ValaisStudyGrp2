package com.hevs.y1617.valaisstudygrp2.db;

import android.provider.BaseColumns;

/**
 * Created by Acer on 18.11.2016.
 */

public class ValaisStudyContract {

    public ValaisStudyContract(){}

    public static abstract class Destination implements BaseColumns {
        public static final String TABLE_Destination = "Destination";

        public static final String COLUMN_Destination_NAME_ENTRY_ID = "idDestination";
        public static final String COLUMN_Destination_NAME_DESTINATION = "Name_Destination";
    }

    public static abstract class Town implements BaseColumns {
        public static final String TABLE_Town = "Town";

        public static final String COLUMN_Town_NAME_ENTRY_ID = "idTown";
        public static final String COLUMN_Town_NO_OFS = "No_OFS";
        public static final String COLUMN_Town_NAME_TOWN = "Name_Town";

        // foreign key! idDestination
        public static final String COLUMN_Town_ID_DESTINATION = "idDestination";
    }

    public static abstract class Quiz implements BaseColumns {
        public static final String TABLE_Quiz = "Quiz";

        public static final String COLUMN_Quiz_NAME_ENTRY_ID = "idQuestion";
        public static final String COLUMN_Quiz_QUESTION_DE = "Question_DE";
        public static final String COLUMN_Quiz_QUESTION_FR = "Question_FR";
        public static final String COLUMN_Quiz_IMAGE_LINK= "Image_Link";
        public static final String COLUMN_Quiz_IMAGE_SOURCE= "Image_Source";

        // foreign keys
        public static final String COLUMN_Quiz_ID_THEME = "idTheme";
        public static final String COLUMN_Quiz_ID_LEVEL = "idLevel";
        public static final String COLUMN_Quiz_ID_USER_TYPE = "idUserType";
    }

    public static abstract class QuizTheme implements BaseColumns {
        public static final String TABLE_QuizTheme = "QuizTheme";

        public static final String COLUMN_QuizTheme_NAME_ENTRY_ID = "idQuizTheme";
        public static final String COLUMN_QuizTheme_NAME_THEME_DE = "Name_Theme_DE";
        public static final String COLUMN_QuizTheme_NAME_THEME_FR = "Name_Theme_FR";
        public static final String COLUMN_QuizTheme_USE_FOR_QUESTION = "UseForQuestion";
    }

    public static abstract class QuizLevel implements BaseColumns {
        public static final String TABLE_QuizLevel = "QuizLevel";

        public static final String COLUMN_QuizLevel_NAME_ENTRY_ID = "idQuizLevel";
        public static final String COLUMN_QuizLevel_NAME_LEVEL_DE = "Name_Level_DE";
        public static final String COLUMN_QuizLevel_NAME_LEVEL_FR = "Name_Level_FR";
    }

    public static abstract class QuizUserType implements BaseColumns {
        public static final String TABLE_QuizUserType = "QuizUserType";

        public static final String COLUMN_QuizUserType_NAME_ENTRY_ID = "idQuizUserType";
        public static final String COLUMN_QuizUserType_NAME_USERTYPE_DE = "Name_UserType_DE";
        public static final String COLUMN_QuizUserType_NAME_USERTYPE_FR = "Name_UserType_FR";
    }

    public static abstract class QuizAnswer implements BaseColumns {
        public static final String TABLE_QuizAnswer = "QuizAnswer";

        public static final String COLUMN_QuizAnswer_GOOD_ANSWER_DE = "Good_Answer_DE";
        public static final String COLUMN_QuizAnswer_GOOD_ANSWER_FR = "Good_Answer_FR";
        public static final String COLUMN_QuizAnswer_BAD_ANSWER_DE = "Bad_Answer_DE";
        public static final String COLUMN_QuizAnswer_BAD_ANSWER_FR = "Bad_Answer_FR";
        public static final String COLUMN_QuizAnswer_INFO_ANSWER_DE = "Info_Answer_DE";
        public static final String COLUMN_QuizAnswer_INFO_ANSWER_FR = "Info_Answer_FR";

        // foreign key! idQuestion & idDestination
        public static final String COLUMN_QuizAnswer_ID_QUESTION = "idQuestion";
        public static final String COLUMN_QuizAnswer_ID_DESTINATION = "idDestination";
    }

    public static abstract class LearningOffline implements BaseColumns {
        public static final String TABLE_LearningOffline = "LearningOffline";

        public static final String COLUMN_LearningOffline_HEADERBIG_FR = "Headerbig_FR";
        public static final String COLUMN_LearningOffline_HEADERBIG_DE = "Headerbig_DE";
        public static final String COLUMN_LearningOffline_HEADERLITTLE1_FR = "Headerlittle1_FR";
        public static final String COLUMN_LearningOffline_HEADERLITTLE1_DE = "Headerlittle1_DE";
        public static final String COLUMN_LearningOffline_PARAGRAPH1_FR = "Paragraph1_FR";
        public static final String COLUMN_LearningOffline_PARAGRAPH1_DE = "Paragraph1_DE";
        public static final String COLUMN_LearningOffline_HEADERLITTLE2_FR = "Headerlittle2_FR";
        public static final String COLUMN_LearningOffline_HEADERLITTLE2_DE = "Headerlittle2_DE";
        public static final String COLUMN_LearningOffline_LIST_FR = "list_FR";
        public static final String COLUMN_LearningOffline_LIST_DE = "list_DE";
        public static final String COLUMN_LearningOffline_HEADERLITTLE3_FR = "Headerlittle3_FR";
        public static final String COLUMN_LearningOffline_HEADERLITTLE3_DE = "Headerlittle3_DE";
        public static final String COLUMN_LearningOffline_PARAGRAPH2_FR = "Paragraph2_FR";
        public static final String COLUMN_LearningOffline_PARAGRAPH2_DE = "Paragraph2_DE";

        // foreign key! idQuestion & idDestination
        public static final String COLUMN_LearningOffline_ID_DESTINATION = "idDestination";
        public static final String COLUMN_LearningOffline_ID_THEME = "idTheme";
    }

    public static abstract class Remark implements BaseColumns {
        public static final String TABLE_Remark = "Remark";

        public static final String COLUMN_RemarkText = "RemarkText";
        public static final String COLUMN_RemarkPoints = "RemarkPoints";

        // foreign key! idTheme & idDestination
        public static final String COLUMN_Remark_ID_DESTINATION = "idDestination";
        public static final String COLUMN_Remark_ID_THEME = "idTheme";
    }

    public static abstract class StatisticQuizDest implements BaseColumns {
        public static final String TABLE_Statistics = "StatisticQuizDest";

        public static final String COLUMN_Counter = "Counter";

        // foreign key! idDestination
        public static final String COLUMN_Statistics_ID_DESTINATION = "idDestination";
    }

    public static abstract class StatisticQuizTopic implements BaseColumns {
        public static final String TABLE_Statistics = "StatisticQuizTopic";

        public static final String COLUMN_Counter = "Counter";

        // foreign key! idTheme & idDestination
        public static final String COLUMN_Statistics_ID_THEME = "idTheme";
    }

    public static abstract class StatisticLearningDest implements BaseColumns {
        public static final String TABLE_Statistics = "StatisticLearningDest";

        public static final String COLUMN_Counter = "Counter";

        // foreign key! idDestination
        public static final String COLUMN_Statistics_ID_DESTINATION = "idDestination";
    }

    public static abstract class StatisticLearningTopic implements BaseColumns {
        public static final String TABLE_Statistics = "StatisticLearningTopic";

        public static final String COLUMN_Counter = "Counter";

        // foreign key! idTheme & idDestination
        public static final String COLUMN_Statistics_ID_THEME = "idTheme";
    }

    public static abstract class LastUpdate implements BaseColumns {
        public static final String TABLE_LastUpdate = "LastUpdate";
        public static final String COLUMN_LastUpdate_NAME_ENTRY_ID = "IdLastUpdate";
        public static final String COLUMN_UPDATE = "Updated";

    }


}
