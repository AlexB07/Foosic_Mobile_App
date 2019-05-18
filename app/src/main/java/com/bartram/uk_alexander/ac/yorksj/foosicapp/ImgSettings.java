package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

public class ImgSettings extends AppCompatImageView {

    public ImgSettings(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settings = new Intent(getContext(), settingScreen.class);
                settings.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                getContext().startActivity(settings);
            }
        });

    }


}
