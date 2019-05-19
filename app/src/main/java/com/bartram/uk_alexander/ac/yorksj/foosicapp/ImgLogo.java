package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

import java.util.jar.Attributes;

public class ImgLogo extends AppCompatImageView {

    private Intent home;

    public ImgLogo(Context context, AttributeSet s) {
        super(context, s);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginScreen.userID > -1){
                        home = new Intent(getContext(), HomeScreenLoggedin.class);
                }else {
                     home = new Intent(getContext(), homeScreenGuest.class);
                }
                home.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                getContext().startActivity(home);
            }
        });
    }

}
