package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.support.v4.app.Fragment;

public class homeScreenGuest extends AppCompatActivity {


    private ImageView imgLogo;
    private Button btnLogin;
    private Button btnTest;

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                // Fragment fragment1 = new MainFragment();
                //moveToFragment(fragment1);
                //mTextMessage.setText("home");
                return true;
            case R.id.navigation_taste:
                //Fragment fragment2 = new TasteFragment();
                //moveToFragment(fragment2);
                //mTextMessage.setText("taste");
                return true;
            case R.id.navigation_music:
                Fragment fragment3 = new musicfragment();
                moveToFragment(fragment3);
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

    private void moveToFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_guest);

        Fragment fragment = new FragmentMain();
        //Fragment sFragment = new FragmentMain();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
        //getSupportFragmentManager().beginTransaction().replace(R.id.settings_frame, sFragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
        imgLogo = (ImageView) findViewById(R.id.imgLogoTopRight);
        //btnLogin = (Button) findViewById(R.id.btnLogin);
       // btnTest = (Button) findViewById(R.id.btnTest);

        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), homeScreenGuest.class);
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

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(v.getContext(), music.class);
                a.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(a);
            }
        });


    }
}
