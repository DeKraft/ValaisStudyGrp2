package com.example.Acer.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Acer on 05.12.2016.
 */

@Entity
public class Remark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Index
    private String remarkText;
    @Index
    private double remarkPoints;
    @Index
    private int idDestination;
    @Index
    private int idTheme;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemarkText() {
        return remarkText;
    }

    public void setRemarkText(String remarkText) {
        this.remarkText = remarkText;
    }

    public double getRemarkPoints() {
        return remarkPoints;
    }

    public void setRemarkPoints(double remarkPoints) {
        this.remarkPoints = remarkPoints;
    }

    public int getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(int idDestination) {
        this.idDestination = idDestination;
    }

    public int getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(int idTheme) {
        this.idTheme = idTheme;
    }
}
