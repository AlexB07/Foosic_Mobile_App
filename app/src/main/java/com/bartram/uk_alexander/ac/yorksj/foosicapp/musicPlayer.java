package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.app.Activity;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class musicPlayer extends AppCompatActivity {
    private MediaPlayer mp;
    private ImageView imgPlay;
    private TextView txtName;
    private SoundbiteNavigationView nav;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        imgPlay = findViewById(R.id.imgPlayBtn);
        final TextView t = (TextView) findViewById(R.id.txtSongName);
        nav = findViewById(R.id.navigation);
        imgPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mp.isPlaying()){
                    mp.pause();
                } else {
                    stopPlaying();
                   // mp = MediaPlayer.create(musicPlayer.this, ) //R.raw.far); //where the error is place a sound/music
                    mp.start();
                }
            }
        });
    }

    public void onStart(){
        super.onStart();
        nav.setSelectedItemId(R.id.navigation_music);
    }

    private void stopPlaying() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
