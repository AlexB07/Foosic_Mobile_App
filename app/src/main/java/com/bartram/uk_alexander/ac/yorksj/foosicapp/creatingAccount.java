package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class creatingAccount extends AppCompatActivity {


    private ImageView imgSettings;
    private Button btnSubmit;
    private Button btnCancel;
    private Spinner spAge;
    private TextInputLayout txtInputName;
    private TextInputLayout txtInputEmail;
    private TextInputLayout getTxtInputPassword;

    private creatingAccountSQL account = new creatingAccountSQL();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating_account);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);

        txtInputName = findViewById(R.id.txtInputName);
        txtInputEmail = findViewById(R.id.txtInputEmail);
        getTxtInputPassword = findViewById(R.id.txtInputPassword);

        spAge = findViewById(R.id.spinnerAge);
        String[] spinnerArray = {"16","17","18","19","20","21","22","23","24","24","25","26","27","28","29","30"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
        spAge.setAdapter(adapter);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (account.getStatus() == AsyncTask.Status.PENDING || account.getStatus() == AsyncTask.Status.FINISHED) {
                    String name = txtInputName.getEditText().getText().toString();
                    String email = txtInputEmail.getEditText().getText().toString();
                    String password = getTxtInputPassword.getEditText().getText().toString();
                    String age = spAge.getSelectedItem().toString();
                    account = new creatingAccountSQL();
                    account.parent = creatingAccount.this;

                    account.execute(name, email,age, password);

                }


            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginScreen.class);
                i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
            }
        });

    }

    public void accountCreationResult(String s) {
        if (s.contains("created")){
            Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();
            Intent login = new Intent(creatingAccount.this, LoginScreen.class);
            login.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(login);
        }
    }
}
