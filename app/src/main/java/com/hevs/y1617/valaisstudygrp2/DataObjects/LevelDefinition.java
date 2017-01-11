package com.hevs.y1617.valaisstudygrp2.DataObjects;

/**
 * Created by Dennis on 07.12.2016.
 */

public class LevelDefinition {

    private static final int level1 = 200;
    private static final int level2 = 750;
    private static final int level3 = 1500;

    public static int getLevel1() {
        return level1;
    }

    public static int getLevel2() {
        return level2;
    }

    public static int getLevel3() {
        return level3;
    }

    public static int getLevel(int points){
        if(points < level1) return 1;

        if(points < level2) return 2;

        if(points < level3) return 3;

        return 4;
    }
}
