package com.hevs.y1617.valaisstudygrp2.AsyncObjects;

import android.util.Log;

import com.hevs.y1617.valaisstudygrp2.Login;

/**
 * Created by Acer on 18.12.2016.
 */

public class SyncStatisticListener {

    private static int syncWaiter = 5;

    public static void execSynchronization(Login login){
        syncWaiter--;
        Log.d("syncwaiterStatistic", syncWaiter+"");
        if(syncWaiter == 0){
            //reset data
            syncWaiter = 5;
            //login.getDataFromRestAPI();
        }
    }
}
