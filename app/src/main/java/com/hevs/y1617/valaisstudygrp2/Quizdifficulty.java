package com.hevs.y1617.valaisstudygrp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentQuizProperties;
import com.hevs.y1617.valaisstudygrp2.DataObjects.Destination;


public class Quizdifficulty extends AppCompatActivity {

    Destination chosenDestination;
    int idQuizTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizdifficulty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getPropertiesForQuiz();
        fillQuizProperties();

    }

    private void getPropertiesForQuiz(){
        chosenDestination = (Destination) getIntent().getSerializableExtra("ChosenQuizDestination");
        idQuizTopic = getIntent().getIntExtra("quizTopicId", 0);
    }

    private void startQuiz(int diff){
        Intent next = new Intent(Quizdifficulty.this, Question.class);
        CurrentQuizProperties.setIdDiff(diff);
        startActivity(next);
    }

    public void selecteasy(View view){
        startQuiz(1);
    }
    public void selectmedium(View view){
        startQuiz(2);
    }
    public void selecthard(View view){
        startQuiz(3);
    }

    private void fillQuizProperties(){
        CurrentQuizProperties.getQuestionList(this);
        CurrentQuizProperties.getQuestionAnswerList(this);
    }



}

