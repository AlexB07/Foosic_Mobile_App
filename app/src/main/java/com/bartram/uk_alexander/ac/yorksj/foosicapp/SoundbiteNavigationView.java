package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;

public class SoundbiteNavigationView extends BottomNavigationView {

    private Context c;
    private appdatastorage data = new appdatastorage();

    public SoundbiteNavigationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.setOnNavigationItemSelectedListener(mOnNavigationItemReselectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemReselectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    Intent home = new Intent(getContext(), homeScreenGuest.class);
                    home.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    //home.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    getContext().startActivity(home);
                    ((Activity) getContext()).overridePendingTransition(0,0);
                   return true;
                case R.id.navigation_taste:
                    Intent taste = new Intent(getContext(), TasteScreen.class);
                    taste.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    //taste.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                  getContext().startActivity(taste);
                    ((Activity) getContext()).overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_music:
                    Intent music = new Intent(getContext(), musicPlayer.class);
                    music.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    getContext().startActivity(music);
                    ((Activity) getContext()).overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_favourites:
                    if (data.getLogInStatus() == false) {
                        Intent fav = new Intent(getContext(), favouritesGuest.class);
                        fav.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        getContext().startActivity(fav);
                        ((Activity) getContext()).overridePendingTransition(0,0);
                    }
                    else{
                        Intent fav = new Intent(getContext(), favouritesLoggedIn.class);
                        fav.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        getContext().startActivity(fav);
                        ((Activity) getContext()).overridePendingTransition(0,0);
                    }
                    return true;
                case R.id.navigation_food:
                    Intent food = new Intent(getContext(), foodScreen.class);
                    food.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    getContext().startActivity(food);
                    ((Activity) getContext()).overridePendingTransition(0,0);
                    return true;
            }

            return false;
        }
    };

    private void updateSelection(int itemID){
        Menu menu = this.getMenu();
        MenuItem menuItem = menu.getItem(itemID);
        menuItem.setChecked(true);

    }




}
