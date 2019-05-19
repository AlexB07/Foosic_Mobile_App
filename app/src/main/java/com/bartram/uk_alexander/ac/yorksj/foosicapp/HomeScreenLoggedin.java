package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class HomeScreenLoggedin extends AppCompatActivity {

    private SoundbiteNavigationView nav;
    private statsSQL stats = new statsSQL();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_loggedin);
        nav = findViewById(R.id.navigation);
        statsTest();
        TextView welcome = findViewById(R.id.txtWelcome);
        welcome.setText("Welcome Back " + LoginScreen.name);



    }

    private void statsTest(){
        if (stats.getStatus() == AsyncTask.Status.PENDING || stats.getStatus() == AsyncTask.Status.FINISHED){
            stats = new statsSQL();
            stats.parent = HomeScreenLoggedin.this;
            stats.execute(Integer.toString(LoginScreen.userID));
        }
    }


    public void onStart(){
        super.onStart();
        nav.setSelectedItemId(R.id.navigation_home);
        statsTest();
    }
    public void stats(String s) {
        String[] sArray = s.split(",");
        String time = "Average active Time: " + sArray[0];
        String total = "Total Number of Meals: " + sArray[1];
        String numOfFavs = "Number of Favourites: " + sArray[2];
 //       EditText t
        TextView statsText = findViewById(R.id.statText);
        Log.d("test",time);
        statsText.setText(time + "\n" + total + "\n" + numOfFavs);




    }


}
