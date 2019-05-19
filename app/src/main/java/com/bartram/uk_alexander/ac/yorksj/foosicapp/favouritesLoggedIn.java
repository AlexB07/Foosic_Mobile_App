package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class favouritesLoggedIn extends AppCompatActivity {
    private ImageView imgLogo;
    private SoundbiteNavigationView nav;
    private favouritesSQL favouritesSQL = new favouritesSQL();
    public ListView listView;
    private findSongWithIDFavSQL song = new findSongWithIDFavSQL();
    private Intent music;
    private String item;

    protected void onCreate(Bundle savedInstatnceState) {
        super.onCreate(savedInstatnceState);
        setContentView(R.layout.activity_favourites_logged_in);
        listView = findViewById(R.id.list);


        imgLogo = findViewById(R.id.imgLogoTopRight);
        nav = findViewById(R.id.navigation);
        startSQL();
        Intent fav = new Intent(nav.getContext(), favouritesLoggedIn.class);
        fav.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(fav);

        imgLogo.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), homeScreenGuest.class);
                i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
            }
        }));
    }

    public void startSQL(){

        if (favouritesSQL.getStatus() == AsyncTask.Status.PENDING || favouritesSQL.getStatus() == AsyncTask.Status.FINISHED) {
            favouritesSQL = new favouritesSQL();
            favouritesSQL.parent = favouritesLoggedIn.this;
            favouritesSQL.execute(Integer.toString(LoginScreen.userID));
        }
    }

    public void onStart() {
        super.onStart();
        nav.setSelectedItemId(R.id.navigation_favourites);
        startSQL();


    }

    public void faves(String s) {
        if (s.length() != 0) {
            final HashMap<String, String> favOut = new HashMap<String, String>();
            HashMap<String, String> nameBlank = new HashMap<String, String>();

            listView.setAdapter(null);
            if (!s.trim().isEmpty()) {
                String[] sArray = s.split(",");

                //TODO FIX ERROR WHEN LOGGING IN WITH DIFFERENT USERS
                for (int i = 0; i < sArray.length; i += 2) {
                    favOut.put(sArray[i], sArray[i + 1]);
                    nameBlank.put(sArray[i], "");
                }
                int j = sArray.length / 2;
                if (j % 2 != 0) {
                    j = +1;
                }
                int e = 0;
                final String[] namesArray = new String[j];
                Map.Entry<String, String> map = favOut.entrySet().iterator().next();
                Iterator<Map.Entry<String, String>> entryIterator = favOut.entrySet().iterator();
                while (entryIterator.hasNext()) {
                    Map.Entry<String, String> entry = entryIterator.next();
                    namesArray[e] = entry.getKey();
                    e += 1;
                }

                List<String> list = Arrays.asList(namesArray);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, list);
                listView.setAdapter(adapter);
            }
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    item = (String) listView.getItemAtPosition(position);
                    Toast.makeText(favouritesLoggedIn.this, "" + item, Toast.LENGTH_SHORT).show();
                    String songID = favOut.get(item);
                    Toast.makeText(favouritesLoggedIn.this, "" + songID, Toast.LENGTH_SHORT).show();
                    if ((song.getStatus() == AsyncTask.Status.PENDING || song.getStatus() == AsyncTask.Status.FINISHED)) {
                        song = new findSongWithIDFavSQL();
                        song.parent = favouritesLoggedIn.this;
                        song.execute(songID);


                    }
                }
            });

            listView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(favouritesLoggedIn.this, "Holding", Toast.LENGTH_SHORT).show();
                    
                    return false;
                }
            });
        }

    }

    public void result(byte[] s) {
        byte[] song = s;
        music = new Intent(this, musicPlayer.class);
        music.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        music.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        music.putExtra("songFile", song);
        startActivity(music);
        music.putExtra("songFile", 0);
        music.putExtra("songName", item);
    }


}

