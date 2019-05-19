package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginScreen extends AppCompatActivity {


    private Button btnSubmit;
    private TextInputLayout txtInputName, txtInputPassword;
    private EditText txtEditName, txtEditPassword;
    private TextView txtClickHere;
    private loginSQL sqlLogin;
    private String result = "";

    static int userID = -1;
    static String name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        sqlLogin = new loginSQL();
        initialiseWidgets();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    if (sqlLogin.getStatus() == AsyncTask.Status.PENDING || sqlLogin.getStatus() == AsyncTask.Status.FINISHED) {
                        sqlLogin = new loginSQL();
                        sqlLogin.parent = LoginScreen.this;
                        String username = txtEditName.getText().toString();
                        String password = txtEditPassword.getText().toString();

                        sqlLogin.execute(username, password);

                        Toast.makeText(getApplicationContext(), "Checking...", Toast.LENGTH_SHORT).show();
                    }
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
        reset();

    }


    public void loginSuccessful(String s) {
        String temp;
        String[] sArray = s.split(",");
        try {
            temp = sArray[0].replaceAll("\\s+", "");
            if (Integer.parseInt(temp) > 0) {
                result = sArray[0];
                name = sArray[1];
            }
        } catch (NumberFormatException e) {
            result = "";
            e.printStackTrace();

        }
        if (!result.equals("")) {
            Toast.makeText(getApplicationContext(), "User signed", Toast.LENGTH_SHORT).show();
            userID = Integer.parseInt(result.trim());
            reset();
        } else {
            Toast.makeText(getApplicationContext(), "User denied", Toast.LENGTH_SHORT).show();
        }

    }

    public void initialiseWidgets() {

        //Text Input Layouts
        txtInputName = findViewById(R.id.txtInputUsername);
        txtInputPassword = findViewById(R.id.txtInputPasswrd);

        //Text Edits
        txtEditName = findViewById(R.id.txtEditUsername);
        txtEditPassword = findViewById(R.id.txtEditPasswrd);

        //text view
        txtClickHere = findViewById(R.id.txtCreateAccount);

        //buttons
        btnSubmit = findViewById(R.id.btnSubmit);


    }

    public void reset() {
        txtEditName.setText(R.string.blank);
        txtEditName.setHint(R.string.hintEmail);
        txtInputName.setError(getString(R.string.blank));
        txtEditPassword.setText(R.string.blank);
        txtEditPassword.setHint(R.string.hintPassword);
        txtInputPassword.setError(getString(R.string.blank));


    }

    public boolean isValid() {
        Boolean result = true;
        //Email Validation
        String emailPattern = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(txtEditName.getText().toString());

        //reset errors
        txtInputName.setError(getString(R.string.blank));
        txtInputPassword.setError(getString(R.string.blank));

        if (txtEditName.getText().toString().isEmpty()) {
            txtInputName.setError(getString(R.string.emailError));
            result = false;
        } else if (!matcher.matches()) {
            txtInputName.setError(getString(R.string.emailPattern));
            result = false;
        }
        if (txtEditPassword.getText().toString().isEmpty()) {
            txtInputPassword.setError(getString(R.string.passwordEmpty));
            result = false;
        }

        return result;
    }
}
