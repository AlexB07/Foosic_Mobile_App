package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

public class TasteScreen extends AppCompatActivity {

    private Button btnReset;
    private Button btnSubmit;
    private Spinner spSalty;
    private Spinner spBitter;
    private SeekBar sbSweetSour;
    private findSong song;
    public int sweetSour;

    private SoundbiteNavigationView nav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taste_screen);
        nav = findViewById(R.id.navigation);

        //Initialise spinners
        String[] spinnerArray = new String[]{"0", "1", "2", "3", "4", "5"};
        spSalty = (Spinner) findViewById(R.id.spinnerSalty);
        spBitter = (Spinner) findViewById(R.id.spinnerBitter);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
        spSalty.setAdapter(adapter);
        spBitter.setAdapter(adapter);

        //Initialise seek-bar
        sbSweetSour = findViewById(R.id.seekBar);
        sbSweetSour.setMax(40);

        resetValues();

        //Initialise Buttons
        btnReset = findViewById(R.id.btnReset);
        btnSubmit = findViewById(R.id.btnSubmit);
        song = new findSong();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetValues();
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (song.getStatus() == AsyncTask.Status.PENDING || song.getStatus() == AsyncTask.Status.FINISHED) {
                    song = new findSong();
                    song.parent = TasteScreen.this;


                    String sweet = "0", sour = "0", salty = "0", bitter = "0";
                    calculateSweetSour();
                    Toast.makeText(getApplicationContext(), ("Finding Song..."), Toast.LENGTH_SHORT).show();

                    //Setting values
                    if (sweetSour == 3) {
                        sweet = "1";
                        sour = "1";
                    } else if (sweetSour > 3) {
                        sour = "1";
                    } else {
                        sweet = "1";
                    }

                    salty = spSalty.getSelectedItem().toString();
                    bitter = spBitter.getSelectedItem().toString();

                    song.execute(sweet, sour, salty, bitter);
                }

            }
        });


    }

    public void onStart(){
        super.onStart();
        nav.setSelectedItemId(R.id.navigation_taste);
    }

    public void resetValues() {
        spBitter.setSelection(0);
        spSalty.setSelection(0);
        sbSweetSour.setProgress(20);

    }

    public void calculateSweetSour() {
        sweetSour = 0;
        sweetSour = ((sbSweetSour.getProgress()));
        sweetSour = (sweetSour / 10) + 1;
    }

    public void testingSongOutput(String s) {
        String songID = "";
        String song = "";

        try {
            String[] results = s.split("!");

            songID = results[0];
            song = results[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            Toast.makeText(getApplicationContext(), "APP [WIP]", Toast.LENGTH_SHORT).show();
        }


        if (!songID.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Completed song ID" + songID, Toast.LENGTH_SHORT).show();
        }
    }


}
