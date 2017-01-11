package com.hevs.y1617.valaisstudygrp2.DataObjects;

/**
 * Created by Acer on 21.11.2016.
 */

public class Town {

    private int idTown;
    private int fk_idDestination;
    private int no_ofs;
    private String name_town;

    public Town(int idTown, int fk_idDestination, int no_ofs, String name_town) {
        this.idTown = idTown;
        this.fk_idDestination = fk_idDestination;
        this.no_ofs = no_ofs;
        this.name_town = name_town;
    }

    public int getIdTown() {
        return idTown;
    }

    public void setIdTown(int idTown) {
        this.idTown = idTown;
    }

    public int getFk_idDestination() {
        return fk_idDestination;
    }

    public void setFk_idDestination(int fk_idDestination) {
        this.fk_idDestination = fk_idDestination;
    }

    public int getNo_ofs() {
        return no_ofs;
    }

    public void setNo_ofs(int no_ofs) {
        this.no_ofs = no_ofs;
    }

    public String getName_town() {
        return name_town;
    }

    public void setName_town(String name_town) {
        this.name_town = name_town;
    }

    @Override
    public String toString() {
        return "Town{" +
                "idTown=" + idTown +
                ", fk_idDestination=" + fk_idDestination +
                ", no_ofs=" + no_ofs +
                ", name_town='" + name_town + '\'' +
                '}';
    }
}
