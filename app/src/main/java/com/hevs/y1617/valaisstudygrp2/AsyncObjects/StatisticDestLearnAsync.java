package com.hevs.y1617.valaisstudygrp2.AsyncObjects;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.example.acer.myapplication.backend.statisticDestLearnApi.StatisticDestLearnApi;
import com.example.acer.myapplication.backend.statisticDestLearnApi.model.StatisticDestLearn;
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

public class StatisticDestLearnAsync extends AsyncTask<Void, Void, List<StatisticDestLearn>> {

    private StatisticDestLearnApi sdqApi = null;
    private final String TAG = StatisticDestLearnAsync.class.getName();

    Home home = null;
    boolean insert = false;

    ProgressDialog mDialog = null;

    List<StatisticDestLearn> sdqList = new ArrayList<>();

    public StatisticDestLearnAsync(Home home, List<StatisticDestLearn> sdqList){
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
    protected List<StatisticDestLearn> doInBackground(Void... params) {
        if(sdqApi == null){
            // Only do this once
            StatisticDestLearnApi.Builder builder = new StatisticDestLearnApi.Builder(AndroidHttp.newCompatibleTransport(),
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
                for(StatisticDestLearn statisticDestLearn : sdqList){
                    sdqApi.insert(statisticDestLearn).execute();
                    Log.i(TAG, "insert statisticDestLearn");
                }
                SyncListener.execSynchronization(this.home);
                //SyncStatisticListener.execSynchronization(this.home);
            }

            // and for instance return the list of all teams
            return sdqApi.list().execute().getItems();

        } catch (IOException e){
            Log.e(TAG, e.toString());
            return new ArrayList<StatisticDestLearn>();
        }
    }

    @Override
    protected void onPostExecute(List<StatisticDestLearn> result){
        if(result != null) {
            if(this.home != null){
                mDialog.dismiss();
            }
        }
    }

}