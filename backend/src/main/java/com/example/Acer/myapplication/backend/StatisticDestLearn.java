package com.example.Acer.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Acer on 16.12.2016.
 */
@Entity
public class StatisticDestLearn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Index
    private int statCounter;
    @Index
    private int idDestination;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStatCounter() {
        return statCounter;
    }

    public void setStatCounter(int statCounter) {
        this.statCounter = statCounter;
    }

    public int getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(int idDestination) {
        this.idDestination = idDestination;
    }

}
