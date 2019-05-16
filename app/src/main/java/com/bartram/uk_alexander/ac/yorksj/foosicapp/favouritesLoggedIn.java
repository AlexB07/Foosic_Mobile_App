package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class favouritesLoggedIn extends AppCompatActivity {
    private ImageView imgLogo;
    private SoundbiteNavigationView nav;
    private appdatastorage data = new appdatastorage();

    protected void onCreate(Bundle savedInstatnceState){
        super.onCreate(savedInstatnceState);
        if (data.getUserID() == "") {
            Intent ig = new Intent(nav.getContext(), favouritesGuest.class);
            ig.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(ig);
        }
        else{
            Intent il = new Intent(nav.getContext(), favouritesLoggedIn.class);
            il.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(il);

            imgLogo = (ImageView) findViewById(R.id.imgLogoTopRight);
            nav = findViewById(R.id.navigation);

            imgLogo.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), homeScreenGuest.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(i);
                }
            }));
        }
    }
    public void onStart(){
        super.onStart();
        nav.setSelectedItemId(R.id.navigation_favourites);
    }

}
