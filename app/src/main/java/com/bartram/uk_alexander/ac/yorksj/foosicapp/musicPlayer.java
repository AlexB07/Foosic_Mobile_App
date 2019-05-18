package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private byte[] song;
    private File tempMp3;

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
            public void onClick(View v) {
                if (!favBtn) {
                    imgFav.setImageResource(R.drawable.ic_fav_star);
                    favBtn = true;
                } else {
                    imgFav.setImageResource(R.drawable.ic_unfav_star);
                    favBtn = false;
                }

            }
        });

    }


    public void onStart() {
        super.onStart();
        nav.setSelectedItemId(R.id.navigation_music);
        song = new byte[50];
        song = getIntent().getByteArrayExtra("songFile");
        if (!mediaPlayer.isPlaying() && (song != null)) {
            convertByteToMP3();
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
            mediaPlayer.setLooping(true);
        } catch (IOException e) {
            Log.e("error", e.toString());
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
