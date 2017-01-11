package com.hevs.y1617.valaisstudygrp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentQuizProperties;
import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentUser;
import com.hevs.y1617.valaisstudygrp2.DataObjects.Destination;
import com.hevs.y1617.valaisstudygrp2.db.GetRestDataDB;
import com.hevs.y1617.valaisstudygrp2.db.UpdateDataToDB;

import java.util.ArrayList;
import java.util.List;

public class DestinationQuiz extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    List<Destination> destinationList;
    List<String> destinationNamesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinationselect_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_destinationselect);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_destinationselect);
        navigationView.setNavigationItemSelectedListener(this);

        checkLoggedIn();

        getDestinationFromDB();
        setListAdapter();

        //Log.i("Curr Dest", CurrentQuizProperties.getQuizDestination().toString());

        prepareCorrectMenu();

    }

    private void checkLoggedIn(){
        if(CurrentUser.getId() != -1 && !CurrentUser.isChooseDest()){
            CurrentUser.setChooseDest(false);
            CurrentQuizProperties.setQuizDestination(GetRestDataDB.getDestinationWithIDFromDB(this, CurrentUser.getDestinationId()));
            startActivity(new Intent(this, QuizTopic.class));
        }
    }

    private void prepareCorrectMenu(){
        if(CurrentUser.getId() == -1) {
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_destinationselect);
            Menu nav_menu = navigationView.getMenu();
            nav_menu.findItem(R.id.nav_profile).setEnabled(false);
            nav_menu.findItem(R.id.nav_rank).setEnabled(false);
        }
    }
    private void setListAdapter(){
        ListView lv = (ListView) findViewById(R.id.DestinationLV);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.answerlistview,R.id.listviewItem, destinationNamesList);
        lv.setAdapter(adapter);
        setOnClickItemListener(lv);
    }

    private void setOnClickItemListener(ListView lv){
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UpdateDataToDB.updateStatisticDest(DestinationQuiz.this, 0, destinationList.get(position).getIdDestination());

                Intent intent = new Intent(DestinationQuiz.this, QuizTopic.class);
                CurrentQuizProperties.setQuizDestination(destinationList.get(position));
                //intent.putExtra("ChosenQuizDestination", destinationList.get(position));
                startActivity(intent);
            }
        });
    }


    private void getDestinationFromDB(){
        destinationList = GetRestDataDB.getDestinationFromDB(this);
        destinationNamesList = new ArrayList<>();
        for(Destination d : destinationList){
            destinationNamesList.add(d.getName_destination());
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_destinationselect);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_destinationselect);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




}
