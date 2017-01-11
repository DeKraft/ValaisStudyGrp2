package com.hevs.y1617.valaisstudygrp2.AsyncObjects;

import android.util.Log;

import com.hevs.y1617.valaisstudygrp2.Home;

/**
 * Created by Acer on 11.12.2016.
 */

public class SyncListener {

    private static int syncWaiter = 13;

    public static void execSynchronization(final Home home){
        syncWaiter--;
        Log.d("syncwaiter", syncWaiter+"");
        if(syncWaiter == 0){
            //reset data
            syncWaiter = 13;
            home.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    home.synchronizeData();
                }
            });
        }
    }

}
