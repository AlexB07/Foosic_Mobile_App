package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HomeScreenLoggedin extends AppCompatActivity {

    private SoundbiteNavigationView nav;
    private statsSQL stats = new statsSQL();
    private TextView statsText, welcome;
    private String time, total, numOfFavs;
    private String[] sArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_loggedin);
        nav = findViewById(R.id.navigation);
        statsTest();
        welcome = findViewById(R.id.txtWelcome);
        statsText = findViewById(R.id.txtStats);
       // welcome.setText("Welcome Back " + LoginScreen.userName);



    }

    private void statsTest(){
        if (stats.getStatus() == AsyncTask.Status.PENDING || stats.getStatus() == AsyncTask.Status.FINISHED){
            stats = new statsSQL();
            stats.parent = HomeScreenLoggedin.this;
            stats.execute(Integer.toString(LoginScreen.userID));
        }
    }

    public void reset(){
        welcome.setText("");
        statsText.setText("");
        sArray = new String[4];
        time = "";
        total = "";
        numOfFavs = "";
    }


    public void onStart(){
        super.onStart();
        nav.setSelectedItemId(R.id.navigation_home);
        statsTest();
    }
    public void stats(String s) {

        if (s.length() != 0) {
            reset();
             sArray = s.split(",");
             time = "Average active Time: " + sArray[0];
             total = "Total Number of Meals: " + sArray[1];
             numOfFavs = "Number of Favourites: " + sArray[2];
            //       EditText t
            Log.d("test", time);
            welcome.setText("Welcome Back " + LoginScreen.userName);
            statsText.setText(time + "\n" + total + "\n" + numOfFavs);
        }else {
            reset();
            welcome.setText("Welcome Back " + LoginScreen.userName);
            statsText.setText("Use the app to get Statistics \n After a week Statistics will appear");
        }



    }




}
