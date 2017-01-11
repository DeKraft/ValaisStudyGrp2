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
import android.widget.TextView;

import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentLanguage;
import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentUser;
import com.hevs.y1617.valaisstudygrp2.db.GetRestDataDB;

public class Profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_profile);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_profile);
        navigationView.setNavigationItemSelectedListener(this);

        setProfileInformations();

        prepareCorrectMenu();

    }

    private void prepareCorrectMenu(){
        if(CurrentUser.getId() == -1) {
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_profile);
            Menu nav_menu = navigationView.getMenu();
            nav_menu.findItem(R.id.nav_profile).setEnabled(false);
            nav_menu.findItem(R.id.nav_rank).setEnabled(false);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_profile_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_profile);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_profile);
        navigationView.setNavigationItemSelectedListener(this);

        setProfileInformations();

        prepareCorrectMenu();
    }

    private void setProfileInformations(){
        Log.i("destid", CurrentUser.getDestinationId()+"");
        Log.i("utid", CurrentUser.getUsertypeId()+"");
        TextView username = (TextView) findViewById(R.id.tvusername);
        username.setText(CurrentUser.getUsername().toString());

        TextView firstname = (TextView) findViewById(R.id.tvfirstname);
        firstname.setText(CurrentUser.getFirstname().toString());

        TextView lastname = (TextView) findViewById(R.id.tvlastname);
        lastname.setText(CurrentUser.getLastname().toString());

        String destName = GetRestDataDB.getDestinationWithIDFromDB(this, CurrentUser.getDestinationId()).getName_destination().toString();
        TextView ort = (TextView) findViewById(R.id.tvort);
        ort.setText(destName);


        String userTypeDE = GetRestDataDB.getUserTypeWithIdFromDB(this, CurrentUser.getUsertypeId()).getNameUserTypeFR().toString();
        String userTypeFR = GetRestDataDB.getUserTypeWithIdFromDB(this, CurrentUser.getUsertypeId()).getNameUserTypeDE().toString();

        TextView userType = (TextView) findViewById(R.id.tvusertype);
        switch (CurrentLanguage.getCurrentLanguage()){
            case "fr": userType.setText(userTypeDE);
                break;
            case "de": userType.setText(userTypeFR);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_profile);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_profile);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
