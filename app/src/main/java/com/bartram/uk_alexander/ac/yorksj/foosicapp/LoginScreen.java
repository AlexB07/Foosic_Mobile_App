package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {


    private Button btnSubmit;
    private TextInputLayout tilUsername;
    private TextInputLayout tilPassword;
    private TextView txtClickHere;
    private loginSQL sqlLogin;
    private String result = "";
    private SoundbiteNavigationView nav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        sqlLogin = new loginSQL();
        nav = findViewById(R.id.navigation);

        tilUsername = (TextInputLayout) findViewById(R.id.txtInputName);
        tilPassword = (TextInputLayout) findViewById(R.id.txtInputPassword);
        txtClickHere = (TextView) findViewById(R.id.txtCreateAccount);
        tilUsername.setHint("Email Address");
        tilPassword.setHint("Password");
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sqlLogin.getStatus() == AsyncTask.Status.PENDING || sqlLogin.getStatus() == AsyncTask.Status.FINISHED) {
                    sqlLogin = new loginSQL();
                    sqlLogin.parent = LoginScreen.this;
                    String username = tilUsername.getEditText().getText().toString();
                    String password = tilPassword.getEditText().getText().toString();

                    sqlLogin.execute(username, password);

                    Toast.makeText(getApplicationContext(), "Checking...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtClickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent account = new Intent(LoginScreen.this, creatingAccount.class);
                account.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(account);
            }
        });


    }

    public void onStart() {
        super.onStart();

    }


    public void loginSuccessful(String s) {
        String temp = "";
        try {
            temp = s.replaceAll("\\s+", "");
            if (Integer.parseInt(temp) > 0) {
                result = s;
            }
        } catch (NumberFormatException e) {
            result = "";
            e.printStackTrace();

        }
        if (!result.equals("")) {
            Toast.makeText(getApplicationContext(), "User signed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "User denied", Toast.LENGTH_SHORT).show();
        }


    }
}
