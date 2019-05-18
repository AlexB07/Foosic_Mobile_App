package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class TasteScreen extends Fragment {
    private Button btnReset;
    private Button btnSubmit;
    private Spinner spSalty;
    private Spinner spBitter;
    private SeekBar sbSweetSour;
    private findSong song;
    public int sweetSour;
    private Context mContext;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mContext = context;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        String[] spinnerArray = new String[]{"0", "1", "2", "3", "4", "5"};
        spSalty = (Spinner) view.findViewById(R.id.spinnerSalty);
        spBitter = (Spinner) view.findViewById(R.id.spinnerBitter);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, spinnerArray);
        spSalty.setAdapter(adapter);
        spBitter.setAdapter(adapter);

        //Initialise seek-bar
        sbSweetSour = view.findViewById(R.id.seekBar);
        sbSweetSour.setMax(40);

        resetValues();

        song = new findSong();



        btnReset = (Button) view.findViewById(R.id.btnReset);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetValues();

            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (song.getStatus() == AsyncTask.Status.PENDING || song.getStatus() == AsyncTask.Status.FINISHED) {
                    song = new findSong();
                    song.parent = TasteScreen.this;


                    String sweet = "0", sour = "0", salty = "0", bitter = "0";
                    calculateSweetSour();
                    Toast.makeText(getContext(), ("Finding Song..."), Toast.LENGTH_SHORT).show();

                    //Setting values
                    if (sweetSour == 3) {
                        sweet = "1";
                        sour = "1";
                    } else if (sweetSour > 3) {
                        sour = "1";
                    } else {
                        sweet = "1";
                    }

                    salty = spSalty.getSelectedItem().toString();
                    bitter = spBitter.getSelectedItem().toString();

                    song.execute(sweet, sour, salty, bitter);
                }

            }


        });

        return view;
    }
    public void resetValues() {
        spBitter.setSelection(0);
        spSalty.setSelection(0);
        sbSweetSour.setProgress(20);

    }

    public void calculateSweetSour() {
        sweetSour = 0;
        sweetSour = ((sbSweetSour.getProgress()));
        sweetSour = (sweetSour / 10) + 1;
    }

    public void testingSongOutput(String s) {
        String songID = "";
        String song = "";

        try {
            String[] results = s.split("!");

            songID = results[0];
            song = results[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            Toast.makeText(getContext(), "APP [WIP]", Toast.LENGTH_SHORT).show();
        }


        if (!songID.isEmpty()) {
            Toast.makeText(getContext(), "Completed song ID" + songID, Toast.LENGTH_SHORT).show();
        }
    }


}
