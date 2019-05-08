package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bartram.uk_alexander.ac.yorksj.foosicapp.guestscreens.Taste;

public class activity1 extends AppCompatActivity {

    private TextView mTextMessage;
    private ImageView imgSettings;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText("home");
                    return true;
                case R.id.navigation_taste:
                    //mTextMessage.setText("taste");

                    return true;
                case R.id.navigation_music:
                    mTextMessage.setText("music");
                    return true;
                case R.id.navigation_favourites:
                    mTextMessage.setText("favourites");
                    return true;
                case R.id.navigation_food:
                    mTextMessage.setText("food");
                    return true;
            }
            return false;
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);
        imgSettings = (ImageView) findViewById(R.id.imgSettings);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        imgSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
    }
}
