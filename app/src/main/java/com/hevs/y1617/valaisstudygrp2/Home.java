package com.hevs.y1617.valaisstudygrp2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.acer.myapplication.backend.remarkApi.model.Remark;
import com.example.acer.myapplication.backend.statisticDestLearnApi.model.StatisticDestLearn;
import com.example.acer.myapplication.backend.statisticDestQuizApi.model.StatisticDestQuiz;
import com.example.acer.myapplication.backend.statisticTopLearnApi.model.StatisticTopLearn;
import com.example.acer.myapplication.backend.statisticTopQuizApi.model.StatisticTopQuiz;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.CheckInternet;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.RemarkAsync;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.RestDestination;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.RestLearningOffline;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.RestQuiz;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.RestQuizAnswer;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.RestQuizLevel;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.RestQuizTheme;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.RestQuizUserType;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.RestTown;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.StatisticDestLearnAsync;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.StatisticDestQuizAsync;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.StatisticTopLearnAsync;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.StatisticTopQuizAsync;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.SyncListener;
import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentQuizProperties;
import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentUser;
import com.hevs.y1617.valaisstudygrp2.db.GetDataDB;
import com.hevs.y1617.valaisstudygrp2.db.InsertDataToDB;
import com.hevs.y1617.valaisstudygrp2.db.InsertRestDataDB;
import com.hevs.y1617.valaisstudygrp2.db.RemoveDataDB;
import com.hevs.y1617.valaisstudygrp2.db.ValaisStudyDBHelper;

