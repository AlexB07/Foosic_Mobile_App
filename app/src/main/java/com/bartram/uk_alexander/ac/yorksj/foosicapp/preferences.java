package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class preferences extends AppCompatActivity {
    private Switch SwitchA;
    private Switch SwitchB;
    private TextView title;
    private ImageView closeSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        Typeface customFont=Typeface.createFromAsset(getAssets(),"fonts/FuturaBoldItalicFont.ttf");
        SwitchA = findViewById(R.id.switch1);
        SwitchB = findViewById(R.id.switch2);
        title = findViewById(R.id.prefTitle);
        SwitchA.setTypeface(customFont);
        SwitchB.setTypeface(customFont);
        title.setTypeface(customFont);
        closeSettings = findViewById(R.id.settingsClose);
        closeSettings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                preferences.this.finish();
            }
        });


    }

    public void onStart() {
        super.onStart();
    }

}
