package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ImgSettings extends AppCompatImageView {

    private TextView test;

    public ImgSettings(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        ImageView imgSettings = (ImageView) findViewById(R.id.imgSettings);

        imgSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settings = new Intent(getContext(), homeScreenGuest.class);
                settings.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                getContext().startActivity(settings);
            }
        });



    }


}
