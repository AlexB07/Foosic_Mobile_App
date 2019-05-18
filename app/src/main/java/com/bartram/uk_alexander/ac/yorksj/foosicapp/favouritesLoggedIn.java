package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    private favouritesSQL favouritesSQL;
    public ListView listView;

    protected void onCreate(Bundle savedInstatnceState){
        super.onCreate(savedInstatnceState);
        setContentView(R.layout.activity_favourites_loggedin);
        favouritesSQL = new favouritesSQL();
        favouritesSQL.parent = favouritesLoggedIn.this;
        favouritesSQL.execute(LoginScreen.data.getUser());

        imgLogo = (ImageView) findViewById(R.id.imgLogoTopRight);
        nav = findViewById(R.id.navigation);

        Intent il = new Intent(nav.getContext(), favouritesLoggedIn.class);
        il.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(il);

        imgLogo.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), homeScreenGuest.class);
                i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
            }
        }));
    }
    public void onStart(){
        super.onStart();
        nav.setSelectedItemId(R.id.navigation_favourites);
    }
    public void faves(String s) {
        HashMap<String, String> favOut = new HashMap<String,String>();
        HashMap<String, String> nameBlank = new HashMap<String,String>();
        String[] sArray = s.split(",");

        listView = (ListView) findViewById(R.id.list);
        for(int i=0;i<sArray.length;i+=2){
            favOut.put(sArray[i],sArray[i+1]);
            nameBlank.put(sArray[i],"");
        }
        int j = sArray.length/2;
        if (j%2 != 0){
            j =+1;
        }
        int e = 0;
        String[] namesArray = new String[j];
        Map.Entry<String,String> map = favOut.entrySet().iterator().next();
        Iterator<Map.Entry<String, String>> entryIterator = favOut.entrySet().iterator();
        while(entryIterator.hasNext()){
            Map.Entry<String,String> entry = entryIterator.next();
            namesArray[e] = entry.getKey();
            e+=1;
        }


        /*for (int d = 0;d <j; d++){
            if(
            = favOut.entrySet().iterator().next();
            namesArray[d] = map.getKey();
        }*/


        List<String> list = Arrays.asList(namesArray);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(),android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

    }

}
