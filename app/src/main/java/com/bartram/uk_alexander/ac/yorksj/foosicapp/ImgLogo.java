package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

import java.util.jar.Attributes;

public class ImgLogo extends AppCompatImageView {

    public ImgLogo(Context context, AttributeSet s) {
        super(context, s);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getContext(), homeScreenGuest.class);
                home.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                getContext().startActivity(home);
            }
        });
    }

}
