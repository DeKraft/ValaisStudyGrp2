package com.example.Acer.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Acer on 17.11.2016.
 */

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Index
    private String username;
    @Index
    private String lastname;
    @Index
    private String firstname;
    @Index
    private String password;
    @Index
    private int destinationId;
    @Index
    private int usertypeId;
    @Index
    private int quizpoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
