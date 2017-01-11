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
import com.hevs.y1617.valaisstudygrp2.DataObjects.QuizTheme;
import com.hevs.y1617.valaisstudygrp2.Home;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 24.11.2016.
 */

public class RestQuizTheme {

    public static ProgressDialog mDialog = null;
    public static List<QuizTheme> themeList = null;
    public static List<String> themeDE = null;
    public static int queueSize = 2;
    public static Context contextNew;

    public RestQuizTheme(){}

    public static void getRestData(Context c){
        contextNew = c;
        //Set mDialog
        mDialog = ProgressDialog.show(c,
                "Initialize Data", "Wait ...", true, true);

        themeList = new ArrayList<>();
        themeDE = new ArrayList<>();
        RequestQueue rq = Volley.newRequestQueue(c);
        final String URLfr= "https://extranet.tourobs.ch/ApiTourobs/QuizTheme?&lang=FR";
        final String URLde= "https://extranet.tourobs.ch/ApiTourobs/QuizTheme?&lang=DE";

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
                Log.i("quizTheme finished", request.getUrl().toString());
                Log.i("quizTheme finished", queueSize + "");
                if(quizQueueFinished() == 0){
                    RestQuizTheme.queueSize = 2;
                    setLanguageToQuizTheme();
                    SyncListener.execSynchronization((Home)contextNew);
                    mDialog.dismiss();
                }
            }
        };
    }

    private static int quizQueueFinished(){
        return --RestQuizTheme.queueSize;
    }

    private static JsonArrayRequest getJsonDataRequest(String URLfr){
        return new JsonArrayRequest(Request.Method.GET, URLfr, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 1; i < response.length(); i++) {
                    try {
                        JSONArray jsonArrays = response.getJSONArray(i);
                        QuizTheme quizTheme = new QuizTheme(Integer.parseInt(jsonArrays.getString(0)), jsonArrays.getString(1),
                                Boolean.parseBoolean(jsonArrays.getString(2)));
                        themeList.add(quizTheme);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                logQuizThemeData();
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
                        themeDE.add(jsonArrays.getString(1));
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

    private static void logQuizThemeData(){
        for(QuizTheme d : themeList){
            Log.d("QuizTheme",d.toString());
        }
    }

    private static void setLanguageToQuizTheme(){
        if(RestQuizTheme.themeList.size() != 0 && RestQuizTheme.themeDE.size() != 0){
            for(int i=0;i<RestQuizTheme.themeList.size();i++){
                RestQuizTheme.themeList.get(i).setNameThemeDE(RestQuizTheme.themeDE.get(i));
            }
        }
    }

}
