package com.bartram.uk_alexander.ac.yorksj.foosicapp;

public class appdatastorage {
    public Boolean logIn = false;
    public String user = "";

    public Boolean getLogInStatus() {

        return logIn;
    }

    public void setLoggedIn(Boolean b){

        logIn = b;
    }
    public String getUser(){

        return user;
    }
    public void setUser(String s) {

        user = s;
    }
}
