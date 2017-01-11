package com.hevs.y1617.valaisstudygrp2.DataObjects;

/**
 * Created by Acer on 12.12.2016.
 */

public class CurrentLearningOffline {

    private static int currentLearningTopic = 0;

    public static int getCurrentLearningTopic() {
        return currentLearningTopic;
    }

    public static void setCurrentLearningTopic(int currentLearningTopic) {
        CurrentLearningOffline.currentLearningTopic = currentLearningTopic;
    }
}
