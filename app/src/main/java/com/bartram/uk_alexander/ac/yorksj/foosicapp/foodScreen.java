package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class foodScreen extends AppCompatActivity {


    private SoundbiteNavigationView nav;

    private Intent music;

    private ExpandableListView expandableListView;

    private ExpandableListViewAdpter explistAdapter;
    private List<String> listViewGroup;
    private HashMap<String, String[][]> listViewChid;
    private findSongWIthID song = new findSongWIthID();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_screen);
        nav = findViewById(R.id.navigation);


        initViews();
        initListeners();
        initObjects();
        initListData();


    }
    private void initViews() {

        expandableListView = findViewById(R.id.expList);

    }

    /**
     * method to initialize the listeners
     */
    private void initListeners() {

        // ExpandableListView on child click listener
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        listViewGroup.get(groupPosition)
                                + " : "
                                + listViewChid.get(
                                listViewGroup.get(groupPosition))[childPosition][0], Toast.LENGTH_SHORT)
                        .show();
                musicPlayer.mediaPlayer.pause();
                if (song.getStatus() == AsyncTask.Status.PENDING || song.getStatus() == AsyncTask.Status.FINISHED) {
                    song = new findSongWIthID();
                    song.parent = foodScreen.this;
                    Toast.makeText(foodScreen.this, "Finding Song...", Toast.LENGTH_SHORT).show();
                    String tempInput = listViewChid.get(listViewGroup.get(groupPosition))[childPosition][1].toString();

                    song.execute(tempInput);
                }

                return false;
            }
        });

        // ExpandableListView Group expanded listener
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listViewGroup.get(groupPosition) + " " + getString(R.string.text_exp),
                        Toast.LENGTH_SHORT).show();
            }
        });

        // ExpandableListView Group collapsed listener
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listViewGroup.get(groupPosition) + " " + getString(R.string.text_col),
                        Toast.LENGTH_SHORT).show();

            }
        });

    }

    /**
     * method to initialize the objects
     */
    private void initObjects() {

        // initializing the list of groups
        listViewGroup = new ArrayList<>();

        // initializing the list of child
        listViewChid = new HashMap<>();

        // initializing the adapter object
        explistAdapter = new ExpandableListViewAdpter(this, listViewGroup, listViewChid);

        // setting list adapter
        expandableListView.setAdapter(explistAdapter);

    }


    private void initListData() {


        // Adding group data
        listViewGroup.add(getString(R.string.breakfast_name));
        listViewGroup.add(getString(R.string.lunch_name));
        listViewGroup.add(getString(R.string.dinner_name));

        // array of strings
        String[] array;

        // list for Breakfast
        String[][] breakfastList = new String[3][2];
        //Intitalising the breakfast list
        breakfastList[0][0] = "Cereal";
        breakfastList[0][1] = "6";

        breakfastList[1][0] = "Toast";
        breakfastList[1][1] = "4";

        breakfastList[2][0] = "Fruit";
        breakfastList[2][1] = "1";

        // list for Lunch
        String[][] lunchList = new String[3][2];
        //Intitalising the breakfast list
        lunchList[0][0] = "Pasta";
        lunchList[0][1] = "2";

        lunchList[1][0] = "Sandwiches";
        lunchList[1][1] = "3";

        lunchList[2][0] = "Salad";
        lunchList[2][1] = "0";

        // list for Dinner
        String[][] dinnerList = new String[3][2];
        //Intitalising the breakfast list
        dinnerList[0][0] = "Pizza";
        dinnerList[0][1] = "6";

        dinnerList[1][0] = "Curry";
        dinnerList[1][1] = "3";

        dinnerList[2][0] = "Chicken";
        dinnerList[2][1] = "5";


        // Adding child data


        listViewChid.put(listViewGroup.get(0), breakfastList);
        listViewChid.put(listViewGroup.get(1), lunchList);
        listViewChid.put(listViewGroup.get(2), dinnerList);


        // notify the adapter
        explistAdapter.notifyDataSetChanged();
    }

    public void onStart() {
        super.onStart();
        nav.setSelectedItemId(R.id.navigation_food);
    }

    public void results(byte[] b) {
        byte[] song = b;

        music = new Intent(this, musicPlayer.class);
        music.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        music.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        music.putExtra("songFile", song);
        music.putExtra("fav", "1");
        startActivity(music);
        music.putExtra("songFile", 0);


    }


}

