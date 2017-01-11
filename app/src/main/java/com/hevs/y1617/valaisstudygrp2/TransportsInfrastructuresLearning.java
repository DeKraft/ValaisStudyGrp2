package com.hevs.y1617.valaisstudygrp2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentLanguage;

public class TransportsInfrastructuresLearning extends AppCompatActivity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transports_infrastructures_learning);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       getApiInfo(getIntent().getIntExtra("ChosenDestinationId", 1), CurrentLanguage.getCurrentLanguage().toUpperCase());

    }

    protected void getApiInfo(int idDestination, String lang)
    {
        this.myWebView = (WebView) findViewById(R.id.activity_TransportsInfrastructuresLearning_webview);

        // Enable Javascript
        this.myWebView.getSettings().setJavaScriptEnabled(true);

        String html = "<iframe width=\"100%\" height=\"100%\" src=\"https://extranet.tourobs.ch/ApiTourobs/LearningTransport_Infrastructure?id_dest="+idDestination+"&lang="+lang+"\" />";

        this.myWebView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
    }

}
