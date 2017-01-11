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
import com.hevs.y1617.valaisstudygrp2.DataObjects.Destination;
import com.hevs.y1617.valaisstudygrp2.Home;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 22.11.2016.
 */

public class RestDestination {

    public static ProgressDialog mDialog = null;
    public static List<Destination> destinationList = null;
    public static Context contextNew;

    public RestDestination(){}

    public static void getRestData(Context c){
        contextNew = c;
        Log.i("context", c.toString());
        //Set mDialog
        mDialog = ProgressDialog.show(c,
                "Initialize Data", "Wait ...", true, true);

        destinationList = new ArrayList<>();
        RequestQueue rq = Volley.newRequestQueue(c);
        final String URL = "https://extranet.tourobs.ch/ApiTourobs/Destination";

        JsonArrayRequest jsonArrayRequest = getJsonDataRequest(URL);

        RequestQueue.RequestFinishedListener listener = getRequestListener();
        rq.addRequestFinishedListener(listener);

        // Add the request to the RequestQueue.
        rq.add(jsonArrayRequest);
    }

    private static JsonArrayRequest getJsonDataRequest(String URL){
        return new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 1; i < response.length(); i++) {
                    try {
                        JSONArray jsonArrays = response.getJSONArray(i);
                        destinationList.add(new Destination(Integer.parseInt(jsonArrays.getString(0)), jsonArrays.getString(1)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                logDestinationData();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.toString());
            }
        });
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

    private static void logDestinationData(){
        for(Destination d : destinationList){
            Log.d("Dests",d.toString());
        }
    }


}
