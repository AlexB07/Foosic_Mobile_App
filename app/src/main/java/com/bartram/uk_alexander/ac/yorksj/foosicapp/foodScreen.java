package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


    private ImageView imgLogo;
    private ImageView imgSettings;

    private ExpandableListView expandableListView;

    private ExpandableListViewAdpter explistAdapter;
    private List<String> listViewGroup;
    private HashMap<String, List<String>> listViewChid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_screen);


        initViews();
        initListeners();
        initObjects();
        initListData();

        imgLogo = findViewById(R.id.imgLogoTopRight);
        imgSettings = findViewById(R.id.imgSettings);

        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), homeScreenGuest.class);
                i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
            }
        });

        imgSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO code settings menu
            }
        });
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
                                listViewGroup.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
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
            List<String> breakfastList = new ArrayList<>();
            array = getResources().getStringArray(R.array.string_array_breakfast);
            for (String item : array) {
                breakfastList.add(item);
            }

            // list for Lunch
            List<String> lunchList = new ArrayList<>();
            array = getResources().getStringArray(R.array.string_array_lunch);
            for (String item : array) {
                lunchList.add(item);
            }

            // list for Dinner
            List<String> dinnerList = new ArrayList<>();
            array = getResources().getStringArray(R.array.string_array_dinner);
            for (String item : array) {
                dinnerList.add(item);
            }



            // Adding child data


            listViewChid.put(listViewGroup.get(0), breakfastList);
            listViewChid.put(listViewGroup.get(1), lunchList);
            listViewChid.put(listViewGroup.get(2), dinnerList);


            // notify the adapter
            explistAdapter.notifyDataSetChanged();
        }



    }

