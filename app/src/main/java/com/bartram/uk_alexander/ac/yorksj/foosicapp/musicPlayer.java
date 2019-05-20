package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class musicPlayer extends AppCompatActivity {
    private MediaPlayer mp;
    private ImageView imgPlay;
    private ImageView imgFav;
    private SoundbiteNavigationView nav;

    private boolean playBtn = false;
    private boolean favBtn = false;

    private String songID;
    private final Context context = this;
    private byte[] song;
    private File tempMp3;
    private String result;

    private addFavSQL addFav = new addFavSQL();

    static MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        imgPlay = findViewById(R.id.imgPlay);
        nav = findViewById(R.id.navigation);
        imgFav = findViewById(R.id.imgFavourites);
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFav();
                if (!mediaPlayer.isPlaying()) {
                    imgPlay.setImageResource(R.drawable.ic_pause_btn);
                    mediaPlayer.start();
                    playBtn = true;
                } else {
                    imgPlay.setImageResource(R.drawable.ic_play);
                    mediaPlayer.pause();
                    playBtn = false;
                }
            }


        });

        imgFav.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompts, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // edit text
                                        result = userInput.getText().toString();
                                        if (addFav.getStatus() == AsyncTask.Status.PENDING || addFav.getStatus() == AsyncTask.Status.FINISHED) {
                                            addFav = new addFavSQL();
                                            addFav.parent = musicPlayer.this;
                                            songID = getIntent().getStringExtra("song_ID");
                                            String userID = Integer.toString(LoginScreen.userID);

                                            addFav.execute(result, songID, userID);
                                        }


                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                if (getIntent().getByteArrayExtra("songFile") != null) {
                    if (!favBtn && LoginScreen.userID > -1) {
                        alertDialog.show();
                    }
                }
                if (favBtn){
                    Toast.makeText(context, "This song already exists on the app", Toast.LENGTH_SHORT).show();
                }else
                if (LoginScreen.userID == -1) {
                    Toast.makeText(context, "Sign in to use this feature", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "select music using taste screen", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void onStart() {
        super.onStart();
        nav.setSelectedItemId(R.id.navigation_music);
        song = new byte[50];
        song = getIntent().getByteArrayExtra("songFile");
        changeFav();
        if (!mediaPlayer.isPlaying() && (song != null)) {
            convertByteToMP3();
        }

    }

    public void changeFav() {
        if (getIntent().getStringExtra("fav") != null) {
            String test = getIntent().getStringExtra("fav");
            if (test.equals("1")) {
                favBtn = true;
                imgFav.setImageResource(R.drawable.ic_fav_star);
            } else {
                favBtn = false;
            }
        }
    }

    public void convertByteToMP3() {
        try {
            tempMp3 = File.createTempFile("test", "mp3", getCacheDir());
            tempMp3.deleteOnExit();
            FileOutputStream fos = new FileOutputStream(tempMp3);
            fos.write(song);
            fos.close();
            mediaPlayer.reset();
            FileInputStream fis = new FileInputStream(tempMp3);
            mediaPlayer.setDataSource(fis.getFD());
            mediaPlayer.prepare();
            mediaPlayer.start();
            imgPlay.setImageResource(R.drawable.ic_pause_btn);
            mediaPlayer.setLooping(true);
        } catch (IOException e) {
            Log.e("error", e.toString());
        }

    }

    public void addFavResults(String s) {
        if (s.contains("created")) {
            imgFav.setImageResource(R.drawable.ic_fav_star);
            favBtn = true;
            Toast.makeText(context, "Song saved as " + result, Toast.LENGTH_SHORT).show();
        }
    }

    private void stopPlaying() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
