package com.hevs.y1617.valaisstudygrp2.DataObjects;

import java.io.Serializable;

/**
 * Created by Acer on 20.11.2016.
 */

public class Destination implements Serializable{

    private int idDestination;
    private String name_destination;

    public Destination(int idDestination, String name_destination) {
        this.idDestination = idDestination;
        this.name_destination = name_destination;
    }

    public int getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(int idDestination) {
        this.idDestination = idDestination;
    }

    public String getName_destination() {
        return name_destination;
    }

    public void setName_destination(String name_destination) {
        this.name_destination = name_destination;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "idDestination=" + idDestination +
                ", name_destination='" + name_destination + '\'' +
                '}';
    }
}
