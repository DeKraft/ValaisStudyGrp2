package com.hevs.y1617.valaisstudygrp2;
import android.content.Intent;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.myapplication.backend.remarkApi.model.Remark;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.CheckInternet;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.RemarkAsync;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.UserAsync;
import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentQuizProperties;
import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentUser;
import com.hevs.y1617.valaisstudygrp2.DataObjects.LevelDefinition;
import com.hevs.y1617.valaisstudygrp2.db.InsertDataToDB;


public class Quizfinish extends AppCompatActivity {

    private int points;
    private int diff;
    private int questionNumber;
    private int currentlevel;
    private int newlevel;
    private int score;
    private int p1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizfinish);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(CurrentUser.getUsertypeId()== 1){
            RatingBar rb = (RatingBar) findViewById(R.id.ratingBar);
            rb.setVisibility(View.GONE);

            EditText et = (EditText) findViewById(R.id.comment);
            et.setVisibility(View.GONE);

            Button bt = (Button) findViewById(R.id.sendremark);
            bt.setVisibility(View.GONE);
        }

        points = CurrentQuizProperties.getPoints();
        diff = CurrentQuizProperties.getIdDiff();
        questionNumber = Question.getCorrectQuestions();
        score = CurrentUser.getQuizpoints();
        currentlevel = LevelDefinition.getLevel(score);

        TextView lbravo = (TextView) findViewById(R.id.Label);
        TextView t = (TextView) findViewById(R.id.desc);
        String s1 = getString(R.string.youhave);
        try {
            p1 = 100 / (questionNumber) * points;
        }catch (ArithmeticException e){
            p1 = 0;
        }
        String s3 = getString(R.string.rankpoints);
        String fin = s1+" "+Integer.toString(p1)+s3;
        t.setText(fin);
        if(p1 < 80){
            lbravo.setText(R.string.schade);
        }

        else{
            if(CurrentUser.getId() != -1 && CheckInternet.isNetworkAvailable(this)) {
                score = score + (3 * diff);
                CurrentUser.setQuizpoints(score);
                new UserAsync(this, true).execute();
            }

        }

        newlevel = LevelDefinition.getLevel(score);
        TextView label =(TextView) findViewById(R.id.labelr);
        TextView newlvl =(TextView) findViewById(R.id.newlvl);

        ImageView imgview = (ImageView) findViewById(R.id.imageView2);

        if(currentlevel == newlevel){
            String s = getResources().getString(R.string.labelrank)+" "+getResources().getString(R.string.with)+" "+score+" "+getResources().getString(R.string.points);
            label.setText(s);
            switch (currentlevel){
                case 1:
                    imgview.setImageResource(R.mipmap.level1);
                    newlvl.setText(LevelDefinition.getLevel1()-CurrentUser.getQuizpoints()+" "+ getResources().getString(R.string.torank2));
                    break;
                case 2:
                    imgview.setImageResource(R.mipmap.level2);
                    newlvl.setText(LevelDefinition.getLevel2()-CurrentUser.getQuizpoints()+" "+ getResources().getString(R.string.torank3));
                    break;
                case 3:
                    imgview.setImageResource(R.mipmap.level3);
                    newlvl.setText(LevelDefinition.getLevel3()-CurrentUser.getQuizpoints()+" "+ getResources().getString(R.string.torank4));
                    break;
                case 4:
                    imgview.setImageResource(R.mipmap.level4);
                    newlvl.setText(getResources().getString(R.string.maxlvl));
                    break;
            }

        }
        else{
            label.setText(R.string.labelnewrank);
            int p;
            switch (newlevel){
                case 1:
                    imgview.setImageResource(R.mipmap.level1);
                    newlvl.setText(LevelDefinition.getLevel1()-CurrentUser.getQuizpoints()+" "+getResources().getString(R.string.torank2));
                    break;
                case 2:
                    imgview.setImageResource(R.mipmap.level2);
                    newlvl.setText(LevelDefinition.getLevel2()-CurrentUser.getQuizpoints()+" "+ getResources().getString(R.string.torank3));
                    break;
                case 3:
                    imgview.setImageResource(R.mipmap.level3);
                    newlvl.setText(LevelDefinition.getLevel3()-CurrentUser.getQuizpoints()+" "+ getResources().getString(R.string.torank4));
                    break;
                case 4:
                    imgview.setImageResource(R.mipmap.level4);
                    newlvl.setText(getResources().getString(R.string.maxlvl));
                    break;
            }
        }

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(0);

        resetQuizProperties();

    }

    private void resetQuizProperties(){
        Question.counter =0;
        CurrentQuizProperties.setPoints(0);
        CurrentQuizProperties.setQuizNumber(0);
        CurrentQuizProperties.setProgress(1);
        Question.setCorrectQuestions(0);
    }

    public void sendrequest(View view){
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        EditText editText = (EditText) findViewById(R.id.comment);
        double value = (double)ratingBar.getRating();
        String comment = editText.getText().toString();

        Remark remark = new Remark();
        remark.setIdDestination(CurrentQuizProperties.getQuizDestination().getIdDestination());
        remark.setIdTheme(CurrentQuizProperties.getIdTopic());
        remark.setRemarkPoints(value);
        remark.setRemarkText(comment);

        if(!CheckInternet.isNetworkAvailable(this)){
            InsertDataToDB.insertRemarkToDB(this, comment, CurrentQuizProperties.getIdTopic(),
                    CurrentQuizProperties.getQuizDestination().getIdDestination(), (float)value);
        }
        else new RemarkAsync(this, true, remark).execute();

        Toast.makeText(getBaseContext(), getString(R.string.thanksremark), Toast.LENGTH_LONG).show();
        startActivity(new Intent(Quizfinish.this, Home.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.quizfinishbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {

        startActivity(new Intent(Quizfinish.this, Home.class));
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Quizfinish.this, DestinationQuiz.class));
    }
}
