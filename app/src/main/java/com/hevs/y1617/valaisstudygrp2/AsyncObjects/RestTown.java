package com.hevs.y1617.valaisstudygrp2.AsyncObjects;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hevs.y1617.valaisstudygrp2.DataObjects.Town;
import com.hevs.y1617.valaisstudygrp2.Home;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 22.11.2016.
 */

public class RestTown {

    public static ProgressDialog mDialog = null;
    public static List<Town> townList = null;
    public static Context contextNew;

    public RestTown(){}

    public static void getRestData(Context c){
        contextNew = c;
        //Set mDialog
        mDialog = ProgressDialog.show(c,
                "Initialize Data", "Wait ...", true, true);

        townList = new ArrayList<>();
        RequestQueue rq = Volley.newRequestQueue(c);
        final String URL = "https://extranet.tourobs.ch/ApiTourobs/Town";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 1; i < response.length(); i++) {
                    try {
                        JSONArray jsonArrays = response.getJSONArray(i);
                        if(!jsonArrays.getString(2).equals("null")){
                            townList.add(new Town(Integer.parseInt(jsonArrays.getString(0)), Integer.parseInt(jsonArrays.getString(1)),
                                    Integer.parseInt(jsonArrays.getString(2)), jsonArrays.getString(3)));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                logTownData();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.toString());
            }
        });

        RequestQueue.RequestFinishedListener listener = getRequestListener();
        rq.addRequestFinishedListener(listener);

        // Add the request to the RequestQueue.
        rq.add(jsonArrayRequest);
    }

    private static RequestQueue.RequestFinishedListener getRequestListener(){
        return new RequestQueue.RequestFinishedListener() {
            @Override
            public void onRequestFinished(Request request) {
                SyncListener.execSynchronization((Home)contextNew);
                mDialog.dismiss();
            }
        };
    }

    private static void logTownData(){
        for(Town d : townList){
            Log.d("Towns",d.toString());
        }
    }

}
