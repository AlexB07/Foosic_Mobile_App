package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class homeScreenGuest extends AppCompatActivity {


    private ImageView imgLogo;
    private Button btnLogin;
    private SoundbiteNavigationView nav;
    public homeScreenGuest parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_guest);


        imgLogo = (ImageView) findViewById(R.id.imgLogoTopRight);
        btnLogin = (Button) findViewById(R.id.btnLogin);


        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), foodScreen.class);
                i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), LoginScreen.class);
                i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
            }
        });

        //Initalise the Nav Bar
        nav = findViewById(R.id.navigation);
    }


    public void onStart() {
        super.onStart();
        nav.setSelectedItemId(R.id.navigation_home);
    }




}
