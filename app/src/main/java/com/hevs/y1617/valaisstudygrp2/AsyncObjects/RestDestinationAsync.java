package com.hevs.y1617.valaisstudygrp2.AsyncObjects;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.hevs.y1617.valaisstudygrp2.DataObjects.Destination;
import com.hevs.y1617.valaisstudygrp2.Login;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 20.11.2016.
 */

public class RestDestinationAsync extends AsyncTask<String, Void, String> {

    Login login = null;
    ProgressDialog mDialog = null;

    List<Destination> destinationList = null;

    private static final String URL = "https://extranet.tourobs.ch/ApiTourobs/Destination";
    StreamConverter sc = new StreamConverter();

    public RestDestinationAsync(Login login){
        this.login = login;
    }

    @Override
    protected void onPreExecute() {
        mDialog = ProgressDialog.show(login,
                "Initialize Data", "Wait ...", true, true);
    }

    @Override
    protected String doInBackground(String... params) {
        Log.i("", URL);
        return sc.requestContent(URL);
    }

    @Override
    protected void onPostExecute(String res) {
        destinationList = new ArrayList<Destination>();

        try {
            JSONArray jsonarray = new JSONArray(res);
            for (int i = 1; i < jsonarray.length(); i++) {
                JSONArray jsonArrays = jsonarray.getJSONArray(i);
                destinationList.add(new Destination(Integer.parseInt(jsonArrays.getString(0)), jsonArrays.getString(1)));
                //Log.i("",jsonArrays.getString(j));
            }
            for(Destination d : destinationList){
                Log.d("Dests",d.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("GetDest", res);
        if(mDialog != null){
            mDialog.dismiss();
        }
    }

}
