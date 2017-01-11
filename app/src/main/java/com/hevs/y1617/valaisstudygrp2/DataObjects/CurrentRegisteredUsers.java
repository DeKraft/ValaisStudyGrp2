package com.hevs.y1617.valaisstudygrp2.DataObjects;

import com.example.acer.myapplication.backend.userApi.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 18.11.2016.
 */

public class CurrentRegisteredUsers {

    private static List<User> userList = new ArrayList<User>();

    public static List<User> getUserList() {
        return userList;
    }

    public static void setUserList(List<User> userList) {
        CurrentRegisteredUsers.userList = userList;
    }
}
