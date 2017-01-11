package com.hevs.y1617.valaisstudygrp2.AsyncObjects;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.hevs.y1617.valaisstudygrp2.DataObjects.Town;
import com.hevs.y1617.valaisstudygrp2.Login;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 21.11.2016.
 */

public class RestTownAsync extends AsyncTask<String, Void, String> {

    Login login = null;
    ProgressDialog mDialog = null;

    List<Town> townList = null;

    private static final String URL = "https://extranet.tourobs.ch/ApiTourobs/Town";
    StreamConverter sc = new StreamConverter();

    public RestTownAsync(Login login){
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
        townList = new ArrayList<Town>();


        try {
            JSONArray jsonarray = new JSONArray(res);
            for (int i = 1; i < jsonarray.length(); i++) {
                JSONArray jsonArrays = jsonarray.getJSONArray(i);
                if(!jsonArrays.getString(2).equals("null")){
                    townList.add(new Town(Integer.parseInt(jsonArrays.getString(0)), Integer.parseInt(jsonArrays.getString(1)),
                            Integer.parseInt(jsonArrays.getString(2)), jsonArrays.getString(3)));
                }
            }
            for(Town d : townList){
                Log.d("Towns",d.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("GetTown", res);
        if(mDialog != null){
            mDialog.dismiss();
        }
    }

}
