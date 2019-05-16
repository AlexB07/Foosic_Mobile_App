package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {

    private ImageView imgLogo;
    private Button btnSubmit;
    private TextInputLayout tilUsername;
    private TextInputLayout tilPassword;
    private loginSQL sqlLogin = new loginSQL();
    private appdatastorage data = new appdatastorage();
    private String result = "";
    private SoundbiteNavigationView nav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        nav = findViewById(R.id.navigation);

        imgLogo = (ImageView) findViewById(R.id.imgLogoTopRight);
     tilUsername = (TextInputLayout) findViewById(R.id.textInputLayout);
     tilPassword = (TextInputLayout) findViewById(R.id.txtInputPassword);
      tilUsername.setHint("Email Address");
       tilPassword.setHint("Password");


        sqlLogin.parent = this;

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), homeScreenGuest.class);
                i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String username = tilUsername.getEditText().getText().toString();
               String password = tilPassword.getEditText().getText().toString();

               sqlLogin.execute(username, password);

               data.setUserID(username);

               if (!result.equals("")){
                   Toast.makeText(getApplicationContext(), "User signed", Toast.LENGTH_SHORT).show();
               }else {
                    Toast.makeText(getApplicationContext(), "User denied", Toast.LENGTH_SHORT).show();
               }
            }
        });






    }

    public void onStart(){
        super.onStart();

    }



    public void loginSuccessful(String s){
        try {
            if (Integer.parseInt(s) > 0) {
                result = s;
            }
        }catch (NumberFormatException e){
            result = "";
            e.printStackTrace();

        }
    }
}
