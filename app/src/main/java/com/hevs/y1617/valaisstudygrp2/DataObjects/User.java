package com.hevs.y1617.valaisstudygrp2.DataObjects;

/**
 * Created by Acer on 17.11.2016.
 */

public class User {

    private String username;
    private String lastname;
    private String firstname;
    private String password;
    private int destinationId;
    private int usertypeId;
    private int quizpoints;

    public User(String username, String lastname, String firstname, String password, int destinationId, int usertypeId, int quizpoints) {
        this.username = username;
        this.lastname = lastname;
        this.firstname = firstname;
        this.password = password;
        this.destinationId = destinationId;
        this.usertypeId = usertypeId;
        this.quizpoints = quizpoints;
    }

    //For the registration
    public User(String username){
        this.username = username;
    }

    public User(){
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    public int getUsertypeId() {
        return usertypeId;
    }

    public void setUsertypeId(int usertypeId) {
        this.usertypeId = usertypeId;
    }

    public int getQuizpoints() {
        return quizpoints;
    }

    public void setQuizpoints(int quizpoints) {
        this.quizpoints = quizpoints;
    }
}