import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        drawer.openDrawer(GravityCompat.START);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageButton flag = (ImageButton) findViewById(R.id.imageButtonFlag);
        changeLanguageFlag.changeLanguageFlag(flag);

        ImageView imageView = (ImageView) findViewById(R.id.wallisMap);
        PhotoViewAttacher photoView = new PhotoViewAttacher(imageView);
        photoView.update();

        resetQuizProperties();

        prepareCorrectMenu();

        //new Mainpage
        SQLiteOpenHelper dbHelper = new ValaisStudyDBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        execSnychronization();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        drawer.openDrawer(GravityCompat.START);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageButton flag = (ImageButton) findViewById(R.id.imageButtonFlag);
        changeLanguageFlag.changeLanguageFlag(flag);

        ImageView imageView = (ImageView) findViewById(R.id.wallisMap);
        PhotoViewAttacher photoView = new PhotoViewAttacher(imageView);
        photoView.update();

        resetQuizProperties();

        prepareCorrectMenu();

        //new Mainpage
        SQLiteOpenHelper dbHelper = new ValaisStudyDBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        execSnychronization();
    }

    public void execSnychronization(){
        if(GetDataDB.updateReq(this) && CheckInternet.isNetworkAvailable(this)){
            insertStatistics();
            updateRemarks();
            getDataFromRestAPI();
        }
    }

    public void forceSync(View view){
        if(CheckInternet.isNetworkAvailable(this)){
            insertStatistics();
            updateRemarks();
            getDataFromRestAPI();
        }
        else{
            new AlertDialog.Builder(this)
                    .setTitle(R.string.noInternet)
                    .setMessage(R.string.noInternetMsg)
                    .show();
        }
    }

    public void synchronizeData(){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        removeDataFromDB();
        insertDataToDB();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void updateRemarks(){
        List<Remark> remarkList = GetDataDB.getRemarks(this);
        Log.d("Local Remark updated", remarkList.toString());
        if(remarkList.size() != 0){
            new RemarkAsync(this, true, remarkList).execute();
            Log.d("Local Remark updated", "local remark updated");
        }
        else{
            //SyncStatisticListener.execSynchronization(this);
            SyncListener.execSynchronization(this);
        }
    }

    private void insertStatistics(){
        insertStatDestQuiz();
        insertStatTopicQuiz();
        insertStatDestLearn();
        insertStatTopicLearn();
    }

    private void insertStatDestQuiz(){
        List<StatisticDestQuiz> statisticDestQuizList = GetDataDB.getStatisticDestQuiz(this);
        if(statisticDestQuizList.size() != 0){
            new StatisticDestQuizAsync(this, statisticDestQuizList).execute();
        }
        else{
            //SyncStatisticListener.execSynchronization(this);
            SyncListener.execSynchronization(this);
        }
    }

    private void insertStatDestLearn(){
        List<StatisticDestLearn> statisticDestLearnList = GetDataDB.getStatisticDestLearn(this);
        if(statisticDestLearnList.size() != 0){
            new StatisticDestLearnAsync(this, statisticDestLearnList).execute();
        }
        else{
            //SyncStatisticListener.execSynchronization(this);
            SyncListener.execSynchronization(this);
        }
    }

    private void insertStatTopicLearn(){
        List<StatisticTopLearn> statisticTopicLearnList = GetDataDB.getStatisticTopicLearn(this);
        if(statisticTopicLearnList.size() != 0){
            new StatisticTopLearnAsync(this, statisticTopicLearnList).execute();
        }
        else{
            //SyncStatisticListener.execSynchronization(this);
            SyncListener.execSynchronization(this);
        }
    }

    private void insertStatTopicQuiz(){
        List<StatisticTopQuiz> statisticTopicQuizList = GetDataDB.getStatisticTopicQuiz(this);
        if(statisticTopicQuizList.size() != 0){
            new StatisticTopQuizAsync(this, statisticTopicQuizList).execute();
        }
        else{
            //SyncStatisticListener.execSynchronization(this);
            SyncListener.execSynchronization(this);
        }
    }

    public void getDataFromRestAPI(){
        RestDestination.getRestData(this);
        RestTown.getRestData(this);
        RestQuiz.getRestData(this);
        RestQuizAnswer.getRestData(this);
        RestQuizTheme.getRestData(this);
        RestQuizLevel.getRestData(this);
        RestQuizUserType.getRestData(this);
        RestLearningOffline.getRestData(this);
    }

    private void insertDataToDB(){
        InsertRestDataDB.insertDestinationToDB(this);
        InsertRestDataDB.insertTown(this);
        InsertRestDataDB.insertQuizTheme(this);
        InsertRestDataDB.insertQuizLevel(this);
        InsertRestDataDB.insertQuizUserType(this);
        InsertRestDataDB.insertQuiz(this);
        InsertRestDataDB.insertQuizAnswer(this);
        InsertRestDataDB.insertLearningOffline(this);
        InsertDataToDB.insertStatisticQuizDestToDB(this);
        InsertDataToDB.insertStatisticQuizTopicToDB(this);
        InsertDataToDB.insertStatisticLearningDestToDB(this);
        InsertDataToDB.insertStatisticLearningTopicToDB(this);
    }

    private void removeDataFromDB(){
        RemoveDataDB.removeDestination(this);
        RemoveDataDB.removeQuiz(this);
        RemoveDataDB.removeQuizProperties(this);
        RemoveDataDB.removeLearningOffline(this);
        RemoveDataDB.removeRemarks(this);
        RemoveDataDB.removeStatistics(this);
    }

    private void prepareCorrectMenu(){
        if(CurrentUser.getId() == -1) {
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            Menu nav_menu = navigationView.getMenu();
            nav_menu.findItem(R.id.nav_profile).setEnabled(false);
            nav_menu.findItem(R.id.nav_rank).setEnabled(false);
        }
    }
    private void resetQuizProperties(){
        Question.counter =0;
        CurrentQuizProperties.setPoints(0);
        CurrentQuizProperties.setQuizNumber(0);
        CurrentQuizProperties.setProgress(1);
        Question.setCorrectQuestions(0);
    }

    public void redirectLogin(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void redirectSettings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        } else if (id == R.id.nav_profile) {
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
        } else if (id == R.id.nav_rank) {
            Intent intent = new Intent(this, Rank.class);
            startActivity(intent);
        } else if (id == R.id.nav_information) {
            Intent intent = new Intent(this, ChooseDestinationLearning.class);
            startActivity(intent);
        } else if (id == R.id.nav_quiz) {
            Intent intent = new Intent(this, DestinationQuiz.class);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        } else if (id == R.id.nav_contact) {
            Intent intent = new Intent(this, Contact.class);
            startActivity(intent);
        } else if (id == R.id.nav_about) {
            Intent intent = new Intent(this, Copyright.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            CurrentUser.resetCurrentUser();
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
