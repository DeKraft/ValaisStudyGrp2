package com.hevs.y1617.valaisstudygrp2.DataObjects;

/**
 * Created by Acer on 18.11.2016.
 */

public class CurrentLanguage {

    private static String currentLanguage = "de";

    public static String getCurrentLanguage() {
        return currentLanguage;
    }

    public static void setCurrentLanguage(String currentLanguage) {
        CurrentLanguage.currentLanguage = currentLanguage;
    }
}
