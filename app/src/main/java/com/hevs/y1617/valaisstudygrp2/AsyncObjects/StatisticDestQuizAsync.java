package com.hevs.y1617.valaisstudygrp2.AsyncObjects;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.example.acer.myapplication.backend.statisticDestQuizApi.StatisticDestQuizApi;
import com.example.acer.myapplication.backend.statisticDestQuizApi.model.StatisticDestQuiz;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.hevs.y1617.valaisstudygrp2.Home;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 16.12.2016.
 */

public class StatisticDestQuizAsync extends AsyncTask<Void, Void, List<StatisticDestQuiz>> {

    private StatisticDestQuizApi sdqApi = null;
    private final String TAG = StatisticDestQuizAsync.class.getName();

    Home home = null;
    boolean insert = false;

    ProgressDialog mDialog = null;

    List<StatisticDestQuiz> sdqList = new ArrayList<>();

    public StatisticDestQuizAsync(Home home, List<StatisticDestQuiz> sdqList){
        this.home = home;
        this.sdqList = sdqList;
    }

    @Override
    protected void onPreExecute() {
        if(this.home != null){
            mDialog = ProgressDialog.show(home,
                    "Load in progress", "Wait ...", true, true);
        }
    }

    @Override
    protected List<StatisticDestQuiz> doInBackground(Void... params) {
        if(sdqApi == null){
            // Only do this once
            StatisticDestQuizApi.Builder builder = new StatisticDestQuizApi.Builder(AndroidHttp.newCompatibleTransport(),
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
            sdqApi = builder.build();
        }

        try{
            //Insert User
            if(this.home != null && this.sdqList.size() != 0){
                for(StatisticDestQuiz statisticDestQuiz : sdqList){
                    sdqApi.insert(statisticDestQuiz).execute();
                    Log.i(TAG, "insert statisticDestQuiz");
                }
                SyncListener.execSynchronization(this.home);
                // SyncStatisticListener.execSynchronization(this.home);
            }

            // and for instance return the list of all teams
            return sdqApi.list().execute().getItems();

        } catch (IOException e){
            Log.e(TAG, e.toString());
            return new ArrayList<StatisticDestQuiz>();
        }
    }

    @Override
    protected void onPostExecute(List<StatisticDestQuiz> result){
        if(result != null) {
            if(this.home != null){
                mDialog.dismiss();
            }
        }
    }

}
