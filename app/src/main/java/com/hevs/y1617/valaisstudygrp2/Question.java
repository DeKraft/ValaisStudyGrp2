package com.hevs.y1617.valaisstudygrp2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hevs.y1617.valaisstudygrp2.AsyncObjects.DownloadImageTask;
import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentLanguage;
import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentQuizProperties;
import com.hevs.y1617.valaisstudygrp2.DataObjects.Quiz;
import com.hevs.y1617.valaisstudygrp2.DataObjects.QuizAnswer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Question extends AppCompatActivity {

    private static List<Quiz> quizList = null;
    private static List<QuizAnswer> answerList = null;
    private int diff;
    private int idTopic;
    private int idDestination;
    private int quizNumber;
    private int points;
    private int progress;
    private int rightanswer;
    private static int correctQuestions = 0;
    public static int counter =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getQuizData();

        int a = getRandomQuestions(quizNumber);
        int b = getQuizAnswers(quizNumber);

        if ((a + b) > 1) {

        initialize();


        }else{
            startActivity(new Intent(Question.this, ErrorPage.class ));
        }
    }

    public void initialize(){

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


        seekBar.setMax(5);


        seekBar.setProgress(counter+1);

        setQuestionName(quizNumber);

        ImageView imgview = (ImageView) findViewById(R.id.imageView3);

        new DownloadImageTask(imgview).execute(quizList.get(quizNumber).getImageSource());


        final String[][] preparedAnswers = readAnswerFromQuestion(quizNumber);

        ListView lv = (ListView) findViewById(R.id.AnswerLV);


        if(preparedAnswers.length != 0) {
            ArrayAdapter adapter = new ArrayAdapter(this, R.layout.answerlistview, R.id.listviewItem, preparedAnswers[0]);
            lv.setAdapter(adapter);

            setItemClickListener(lv, preparedAnswers);
        }
        else{

            Intent next = new Intent(Question.this, Question.class);
            if (!(quizNumber + 1 < quizList.size())) {
                next = new Intent(Question.this, Quizfinish.class);}
            CurrentQuizProperties.setQuizNumber(quizNumber+1);

            startActivity(next);

    }
    }

    private void setItemClickListener(final ListView lv, final String[][] preparedAnswers) {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent next;
                View v = View.inflate(getBaseContext(),R.layout.answerlistview, null);
                if (preparedAnswers[1][position].equals("1")) {
                    CurrentQuizProperties.setPoints(points + 1);
                    parent.getChildAt(position).setBackgroundColor(Color.GREEN);



                }else{

                    parent.getChildAt(position).setBackgroundColor(Color.RED);

                    for(int i =0; i < preparedAnswers[1].length;i++){
                        if (preparedAnswers[1][i].equals("1")) {

                            parent.getChildAt(i).setBackgroundColor(Color.GREEN);


                        }
                    }

                }
                if(!answerList.get(rightanswer).getInfoAnswerDE().contains("null")){
                    Toast.makeText(getBaseContext(), answerList.get(rightanswer).getInfoAnswerDE(), Toast.LENGTH_LONG).show();
                }



                    CurrentQuizProperties.setProgress(progress + 1);

                if (!(quizNumber + 1 < quizList.size()) || counter == 4) {
                    next = new Intent(Question.this, Quizfinish.class);

                } else {
                    counter++;
                    next = new Intent(Question.this, Question.class);
                    CurrentQuizProperties.setQuizNumber(quizNumber+1);

                }

                correctQuestions++;
                new Timer().schedule(new TimerTask(){
                    public void run() {
                        Question.this.runOnUiThread(new Runnable() {
                            public void run() {
                                startActivity(next);
                            }
                        });
                    }
                }, 1000);

            }
        });
    }

    private String[][] readAnswerFromQuestion(int quizNumber) {
        rightanswer = getrightAnswertoQuestion(this.quizList.get(quizNumber).getIdQuestion());
        if(rightanswer != -1){
            String[] badAnswers = new String[0];
            String[] goodAnswers = new String[1];
            switch (CurrentLanguage.getCurrentLanguage()) {
                case "fr":
                    badAnswers = this.answerList.get(rightanswer).getBadAnswerFR().split(";");
                    if (this.answerList.get(rightanswer).getGoodAnswerFR().contains(";")) {
                        goodAnswers[0] = this.answerList.get(rightanswer).getGoodAnswerFR();
                    } else {
                        goodAnswers = this.answerList.get(rightanswer).getGoodAnswerFR().split(";");
                    }
                    break;
                case "de":
                    badAnswers = this.answerList.get(rightanswer).getBadAnswerDE().split(";");
                    if (this.answerList.get(rightanswer).getGoodAnswerFR().contains(";")) {
                        goodAnswers[0] = this.answerList.get(rightanswer).getGoodAnswerDE();
                    } else {
                        goodAnswers = this.answerList.get(rightanswer).getGoodAnswerDE().split(";");
                    }
                    break;
            }

            String[] allAnswers = new String[(badAnswers.length + goodAnswers.length)];

            for (int i = 0; i < goodAnswers.length; i++) {
                allAnswers[i] = goodAnswers[i];
            }
            for (int i = 0; i < badAnswers.length; i++) {
                allAnswers[i + goodAnswers.length] = badAnswers[i];
            }

            Collections.shuffle(Arrays.asList(allAnswers));

            String[][] preparedAnswers = new String[2][allAnswers.length];

            for (int i = 0; i < preparedAnswers[0].length; i++) {
                preparedAnswers[0][i] = allAnswers[i];
                if (checkGoodOrBadAnswer(allAnswers[i], goodAnswers)) {
                    preparedAnswers[1][i] = "1";
                } else preparedAnswers[1][i] = "0";
            }
            return preparedAnswers;
        }
        return new String [0][0];
    }

    private boolean checkGoodOrBadAnswer(String answerToCheck, String[] goodAnswers) {
        for (int i = 0; i < goodAnswers.length; i++) {
            if (goodAnswers[i].equals(answerToCheck)) return true;
        }
        return false;
    }

    private void getQuizData() {
        this.idDestination = CurrentQuizProperties.getQuizDestination().getIdDestination();
        this.idTopic = CurrentQuizProperties.getIdTopic();
        this.diff = CurrentQuizProperties.getIdDiff();

        quizNumber = CurrentQuizProperties.getQuizNumber();
        points = CurrentQuizProperties.getPoints();
        progress = CurrentQuizProperties.getProgress();


    }

    private int getRandomQuestions(int quizNumber) {
        if (quizNumber == 0) {
            this.quizList = new ArrayList<>();
            for (Quiz q : CurrentQuizProperties.getQuestionList(this)) {
                if (q.getFk_Level() == diff && q.getFk_Theme() == idTopic ) {
                    this.quizList.add(q);
                }
            }
            if (this.quizList.size() == 0) {

                return 0;
            }
            Collections.shuffle(this.quizList);
        }
        return 1;
    }

    private int getQuizAnswers(int quizNumber) {
        if (quizNumber == 0) {

            this.answerList = CurrentQuizProperties.getQuestionAnswerList(this);
        }
        return 1;
    }

    private void setQuestionName(int quizNumber) {
        TextView tvquestion = (TextView) findViewById(R.id.Question);
        String questionName = "";
        switch (CurrentLanguage.getCurrentLanguage()) {
            case "fr":
                questionName = this.quizList.get(quizNumber).getQuestionFR();
                break;
            case "de":
                questionName = this.quizList.get(quizNumber).getQuestionDE();
        }
        tvquestion.setText(questionName);
    }


    private int getrightAnswertoQuestion(int idquestion){
        for (int i =0 ;i < this.answerList.size();i++ ){
            if((idquestion == this.answerList.get(i).getFk_QuestionId() && this.idDestination == this.answerList.get(i).getFk_DestinationId()) || (idquestion == this.answerList.get(i).getFk_QuestionId() && this.answerList.get(i).getFk_DestinationId() == 50)) return i;
        }
         return -1;
    }



    public static int getCorrectQuestions() {
        return correctQuestions;
    }

    public static void setCorrectQuestions(int correctQuestions) {
        Question.correctQuestions = correctQuestions;
    }

    private void resetQuizProperties(){
        Question.counter =0;
        CurrentQuizProperties.setPoints(0);
        CurrentQuizProperties.setQuizNumber(0);
        CurrentQuizProperties.setProgress(1);
        Question.setCorrectQuestions(0);
    }

    @Override
    public void onBackPressed() {
        resetQuizProperties();
        startActivity(new Intent(Question.this, DestinationQuiz.class));
    }
}
