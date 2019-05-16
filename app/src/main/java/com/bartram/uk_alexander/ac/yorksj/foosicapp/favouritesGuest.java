package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class favouritesGuest extends AppCompatActivity {
    private ImageView imgLogo;
    private Button btnLogin;
    private SoundbiteNavigationView nav;

    protected void onCreate(Bundle savedInstatnceState){
        super.onCreate(savedInstatnceState);

        setContentView(R.layout.activity_favourites_guest);

        imgLogo = (ImageView) findViewById(R.id.imgLogoTopRight);
        btnLogin = (Button) findViewById(R.id.btnFavLogin);
        nav = findViewById(R.id.navigation);

        imgLogo.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), homeScreenGuest.class);
                i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
            }
        }));
        btnLogin.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), LoginScreen.class);
                i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
            }
        }));
    }
    public void onStart(){
        super.onStart();
        nav.setSelectedItemId(R.id.navigation_favourites);
    }

}
