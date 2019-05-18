package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bartram.uk_alexander.ac.yorksj.foosicapp.guestscreens.Taste;

public class activity1 extends AppCompatActivity {


    private TextView mTextMessage;
    private ImageView imgSettings;
    private FrameLayout fragment_frame;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    Fragment fragment1 = new MainFragment();
                    moveToFragment(fragment1);
                    //mTextMessage.setText("home");
                    return true;
                case R.id.navigation_taste:
                    Fragment fragment2 = new TasteScreen();
                    moveToFragment(fragment2);
                    //mTextMessage.setText("taste");
                    return true;
                case R.id.navigation_music:
                    //mTextMessage.setText("music");
                    return true;
                case R.id.navigation_favourites:
                    //mTextMessage.setText("favourites");
                    return true;
                case R.id.navigation_food:
                    //mTextMessage.setText("food");
                    return true;
            }
            return false;
        }
    };

    public void moveToFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }

    private void moveToSettings(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.settings_frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);
        Fragment fragment = new MainFragment();
        Fragment sFragment = new Empty();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.settings_frame, sFragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
        imgSettings = (ImageView) findViewById(R.id.imgSettings);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        imgSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment6 = new SettingsFragment();
                moveToSettings(fragment6);
            }
        });
    }
}
