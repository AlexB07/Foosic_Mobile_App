package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class favouritesLoggedIn extends AppCompatActivity {
    private ImageView imgLogo;
    private SoundbiteNavigationView nav;
    private favouritesSQL favouritesSQL;

    protected void onCreate(Bundle savedInstatnceState){
        super.onCreate(savedInstatnceState);
        setContentView(R.layout.activity_favourites_loggedin);
        favouritesSQL = new favouritesSQL();
        favouritesSQL.execute(LoginScreen.data.getUser());

        imgLogo = (ImageView) findViewById(R.id.imgLogoTopRight);
        nav = findViewById(R.id.navigation);

        Intent il = new Intent(nav.getContext(), favouritesLoggedIn.class);
        il.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(il);

        imgLogo.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), homeScreenGuest.class);
                i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
            }
        }));
    }
    public void onStart(){
        super.onStart();
        nav.setSelectedItemId(R.id.navigation_favourites);
    }
    public void faves(String s) {

    }

}
