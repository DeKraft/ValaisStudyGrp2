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
import com.hevs.y1617.valaisstudygrp2.DataObjects.QuizAnswer;
import com.hevs.y1617.valaisstudygrp2.Home;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 24.11.2016.
 */

public class RestQuizAnswer {

    public static ProgressDialog mDialog = null;
    public static List<QuizAnswer> quizAnswerList = null;
    public static List<QuizAnswer> questionsAnswersDE = null;
    public static int queueSize = 2;
    public static Context contextNew;

    public RestQuizAnswer(){}

    public static void getRestData(Context c){
        contextNew = c;
        //Set mDialog
        mDialog = ProgressDialog.show(c,
                "Initialize Data", "Wait ...", true, true);

        quizAnswerList = new ArrayList<>();
        questionsAnswersDE = new ArrayList<>();
        RequestQueue rq = Volley.newRequestQueue(c);
        final String URLfr= "https://extranet.tourobs.ch/ApiTourobs/QuizAnswer?&lang=FR";
        final String URLde= "https://extranet.tourobs.ch/ApiTourobs/QuizAnswer?&lang=DE";

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
                Log.i("quizanswer finished", request.getUrl().toString());
                Log.i("quizanswer finished", queueSize + "");
                if(quizAnswerQueueFinished() == 0){
                    RestQuizAnswer.queueSize = 2;
                    setLanguageToQuizAnswer();
                    SyncListener.execSynchronization((Home)contextNew);
                    mDialog.dismiss();
                }
            }
        };
    }

    private static int quizAnswerQueueFinished(){
        return --RestQuizAnswer.queueSize;
    }

    private static JsonArrayRequest getJsonDataRequest(String URLfr){
        return new JsonArrayRequest(Request.Method.GET, URLfr, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 1; i < response.length(); i++) {
                    try {
                        JSONArray jsonArrays = response.getJSONArray(i);
                        QuizAnswer quizAnswer = new QuizAnswer(Integer.parseInt(jsonArrays.getString(0)), Integer.parseInt(jsonArrays.getString(1)),
                                jsonArrays.getString(2), jsonArrays.getString(3), jsonArrays.getString(4));
                        quizAnswerList.add(quizAnswer);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                logQuizAnswerData();
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
                        QuizAnswer quizAnswer = new QuizAnswer(jsonArrays.getString(2),jsonArrays.getString(3),jsonArrays.getString(4));
                        questionsAnswersDE.add(quizAnswer);
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

    private static void logQuizAnswerData(){
        for(QuizAnswer d : quizAnswerList){
            Log.d("QuizAnswer",d.toString());
        }
    }

    private static void setLanguageToQuizAnswer(){
        if(RestQuizAnswer.quizAnswerList.size() != 0 && RestQuizAnswer.questionsAnswersDE.size() != 0){
            for(int i=0;i<RestQuizAnswer.quizAnswerList.size();i++){
                RestQuizAnswer.quizAnswerList.get(i).setGoodAnswerDE(RestQuizAnswer.questionsAnswersDE.get(i).getGoodAnswerDE());
                RestQuizAnswer.quizAnswerList.get(i).setBadAnswerDE(RestQuizAnswer.questionsAnswersDE.get(i).getBadAnswerDE());
                RestQuizAnswer.quizAnswerList.get(i).setInfoAnswerDE(RestQuizAnswer.questionsAnswersDE.get(i).getInfoAnswerDE());
            }
        }
    }


}
