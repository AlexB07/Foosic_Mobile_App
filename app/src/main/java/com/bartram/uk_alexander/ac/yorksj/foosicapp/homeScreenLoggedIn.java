package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class homeScreenLoggedIn extends AppCompatActivity {
    private ImageView imgLogo;
    private Button btnLogin;
    private TextView txtWelcome;
    private appdatastorage data = new appdatastorage();
    private SoundbiteNavigationView nav;
    public homeScreenLoggedIn parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_guest);

        imgLogo = (ImageView) findViewById(R.id.imgLogoTopRight);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtWelcome = (TextView) findViewById(R.id.txtWelcome);

        //txtWelcome.setText("Welcome back " + data.getUserID());

        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), foodScreen.class);
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
