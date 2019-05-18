package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.prefs.Preferences;

public class settingsScreen extends AppCompatActivity {

    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btnStatistics;
        Button btnSavedMusic;
        Button btnLogout;
        Button btnPreferences;

        btnStatistics = (Button) findViewById(R.id.btnStatistics);
        btnSavedMusic = (Button) findViewById(R.id.btnSavedMusic);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnPreferences = (Button) findViewById(R.id.btnPreferencecs);
        test = (TextView) findViewById(R.id.textView);

        btnStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Statistics.class);
                i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);

            }
        });

        btnSavedMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), SavedMusic.class);
                i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);

            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        btnPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), PreferencesPage.class);
                i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);

            }
        });

    }
}
