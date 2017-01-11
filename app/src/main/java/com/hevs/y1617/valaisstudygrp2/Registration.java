package com.hevs.y1617.valaisstudygrp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.acer.myapplication.backend.userApi.model.User;
import com.hevs.y1617.valaisstudygrp2.AsyncObjects.UserAsync;
import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentLanguage;
import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentRegisteredUsers;
import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentUser;
import com.hevs.y1617.valaisstudygrp2.DataObjects.Destination;
import com.hevs.y1617.valaisstudygrp2.DataObjects.QuizUserType;
import com.hevs.y1617.valaisstudygrp2.db.GetRestDataDB;

import java.util.ArrayList;
import java.util.List;

public class Registration extends AppCompatActivity {

    String [] arraySpinner;
    List<Destination> destinations;
    List<String> destNames;

    List<QuizUserType> userTypes;
    List<String> userTypeNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set spinner informations
        createObjects();

        Log.d("2",CurrentRegisteredUsers.getUserList().toString());

    }

    private void createObjects(){
        setTypeInformationSpinner();
        setDestInformationSpinner();
    }

    private void setTypeInformationSpinner(){
        this.userTypes = GetRestDataDB.getUserTypeFromDB(this);
        Log.d("asdf", this.userTypes.toString());
        if(this.userTypes.size() != 0) {
            this.userTypes.remove(0);
            this.userTypeNames = new ArrayList<>();
            for (QuizUserType qut : this.userTypes) {
                switch (CurrentLanguage.getCurrentLanguage()) {
                    case "fr":
                        this.userTypeNames.add(qut.getNameUserTypeFR());
                        break;
                    case "de":
                        this.userTypeNames.add(qut.getNameUserTypeDE());
                        break;
                }
            }
            Spinner s = (Spinner) findViewById(R.id.spinnerTypes);
            ArrayAdapter<String> adapter = new ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, this.userTypeNames);
            s.setAdapter(adapter);
        }
    }

    private void setDestInformationSpinner(){
        this.destinations = GetRestDataDB.getDestinationFromDB(this);
        if(this.destinations.size() != 0) {
            this.destNames = new ArrayList<>();
            for (Destination destName : this.destinations) {
                this.destNames.add(destName.getName_destination());
            }
            Spinner s = (Spinner) findViewById(R.id.spinnerDestination);
            ArrayAdapter<String> adapter = new ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, this.destNames);
            s.setAdapter(adapter);
        }
    }

    public void register(View view){
        //Get again all current usernames
        new UserAsync(this).execute();

    }

    public void registerExec(){
        Log.i("Register", "exec");
        //execute tests
        if(!registerChecks())return;

        //Get information of user
        EditText username = (EditText) findViewById(R.id.reditUsername);
        EditText lastname = (EditText) findViewById(R.id.reditLastname);
        EditText firstname = (EditText) findViewById(R.id.reditFirstname);
        EditText pw = (EditText) findViewById(R.id.reditPassword);
        Spinner spinnerDest = (Spinner) findViewById(R.id.spinnerDestination);
        Spinner spinnerTypes = (Spinner) findViewById(R.id.spinnerTypes);

        User user = new User();
        user.setUsername(username.getText().toString());
        user.setLastname(lastname.getText().toString());
        user.setFirstname(firstname.getText().toString());
        user.setPassword(pw.getText().toString());
        user.setDestinationId(this.destinations.get(spinnerDest.getSelectedItemPosition()).getIdDestination());
        user.setUsertypeId(this.userTypes.get(spinnerTypes.getSelectedItemPosition()).getIdUserType());
        user.setQuizpoints(0);

        //Set current user object
        setCurrentUser(user.getUsername(), user.getLastname(), user.getFirstname(), user.getPassword(),
                user.getDestinationId(), user.getUsertypeId(), user.getQuizpoints());

        new UserAsync(this, user, true).execute();
        //navigate to home
    }

    public void goToHome(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    private boolean isEmptyCheck(String etText) {
        return etText.trim().length() == 0;
    }

    private boolean testPasswordEquals(EditText pw){
        EditText pwRep = (EditText) findViewById(R.id.reditPasswordAgain);
        if(pw.getText().toString().equalsIgnoreCase(pwRep.getText().toString()))return true;
        return false;
    }

    private boolean usernameAvailableCheck(String username){
        if(CurrentRegisteredUsers.getUserList().size() == 0)return false;
        for(User user : CurrentRegisteredUsers.getUserList()){
            if(user.getUsername().toString().equalsIgnoreCase(username))return false;
        }
        return true;
    }

    private boolean registerChecks(){
        boolean ok = true;

        EditText username = (EditText) findViewById(R.id.reditUsername);
        EditText lastname = (EditText) findViewById(R.id.reditLastname);
        EditText firstname = (EditText) findViewById(R.id.reditFirstname);
        EditText pw = (EditText) findViewById(R.id.reditPassword);

        if(isEmptyCheck(username.getText().toString())){
            username.setError(getResources().getString(R.string.pageregister_usernameEmpty));
            ok = false;
        }
        if(isEmailValid(username.getText().toString())){
            username.setError(getResources().getString(R.string.pageregister_notEmail));
            ok = false;
        }
        if(!usernameAvailableCheck(username.getText().toString())){
            username.setError(getResources().getString(R.string.pageregister_usernameExists));
            ok = false;
        }
        if(isEmptyCheck(lastname.getText().toString())){
            lastname.setError(getResources().getString(R.string.pageregister_nameEmpty));
            ok = false;
        }
        if (isEmptyCheck(firstname.getText().toString())){
            firstname.setError(getResources().getString(R.string.pageregister_firstnameEmpty));
            ok = false;
        }
        if(isEmptyCheck(pw.getText().toString())){
            pw.setError(getResources().getString(R.string.pageregister_pwEmtpy));
            ok = false;
        }
        if(!testPasswordEquals(pw)){
            EditText pwRep = (EditText) findViewById(R.id.reditPasswordAgain);
            pwRep.setText("");
            pwRep.setError(getResources().getString(R.string.pageregister_pwEqualTest));
            ok = false;
        }

        return ok;
    }

    public final static boolean isEmailValid(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void setCurrentUser(String username, String lastname, String firstname, String password,
                                int destinationId, int usertypeId, int quizPoints){
        CurrentUser.setUsername(username);
        CurrentUser.setLastname(lastname);
        CurrentUser.setFirstname(firstname);
        CurrentUser.setPassword(password);
        CurrentUser.setDestinationId(destinationId);
        CurrentUser.setUsertypeId(usertypeId);
        CurrentUser.setQuizpoints(quizPoints);
    }

}
