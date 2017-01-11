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
import com.hevs.y1617.valaisstudygrp2.DataObjects.QuizUserType;
import com.hevs.y1617.valaisstudygrp2.Home;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 24.11.2016.
 */

public class RestQuizUserType {

    public static ProgressDialog mDialog = null;
    public static List<QuizUserType> usertypeList = null;
    public static List<String> usertypeDE = null;
    public static int queueSize = 2;
    public static Context contextNew;

    public RestQuizUserType(){}

    public static void getRestData(Context c){
        contextNew = c;
        //Set mDialog
        mDialog = ProgressDialog.show(c,
                "Initialize Data", "Wait ...", true, true);

        usertypeList = new ArrayList<>();
        usertypeDE = new ArrayList<>();
        RequestQueue rq = Volley.newRequestQueue(c);
        final String URLfr= "https://extranet.tourobs.ch/ApiTourobs/QuizUserType?&lang=FR";
        final String URLde= "https://extranet.tourobs.ch/ApiTourobs/QuizUserType?&lang=DE";

        JsonArrayRequest jsonArrayRequest = getJsonDataRequest(URLfr);
        JsonArrayRequest jsonArrayRequestDE = getJsonDataRequestDE(URLde);
        // Add the request to the RequestQueue.

        RequestQueue.RequestFinishedListener listener = getRequestListener();
        rq.addRequestFinishedListener(listener);

        rq.add(jsonArrayRequest);
        rq.add(jsonArrayRequestDE);


    }

    private static RequestQueue.RequestFinishedListener getRequestListener(){
        return new RequestQueue.RequestFinishedListener() {
            @Override
            public void onRequestFinished(Request request) {
                Log.i("quizType finished", request.getUrl().toString());
                Log.i("quizType finished", queueSize + "");
                if(quizUserTypeQueueFinished() == 0){
                    RestQuizUserType.queueSize = 2;
                    setLanguageToQuizLevel();
                    SyncListener.execSynchronization((Home)contextNew);
                    mDialog.dismiss();
                }
            }
        };
    }

    private static int quizUserTypeQueueFinished(){
        return --RestQuizUserType.queueSize;
    }

    private static JsonArrayRequest getJsonDataRequest(String URLfr){
        return new JsonArrayRequest(Request.Method.GET, URLfr, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 1; i < response.length(); i++) {
                    try {
                        JSONArray jsonArrays = response.getJSONArray(i);
                        QuizUserType quizUserType = new QuizUserType(Integer.parseInt(jsonArrays.getString(0)), jsonArrays.getString(1));
                        usertypeList.add(quizUserType);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                logQuizUserTypeData();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.toString());
            }
        });
    }

    private static JsonArrayRequest getJsonDataRequestDE(String URLde){
        return new JsonArrayRequest(Request.Method.GET, URLde, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 1; i < response.length(); i++) {
                    try {
                        JSONArray jsonArrays = response.getJSONArray(i);
                        usertypeDE.add(jsonArrays.getString(1));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.toString());
            }
        });
    }

    private static void logQuizUserTypeData(){
        for(QuizUserType d : usertypeList){
            Log.d("QuizType",d.toString());
        }
    }

    private static void setLanguageToQuizLevel(){
        if(RestQuizUserType.usertypeList.size() != 0 && RestQuizUserType.usertypeDE.size() != 0){
            for(int i=0;i<RestQuizUserType.usertypeList.size();i++){
                RestQuizUserType.usertypeList.get(i).setNameUserTypeDE(RestQuizUserType.usertypeDE.get(i));
            }
        }
    }

}
