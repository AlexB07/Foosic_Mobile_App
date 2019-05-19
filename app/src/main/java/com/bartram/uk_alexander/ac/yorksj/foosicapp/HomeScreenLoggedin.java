package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeScreenLoggedin extends AppCompatActivity {

    private SoundbiteNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_loggedin);
        nav = findViewById(R.id.navigation);
    }


    public void onStart(){
        super.onStart();
        nav.setSelectedItemId(R.id.navigation_home);
    }


}
