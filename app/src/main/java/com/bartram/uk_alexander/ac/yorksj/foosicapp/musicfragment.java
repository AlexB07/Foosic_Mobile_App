package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class musicfragment extends Fragment implements View.OnClickListener {
    private ImageView imgLogo;
    private Button btnSave;
    private MediaPlayer mp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        imgLogo.findViewById(R.id.imgLogoTopRight);
        btnSave.findViewById(R.id.btnSave);
        imgLogo.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        return view;
    }
       @Override
                   public void  onClick(View v) {
                   /*  switch (v.getId()) {
                        case R.id.imgLogo:

                    break;

                case R.id.btnSave:

                    break;

                case R.id.btnPlayPause:
                    if(mp.isPlaying()) {
                        mp.pause();
                    } else {
                        stopPlaying();
                        mp = MediaPlayer.create(musicfragment.this, R.raw.far); //where the error is place a sound/music
                        mp.start();
                    }
                    break;
            }*/
    }

    private void stopPlaying(){
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }








   /* protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //setContentView(R.layout.fragment_music);

        imgLogo = (ImageView) findViewById(R.id.imgLogoTopRight);
        btnSave = (Button) findViewById(R.id.btnSave);


        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(getApplicationContext(), homeScreenGuest.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                //startActivity(i);
            }
        });
        btnSave .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }*/
}
