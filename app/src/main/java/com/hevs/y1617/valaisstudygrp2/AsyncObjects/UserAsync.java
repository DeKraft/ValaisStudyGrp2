package com.hevs.y1617.valaisstudygrp2.AsyncObjects;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.example.acer.myapplication.backend.userApi.UserApi;
import com.example.acer.myapplication.backend.userApi.model.User;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentRegisteredUsers;
import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentUser;
import com.hevs.y1617.valaisstudygrp2.Login;
import com.hevs.y1617.valaisstudygrp2.Quizfinish;
import com.hevs.y1617.valaisstudygrp2.Rank;
import com.hevs.y1617.valaisstudygrp2.Registration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 17.11.2016.
 */

public class UserAsync extends AsyncTask<Void, Void, List<User>> {

    private UserApi userApi = null;
    private final String TAG = UserAsync.class.getName();

    User user = null;

    Registration registration = null;
    boolean insert = false;

    Login login = null;

    Rank rank = null;

    String username = "";

    Quizfinish quizfinish = null;
    boolean update = false;

    ProgressDialog mDialog = null;

    public UserAsync(Registration registration) {
        this.registration = registration;
    }

    public UserAsync(Rank rank) {
        this.rank = rank;
    }

    public UserAsync(Login login) {
        this.login = login;
    }

    public UserAsync(Quizfinish quizfinish, boolean update) {
        this.quizfinish = quizfinish;
        this.update = update;
    }

    public UserAsync(Registration registration, User user, boolean insert) {
        this.registration = registration;
        this.user = user;
        this.insert = insert;
    }

    @Override
    protected void onPreExecute() {
        if (this.login != null) {
            mDialog = ProgressDialog.show(login,
                    "Load in progress", "Wait ...", true, true);
        }
        if (this.registration != null) {
            mDialog = ProgressDialog.show(registration,
                    "Load in progress", "Wait ...", true, true);
        }
        if (this.quizfinish != null) {
            mDialog = ProgressDialog.show(quizfinish,
                    "Load in progress", "Wait ...", true, true);
        }
        if (this.rank != null) {
            mDialog = ProgressDialog.show(rank,
                    "Load in progress", "Wait ...", true, true);
        }
    }

    @Override
    protected List<User> doInBackground(Void... params) {
        if (userApi == null) {
            // Only do this once
            UserApi.Builder builder = new UserApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    // if you deploy on the cloud backend, use your app name
                    // such as https://<your-app-id>.appspot.com
                    .setRootUrl("https://valaisstudygrp2-149817.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            builder.setApplicationName("ValaisStudy").build();
            userApi = builder.build();
        }

        try {
            //Insert User
            if (insert && user != null) {
                userApi.insert(user).execute();
                Log.i(TAG, "insert user");
                this.username = user.getUsername().toString();
                user = null;
            }
            if (quizfinish != null && update) {
                user = CurrentUser.getUser();
                Log.i(TAG, "update user");
                userApi.update(user.getId(), user).execute();
            }
            // and for instance return the list of all teams
            return userApi.list().execute().getItems();

        } catch (IOException e) {
            Log.e(TAG, e.toString());
            return new ArrayList<User>();
        }
    }

    @Override
    protected void onPostExecute(List<User> result) {
        if (result != null) {
            Log.i("size", result.size() + "");
            for (User user : result) {
                Log.i(TAG, "Member name: " + user.getUsername());
                Log.i(TAG, "Member ID: " + user.getId());
                if (!this.username.equals("")) {
                    if (this.username.equals(user.getUsername())) {
                        CurrentUser.setId(user.getId());
                        this.username = "";
                    }
                }
            }
            if (this.login != null) {
                CurrentRegisteredUsers.setUserList(result);
                this.login.executeLogin();
            }
            if (this.registration != null && !insert) {
                CurrentRegisteredUsers.setUserList(result);
                this.registration.registerExec();
            }
            if (this.registration != null && insert) {
                this.registration.goToHome();
            }
            if (this.rank != null) {
                CurrentRegisteredUsers.setUserList(result);
                this.rank.setUserLevelEmblem();
                if(result.size() != 0){
                    this.rank.setBestPlayers();
                }
            }
            if (mDialog != null) {
                mDialog.dismiss();
            }
        }
    }

}
