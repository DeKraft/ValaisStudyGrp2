package com.hevs.y1617.valaisstudygrp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.hevs.y1617.valaisstudygrp2.AsyncObjects.CheckInternet;
import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentLearningOffline;
import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentUser;
import com.hevs.y1617.valaisstudygrp2.DataObjects.Destination;
import com.hevs.y1617.valaisstudygrp2.db.UpdateDataToDB;

public class Learning extends AppCompatActivity {

    Destination chosenDestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        getChosenDestination();
        if(CurrentUser.getId()==-1){
            Button b = (Button) findViewById(R.id.button3);
            b.setVisibility(View.GONE);
        }
    }

    private void getChosenDestination(){
        chosenDestination = (Destination) getIntent().getSerializableExtra("ChosenLearningDestination");
    }

    public void DisplayDestination(View view)
    {
        UpdateDataToDB.updateStatisticTopic(Learning.this, 1, 1);

        if(CheckInternet.isNetworkAvailable(this)){
            Intent intent = new Intent(this, DestinationLearning.class);
            intent.putExtra("ChosenDestinationId", this.chosenDestination.getIdDestination());
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, destinationLearningOffline.class);
            intent.putExtra("ChosenDestinationId", this.chosenDestination.getIdDestination());
            CurrentLearningOffline.setCurrentLearningTopic(1);
            startActivity(intent);
        }
    }

    public void changeDestination1(View view){
        CurrentUser.setChooseDest(true);
        startActivity(new Intent(Learning.this, ChooseDestinationLearning.class));
    }

    public void DisaplyGastronomy(View view)
    {
        UpdateDataToDB.updateStatisticTopic(Learning.this, 1, 2);

        if(CheckInternet.isNetworkAvailable(this)){
            Intent intent = new Intent(this, GastronomyLearning.class);
            intent.putExtra("ChosenDestinationId", this.chosenDestination.getIdDestination());
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, destinationLearningOffline.class);
            intent.putExtra("ChosenDestinationId", this.chosenDestination.getIdDestination());
            CurrentLearningOffline.setCurrentLearningTopic(2);
            startActivity(intent);
        }
    }

    public void DisplayLeisuresEvents(View view)
    {
        UpdateDataToDB.updateStatisticTopic(Learning.this, 1, 3);

        if(CheckInternet.isNetworkAvailable(this)){
            Intent intent = new Intent(this, LeisuresEventsLearning.class);
            intent.putExtra("ChosenDestinationId", this.chosenDestination.getIdDestination());
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, destinationLearningOffline.class);
            intent.putExtra("ChosenDestinationId", this.chosenDestination.getIdDestination());
            CurrentLearningOffline.setCurrentLearningTopic(3);
            startActivity(intent);
        }
    }

    public void DisplayTransportsInfratructures(View view)
    {
        UpdateDataToDB.updateStatisticTopic(Learning.this, 1, 4);

        if(CheckInternet.isNetworkAvailable(this)){
            Intent intent = new Intent(this, TransportsInfrastructuresLearning.class);
            intent.putExtra("ChosenDestinationId", this.chosenDestination.getIdDestination());
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, destinationLearningOffline.class);
            intent.putExtra("ChosenDestinationId", this.chosenDestination.getIdDestination());
            CurrentLearningOffline.setCurrentLearningTopic(4);
            startActivity(intent);
        }
    }

    public void DisplayCustomer(View view)
    {
        UpdateDataToDB.updateStatisticTopic(Learning.this, 1, 5);

        if(CheckInternet.isNetworkAvailable(this)){
            Intent intent = new Intent(this, CustomerLearning.class);
            intent.putExtra("ChosenDestinationId", this.chosenDestination.getIdDestination());
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, destinationLearningOffline.class);
            intent.putExtra("ChosenDestinationId", this.chosenDestination.getIdDestination());
            CurrentLearningOffline.setCurrentLearningTopic(5);
            startActivity(intent);
        }
    }
}
