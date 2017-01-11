package com.hevs.y1617.valaisstudygrp2.DataObjects;

/**
 * Created by Acer on 18.11.2016.
 */

import com.example.acer.myapplication.backend.userApi.model.User;

public class CurrentUser {

    private static Long id = -1l;
    private static String username = "";
    private static String lastname = "";
    private static String firstname = "";
    private static String password = "";
    private static int destinationId = -1;
    private static int usertypeId = 0;
    private static int quizpoints = 0;
    private static boolean chooseDest = false;

    public static Long getId() {
        return id;
    }

    public static void setId(Long id) {
        CurrentUser.id = id;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        CurrentUser.username = username;
    }

    public static String getLastname() {
        return lastname;
    }

    public static void setLastname(String lastname) {
        CurrentUser.lastname = lastname;
    }

    public static String getFirstname() {
        return firstname;
    }

    public static void setFirstname(String firstname) {
        CurrentUser.firstname = firstname;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        CurrentUser.password = password;
    }

    public static int getDestinationId() {
        return destinationId;
    }

    public static void setDestinationId(int destinationId) {
        CurrentUser.destinationId = destinationId;
    }

    public static int getUsertypeId() {
        return usertypeId;
    }

    public static void setUsertypeId(int usertypeId) {
        CurrentUser.usertypeId = usertypeId;
    }

    public static int getQuizpoints() {
        return quizpoints;
    }

    public static void setQuizpoints(int quizpoints) {
        CurrentUser.quizpoints = quizpoints;
    }

    public static boolean isChooseDest() {
        return chooseDest;
    }

    public static void setChooseDest(boolean chooseDest) {
        CurrentUser.chooseDest = chooseDest;
    }

    public static void resetCurrentUser(){
        setId(-1l);
        setUsername("");
        setLastname("");
        setFirstname("");
        setPassword("");
        setQuizpoints(0);
        setDestinationId(0);
        setUsertypeId(0);
    }
    public static User getUser(){
        User u = new User();
        u.setId(id);
        u.setUsername(username);
        u.setFirstname(firstname);
        u.setLastname(lastname);
        u.setDestinationId(destinationId);
        u.setPassword(password);
        u.setQuizpoints(quizpoints);
        u.setUsertypeId(usertypeId);
        return u;
    }
}
