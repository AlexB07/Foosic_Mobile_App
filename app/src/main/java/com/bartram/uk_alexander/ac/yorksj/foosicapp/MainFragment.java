package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainFragment extends Fragment{

   private activity1 test;

    @Nullable
    private Button button;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        button = (Button) view.findViewById(R.id.btnLogin);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"works", Toast.LENGTH_SHORT);
                Log.d("TESTING", "  test");
                Fragment frag = new LoginScreen();
                getFragmentManager().beginTransaction().replace(R.id.fragment_frame, frag).addToBackStack(null).commit();
            }
        });
        return view;


    }
/*
    public void onClickBtnLogin(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
               /* Fragment fragment1 = new LoginScreen();
                activity1 test = new activity1();
                test.moveToFragment(fragment1);

                break;
        }
    }*/

}
