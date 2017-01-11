package com.hevs.y1617.valaisstudygrp2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentLanguage;
import com.hevs.y1617.valaisstudygrp2.DataObjects.CurrentLearningOffline;
import com.hevs.y1617.valaisstudygrp2.DataObjects.LearningOffline;
import com.hevs.y1617.valaisstudygrp2.db.GetRestDataDB;

import java.util.List;

public class destinationLearningOffline extends AppCompatActivity {

    private static List<LearningOffline> learningList = null;

    private TextView textViewHeaderBig;
    private TextView textViewHeaderLittle;
    private TextView textViewParagraph;
    private TextView textViewHeaderLittle2;
    private TextView textViewHList;
    private TextView textViewHeaderLittle3;
    private TextView textViewParagraph2;

    private int idDestination;
    private int idTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_learning_offline);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setLearningInfos(getIntent().getIntExtra("ChosenDestinationId", 1), CurrentLearningOffline.getCurrentLearningTopic());
    }

    private void setLearningInfos(int idDestination, int idTheme) {
        this.learningList = GetRestDataDB.getLearningInfosFromDB(this);

        if (this.learningList.isEmpty()) {
            return;
        }

        textViewHeaderBig = (TextView) findViewById(R.id.textViewHeaderBig);
        textViewHeaderLittle = (TextView) findViewById(R.id.textViewHeaderLittle);
        textViewParagraph = (TextView) findViewById(R.id.textViewParagraph1);
        textViewHeaderLittle2 = (TextView) findViewById(R.id.textViewHeaderLittle2);
        textViewHList = (TextView) findViewById(R.id.textViewList);
        textViewHeaderLittle3 = (TextView) findViewById(R.id.textViewHeaderLittle3);
        textViewParagraph2 = (TextView) findViewById(R.id.textViewParagraph2);

        String Headerbig = "";
        String HeaderLittle = "";
        String Paragraph1 = "";
        String HeaderLittle2 = "";
        String List = "";
        String HeaderLittle3 = "";
        String Paragraph2 = "";

        for (LearningOffline q : this.learningList)
        {
            Log.i("",q.toString());
            if (q.getIdDestination() == idDestination && q.getIdTheme() == idTheme) {
                switch (CurrentLanguage.getCurrentLanguage()) {
                    case "fr":
                        Headerbig = "<h1>"+ q.getHeaderBigFR() + "</h1>";
                        HeaderLittle = "<h3>" + q.getHeaderLittle1FR() + "</h3>";
                        Paragraph1 = "<p>" + q.getParagraph1FR() + "</p>";
                        HeaderLittle2 = "<h3>" + q.getHeaderLittle2FR() + "</h3>";
                        List = q.getListFR();
                        HeaderLittle3 = "<h3>" + q.getHeaderLittle3FR() + "</h3>";
                        Paragraph2 = "<p>" + q.getParagraph2FR() + "</p>";
                        break;
                    case "de":
                        Headerbig = "<h1>"+ q.getHeaderBigDE() + "</h1>";
                        HeaderLittle = "<h3>" + q.getHeaderLittle1DE() + "</h3>";
                        Paragraph1 = "<p>" + q.getParagraph1DE() + "</p>";
                        HeaderLittle2 = "<h3>" + q.getHeaderLittle2DE() + "</h3>";
                        List = q.getListDE();
                        HeaderLittle3 = "<h3>" + q.getHeaderLittle3DE() + "</h3>";
                        Paragraph2 = "<p>" + q.getParagraph2DE() + "</p>";
                }
            }
        }
        Headerbig = Headerbig.replace(";","<br>");
        textViewHeaderBig.setText(Html.fromHtml(Headerbig));
        HeaderLittle = HeaderLittle.replace(";","<br>");
        textViewHeaderLittle.setText(Html.fromHtml(HeaderLittle));
        Paragraph1 = Paragraph1.replace(";","<br>");
        textViewParagraph.setText(Html.fromHtml(Paragraph1));
        HeaderLittle2 = HeaderLittle2.replace(";","<br>");
        textViewHeaderLittle2.setText(Html.fromHtml(HeaderLittle2));
        if(List != null) {
            List = List.replace(";", "<br>");
            textViewHList.setText(Html.fromHtml(List));
        }
        HeaderLittle3 = HeaderLittle3.replace(";","<br>");
        textViewHeaderLittle3.setText(Html.fromHtml(HeaderLittle3));
        Paragraph2 = Paragraph2.replace(";","<br>");
        textViewParagraph2.setText(Html.fromHtml(Paragraph2));

    }


}
