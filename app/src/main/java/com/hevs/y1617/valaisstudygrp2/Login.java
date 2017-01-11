package com.hevs.y1617.valaisstudygrp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.acer.myapplication.backend.userApi.model.User;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.UserAsync;
import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentRegisteredUsers;
import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentUser;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageButton flag = (ImageButton) findViewById(R.id.imageButtonFlag);
        changeLanguageFlag.changeLanguageFlag(flag);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageButton flag = (ImageButton) findViewById(R.id.imageButtonFlag);
        changeLanguageFlag.changeLanguageFlag(flag);

    }

    public void openRegistration(View view){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }

    public void openHome(View view){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    private boolean isEmptyCheck(String etText) {
        return etText.trim().length() == 0;
    }

    public void loginAsUser(View view){

        //For reading all usernames
        new UserAsync(this).execute();

    }

    public void executeLogin(){
        if(!loginCheck())return;
        else{
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        }
    }

    private boolean loginCheck(){
        boolean ok = true;
        boolean checkCre = true;

        EditText username = (EditText) findViewById(R.id.editUsername);
        EditText pw = (EditText) findViewById(R.id.editPassword);

        if(isEmptyCheck(username.getText().toString())){
            username.setError(getResources().getString(R.string.pagelogin_usernameEmpty));
            checkCre = false;
            ok = false;
        }
        if(isEmptyCheck(pw.getText().toString())){
            pw.setError(getResources().getString(R.string.pagelogin_pwEmtpy));
            checkCre = false;
            ok = false;
        }
        if(!checkCredentials(username, pw) && checkCre){
            username.setError(getResources().getString(R.string.pagelogin_wrongCridentials));
            ok = false;
        }
        return ok;
    }

    private boolean checkCredentials(EditText username, EditText password){
        boolean ok = false;

        for(User user : CurrentRegisteredUsers.getUserList()){
            if(!user.getUsername().toString().equalsIgnoreCase(username.getText().toString())){
                ok = false;
            }
            else{
                if(user.getPassword().toString().equalsIgnoreCase(password.getText().toString())){
                    CurrentUser.setId(user.getId());
                    CurrentUser.setUsername(user.getUsername());
                    CurrentUser.setLastname(user.getLastname());
                    CurrentUser.setFirstname(user.getFirstname());
                    CurrentUser.setPassword(user.getPassword());
                    CurrentUser.setDestinationId(user.getDestinationId());
                    CurrentUser.setUsertypeId(user.getUsertypeId());
                    CurrentUser.setQuizpoints(user.getQuizpoints());
                    ok = true;
                    break;
                }
                else ok = false;
            }
        }
        return ok;
    }

    public void redirectSettings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

}
