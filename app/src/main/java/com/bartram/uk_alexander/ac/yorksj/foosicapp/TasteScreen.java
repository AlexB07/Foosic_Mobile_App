package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    private Intent music;

    private Byte[] test;

    private SoundbiteNavigationView nav;

    private String songID;


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

                    if (Integer.parseInt(spSalty.getSelectedItem().toString()) > 0){
                        salty = "1";
                    }else {
                        salty = "0";
                    }

                    if (Integer.parseInt(spBitter.getSelectedItem().toString()) > 0){
                        bitter = "1";
                    }else {
                        bitter = "0";
                    }


                    song.execute(sweet, sour, salty, bitter);

                }

            }
        });


    }

    public void onStart(){
        super.onStart();
        musicPlayer.mediaPlayer.pause();
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

    public void testingSongOutput(byte[] s) {
        String songID = "";
        byte[] song = new byte[s.length];
        song = s;

        try {


        } catch (ArrayIndexOutOfBoundsException e) {
            Toast.makeText(getApplicationContext(), "APP [WIP]", Toast.LENGTH_SHORT).show();
        }


        if (!songID.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Completed song ID" + songID, Toast.LENGTH_SHORT).show();
        }

        music = new Intent(TasteScreen.this, musicPlayer.class);
        music.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        music.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //music.putExtra("song_ID",songID);
        Log.e("mp3", "putting " + song.length);
        music.putExtra("songFile",song);
        startActivity(music);
        music.putExtra("songFile",0);


        overridePendingTransition(0,0);

    }

    public void getSongID(String s){
        songID = s;
    }


}
