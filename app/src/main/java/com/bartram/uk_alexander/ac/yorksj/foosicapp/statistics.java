package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class statistics extends AppCompatActivity {
    private statsExtendedSQL stats = new statsExtendedSQL();
    private TextView statsText;
    private TextView statsTitle;
    private String time, total, numOfFavs, common, totalListen;
    private String[] sArray;
    private ImageView closeSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        statsTest();
        statsText = findViewById(R.id.txtStatsExtended);
        statsTitle = findViewById(R.id.statsTitle);
        Typeface customFont=Typeface.createFromAsset(getAssets(),"fonts/FuturaBoldItalicFont.ttf");
        statsText.setTypeface(customFont);
        statsTitle.setTypeface(customFont);
        closeSettings = findViewById(R.id.settingsClose);
        closeSettings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                statistics.this.finish();
            }
        });
    }

    private void statsTest(){
        if (stats.getStatus() == AsyncTask.Status.PENDING || stats.getStatus() == AsyncTask.Status.FINISHED){
            stats = new statsExtendedSQL();
            stats.parent = statistics.this;
            stats.execute(Integer.toString(LoginScreen.userID));
        }
    }
    public void onStart(){
        super.onStart();
        statsTest();
    }
    public void reset(){
        statsText.setText("");
        sArray = new String[5];
        time = "";
        total = "";
        numOfFavs = "";
        common = "";
        totalListen = "";
    }
    public void stat(String s) {

        if (s.length() != 0) {
            reset();
            sArray = s.split(",");
            time = "Average active Time: " + sArray[0];
            total = "Total Number of Meals: " + sArray[1];
            numOfFavs = "Number of Favourites: " + sArray[2];
            common = "Most Common Food: " + sArray[3];
            totalListen = "Total Time listening to Music: " + sArray[4];


            //       EditText t
            Log.d("test", time);
            statsText.setText(time + "\n\n" + total + "\n\n" + numOfFavs + "\n\n" + common + "\n\n" + totalListen);
        }else {
            reset();
            statsText.setText("Use the app to get Statistics \n After a week Statistics will appear");
        }
    }

}
