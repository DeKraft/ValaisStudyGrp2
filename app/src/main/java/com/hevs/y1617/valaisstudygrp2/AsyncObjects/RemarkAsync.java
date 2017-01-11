package com.hevs.y1617.valaisstudygrp2.AsyncObjects;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.example.acer.myapplication.backend.remarkApi.RemarkApi;
import com.example.acer.myapplication.backend.remarkApi.model.Remark;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.hevs.y1617.valaisstudygrp2.Home;
import com.hevs.y1617.valaisstudygrp2.Quizfinish;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 05.12.2016.
 */

public class RemarkAsync extends AsyncTask<Void, Void, List<Remark>> {

    private RemarkApi remarkApi = null;
    private final String TAG = RemarkAsync.class.getName();

    Quizfinish quizfinish = null;
    boolean insert = false;
    Remark remark;

    Home home = null;

    ProgressDialog mDialog = null;

    List<Remark> remarkList = new ArrayList<>();

    public RemarkAsync(Quizfinish quizfinish, boolean insert, Remark remark){
        this.quizfinish = quizfinish;
        this.insert = insert;
        this.remark = remark;
    }

    public RemarkAsync(Home home, boolean insert, List<Remark> remarks){
        this.insert = insert;
        this.home = home;
        this.remarkList = remarks;
    }

    @Override
    protected void onPreExecute() {
        if(this.quizfinish != null){
            mDialog = ProgressDialog.show(quizfinish,
                    "Load in progress", "Wait ...", true, true);
        }
    }

    @Override
    protected List<Remark> doInBackground(Void... params) {
        if(remarkApi == null){
            // Only do this once
            RemarkApi.Builder builder = new RemarkApi.Builder(AndroidHttp.newCompatibleTransport(),
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
            remarkApi = builder.build();
        }

        try{
            //Insert Remark
            if(insert && quizfinish != null){
                remarkApi.insert(remark).execute();
                Log.i(TAG, "insert remark");
                remark = null;
            }

            if(insert && this.remarkList.size() != 0 && this.home != null){
                for(Remark remark : this.remarkList){
                    remarkApi.insert(remark).execute();
                    Log.i(TAG, "insert remark local");
                }
                SyncListener.execSynchronization(this.home);
                //SyncStatisticListener.execSynchronization(this.home);
            }
            // and for instance return the list of all teams
            return remarkApi.list().execute().getItems();

        } catch (IOException e){
            Log.e(TAG, e.toString());
            return new ArrayList<Remark>();
        }
    }

    @Override
    protected void onPostExecute(List<Remark> result){
        if(this.quizfinish != null){
            this.mDialog.dismiss();
        }
        if(result != null) {
            if(this.quizfinish != null){
            }
        }
    }

}
