package com.hevs.y1617.valaisstudygrp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentQuizProperties;
import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentUser;
import com.hevs.y1617.valaisstudygrp2.DataObjects.Destination;
import com.hevs.y1617.valaisstudygrp2.db.UpdateDataToDB;


public class QuizTopic extends AppCompatActivity {

    Destination chosenDestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_topic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(CurrentUser.getId()==-1){
            Button b = (Button) findViewById(R.id.button3);
            b.setVisibility(View.GONE);
        }

        getChosenDestination();

      //  setChosenDestText();

    }

    private void getChosenDestination(){
        //chosenDestination = (Destination) getIntent().getSerializableExtra("ChosenQuizDestination");
        chosenDestination = CurrentQuizProperties.getQuizDestination();
    }

  /*  private void setChosenDestText(){
        TextView tvChosenDest = (TextView) findViewById(R.id.textViewChosenDest);
        //tvChosenDest.setText(tvChosenDest.getText() + " " + chosenDestination.getName_destination());
    }*/

    private void goToQuizDiff(int topicId){
        UpdateDataToDB.updateStatisticTopic(QuizTopic.this, 0, topicId);

        Intent intent = new Intent(this, Quizdifficulty.class);
        CurrentQuizProperties.setIdTopic(topicId);
        startActivity(intent);
    }

    public void DisplayDestination(View view)
    {
        goToQuizDiff(1);
    }

    public void DisaplyGastronomy(View view)
    {
        goToQuizDiff(2);
    }

    public void DisplayLeisuresEvents(View view)
    {
        goToQuizDiff(3);
    }

    public void DisplayTransportsInfratructures(View view)
    {
        goToQuizDiff(4);
    }

    public void DisplayCustomer(View view)
    {
        goToQuizDiff(5);
    }

    public void changeDestination(View view){
        CurrentUser.setChooseDest(true);
        startActivity(new Intent(QuizTopic.this, DestinationQuiz.class));
    }

}
