package com.bartram.uk_alexander.ac.yorksj.foosicapp;

public class appdatastorage {
    public String loggedInUser = "";

    public String setUserID(String user) {
        loggedInUser = user;
        return loggedInUser;
    }

    public String getUserID() {
        return loggedInUser;
    }

}
