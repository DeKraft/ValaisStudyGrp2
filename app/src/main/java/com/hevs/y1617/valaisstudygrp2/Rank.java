package com.hevs.y1617.valaisstudygrp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.myapplication.backend.userApi.model.User;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.UserAsync;
import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentRegisteredUsers;
import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentUser;
import com.hevs.y1617.valaisstudygrp2.DataObjects.LevelDefinition;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Rank extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    List<User> bestUsers = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_rank);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_rank);
        navigationView.setNavigationItemSelectedListener(this);

        //For reading all usernames
        new UserAsync(this).execute();

        prepareCorrectMenu();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_rank_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_rank);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_rank);
        navigationView.setNavigationItemSelectedListener(this);

        //For reading all usernames
        new UserAsync(this).execute();

        prepareCorrectMenu();
    }

    private void prepareCorrectMenu(){
        if(CurrentUser.getId() == -1) {
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_rank);
            Menu nav_menu = navigationView.getMenu();
            nav_menu.findItem(R.id.nav_profile).setEnabled(false);
            nav_menu.findItem(R.id.nav_rank).setEnabled(false);
        }
    }

    public void setUserLevelEmblem(){
        ImageView imgview = (ImageView) findViewById(R.id.imageView);
        switch (LevelDefinition.getLevel(CurrentUser.getQuizpoints())){
            case 1:
                imgview.setImageResource(R.mipmap.level1);
                break;
            case 2:
                imgview.setImageResource(R.mipmap.level2);
                break;
            case 3:
                imgview.setImageResource(R.mipmap.level3);
                break;
            case 4:
                imgview.setImageResource(R.mipmap.level4);
                break;
        }
        setUserPoints();
    }

    private void setUserPoints(){
        TextView txtview = (TextView) findViewById(R.id.textView10);
        txtview.setText(CurrentUser.getQuizpoints()+ "");
    }

    public void setBestPlayers(){
        bestUsers = CurrentRegisteredUsers.getUserList();
        if (bestUsers.size() > 0) {
            Collections.sort(bestUsers, new Comparator<User>() {
                @Override
                public int compare(final User object1, final User object2) {
                    return object1.getQuizpoints().compareTo(object2.getQuizpoints());
                }
            });
        }
        Log.i("usersorted", bestUsers.toString());

        setEmblemsForTopPlayers(bestUsers);
        setTopPlayerInfos(bestUsers);
    }

    private void setEmblemsForTopPlayers(List<User> bestUsers){
        ImageView top1 = (ImageView) findViewById(R.id.imageTop1);
        ImageView top2 = (ImageView) findViewById(R.id.imageTop2);
        ImageView top3 = (ImageView) findViewById(R.id.imageTop3);
        //setTop1
        setFirstBestPlayer(top1, bestUsers.get(bestUsers.size()-1).getQuizpoints());
        setFirstBestPlayer(top2, bestUsers.get(bestUsers.size()-2).getQuizpoints());
        setFirstBestPlayer(top3, bestUsers.get(bestUsers.size()-3).getQuizpoints());
    }

    private void setTopPlayerInfos(List<User> bestUsers){
        TextView top1 = (TextView) findViewById(R.id.Top1);
        TextView top2 = (TextView) findViewById(R.id.Top2);
        TextView top3 = (TextView) findViewById(R.id.Top3);

        top1.setText(bestUsers.get(bestUsers.size()-1).getUsername() + ": " + bestUsers.get(bestUsers.size()-1).getQuizpoints());
        top2.setText(bestUsers.get(bestUsers.size()-2).getUsername() + ": " + bestUsers.get(bestUsers.size()-2).getQuizpoints());
        top3.setText(bestUsers.get(bestUsers.size()-3).getUsername() + ": " + bestUsers.get(bestUsers.size()-3).getQuizpoints());
    }

    private void setFirstBestPlayer(ImageView imgview, int bestPlayer){
        switch (LevelDefinition.getLevel(bestPlayer)){
            case 1:
                imgview.setImageResource(R.mipmap.level1);
                break;
            case 2:
                imgview.setImageResource(R.mipmap.level2);
                break;
            case 3:
                imgview.setImageResource(R.mipmap.level3);
                break;
            case 4:
                imgview.setImageResource(R.mipmap.level4);
                break;
        }
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_rank);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_rank);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
