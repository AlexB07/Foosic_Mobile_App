package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class SettingsFragment extends Fragment {

    public SettingsFragment(){

    }


    TextView test;

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnStatistics:
                test.setText("boi");
                break;
            case R.id.btnSavedMusic:
                break;
            case R.id.btnLogout:
                break;
            case R.id.btnPreferencecs:
                break;
            case R.id.settingsClose:
                getActivity().onBackPressed();
                break;
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_settings, container, false);


        return view;
    }
}
