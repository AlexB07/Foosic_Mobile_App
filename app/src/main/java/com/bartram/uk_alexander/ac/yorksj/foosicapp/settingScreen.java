package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class settingScreen extends AppCompatActivity {
        private Button btnStatistics, btnSavedMusic, btnLogout, btnPreferences;
        private ImageView closeSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_screen);
        initialiseWidgets();
        btnStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           /*     Intent i = new Intent(v.getContext(), Statistics.class);
                i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
*/
            }
        });

        btnSavedMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           /*     Intent i = new Intent(v.getContext(), SavedMusic.class);
                i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
*/
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginScreen.userID = -1;
                Toast.makeText(settingScreen.this, "Signed Out", Toast.LENGTH_SHORT).show();
            }
        });

        btnPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   Intent i = new Intent(v.getContext(), PreferencesPage.class);
                i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
*/
            }
        });


        closeSettings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                settingScreen.this.finish();
            }
        });

    }


    public void initialiseWidgets(){
        //buttons
        btnStatistics =  findViewById(R.id.btnStatistics);
        btnSavedMusic =  findViewById(R.id.btnSavedMusic);
        btnLogout =  findViewById(R.id.btnLogout);
        btnPreferences =  findViewById(R.id.btnPreferencecs);

        //Image View
        closeSettings = findViewById(R.id.settingsClose);

    }
}

