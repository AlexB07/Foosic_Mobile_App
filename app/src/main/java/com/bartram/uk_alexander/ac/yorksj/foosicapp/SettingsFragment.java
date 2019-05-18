package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class SettingsFragment extends AppCompatActivity {



    TextView test;


    public void onCreate(Bundle savedInstanceState){

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        Button btnStatistics;
        Button btnSavedMusic;
        Button btnLogout;
        Button btnPreferences;

        btnStatistics = (Button) view.findViewById(R.id.btnStatistics);
        btnSavedMusic = (Button) view.findViewById(R.id.btnSavedMusic);
        btnLogout = (Button) view.findViewById(R.id.btnLogout);
        btnPreferences = (Button) view.findViewById(R.id.btnPreferencecs);
        test = (TextView) view.findViewById(R.id.textView);

        btnStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"works", Toast.LENGTH_SHORT);
                Log.d("TESTING", "  test");
                test.setText("statistacs");

            }
        });

        btnSavedMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"works", Toast.LENGTH_SHORT);
                Log.d("TESTING", "  test");
                test.setText("SavedMusic");

            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"works", Toast.LENGTH_SHORT);
                Log.d("TESTING", "  test");
                test.setText("Logout");

            }
        });

        btnPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"works", Toast.LENGTH_SHORT);
                Log.d("TESTING", "  test");
                test.setText("Preferences");

            }
        });
        return view;
    }
}
