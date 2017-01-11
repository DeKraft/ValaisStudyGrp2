package com.hevs.y1617.valaisstudygrp2.AsyncObjects;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.example.acer.myapplication.backend.statisticTopQuizApi.StatisticTopQuizApi;
import com.example.acer.myapplication.backend.statisticTopQuizApi.model.StatisticTopQuiz;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.hevs.y1617.valaisstudygrp2.Home;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 18.12.2016.
 */

public class StatisticTopQuizAsync extends AsyncTask<Void, Void, List<StatisticTopQuiz>> {

    private StatisticTopQuizApi sdqApi = null;
    private final String TAG = StatisticTopQuizAsync.class.getName();

    Home home = null;
    boolean insert = false;

    ProgressDialog mDialog = null;

    List<StatisticTopQuiz> sdqList = new ArrayList<>();

    public StatisticTopQuizAsync(Home home, List<StatisticTopQuiz> sdqList){
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
    protected List<StatisticTopQuiz> doInBackground(Void... params) {
        if(sdqApi == null){
            // Only do this once
            StatisticTopQuizApi.Builder builder = new StatisticTopQuizApi.Builder(AndroidHttp.newCompatibleTransport(),
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
                for(StatisticTopQuiz statisticTopQuiz : sdqList){
                    sdqApi.insert(statisticTopQuiz).execute();
                    Log.i(TAG, "insert statisticTopicQuiz");
                }
                SyncListener.execSynchronization(this.home);
                //SyncStatisticListener.execSynchronization(this.home);
            }

            // and for instance return the list of all teams
            return sdqApi.list().execute().getItems();

        } catch (IOException e){
            Log.e(TAG, e.toString());
            return new ArrayList<StatisticTopQuiz>();
        }
    }

    @Override
    protected void onPostExecute(List<StatisticTopQuiz> result){
        if(result != null) {
            if(this.home != null){
                mDialog.dismiss();
            }
        }
    }

}
