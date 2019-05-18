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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class creatingAccount extends AppCompatActivity {


    private ImageView imgSettings;
    private Button btnSubmit;
    private Button btnCancel;
    private Spinner spAge;
    private TextInputLayout txtInputName, txtInputEmail, txtInputPassword;
    private EditText txtEditName, txtEditEmail, txtEditPassword;

    private creatingAccountSQL account = new creatingAccountSQL();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating_account);

        initilaiseWidgets();

        String[] spinnerArray = {"16","17","18","19","20","21","22","23","24","24","25","26","27","28","29","30"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
        spAge.setAdapter(adapter);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (account.getStatus() == AsyncTask.Status.PENDING || account.getStatus() == AsyncTask.Status.FINISHED) {

                    if (isValid()) {
                        String name = txtEditName.getText().toString();
                        String email = txtEditEmail.getText().toString();
                        String password = txtEditPassword.getText().toString();
                        String age = spAge.getSelectedItem().toString();
                        account = new creatingAccountSQL();
                        account.parent = creatingAccount.this;

                        account.execute(name, email, age, password);
                        reset();
                    }
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

    public boolean isValid(){
        boolean result = true;
        String emailPattern = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(txtEditEmail.getText().toString());

        txtInputName.setError("");
        txtInputEmail.setError("");
        txtInputPassword.setError("");
        if (txtEditName.getText().toString().isEmpty()){
            txtInputName.setError(getString(R.string.nameError));
            result = false;
        }
        if (txtEditEmail.getText().toString().isEmpty()){
            txtInputEmail.setError(getString(R.string.emailError));
            result = false;
        }else

        if (!matcher.matches()){
            txtInputEmail.setError(getString(R.string.emailPattern));
            result = false;
        }
        if (txtEditPassword.getText().toString().trim().length() < 8){
            txtInputPassword.setError(getString(R.string.passwordError));
            result = false;
        }

        return result;
    }

    public void accountCreationResult(String s) {
        if (s.contains("created")){
            Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();
            Intent login = new Intent(creatingAccount.this, LoginScreen.class);
            login.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(login);
        }
    }

    public void onStart(){
        super.onStart();
        reset();
    }

    public void initilaiseWidgets(){
        //TextInput Layouts
        txtInputName = findViewById(R.id.txtInputName);
        txtInputEmail = findViewById(R.id.txtInputEmail);
        txtInputPassword = findViewById(R.id.txtInputPassword);

        //EditTexts
        txtEditName = findViewById(R.id.txtEditName);
        txtEditName.setHint(R.string.hintName);
        txtEditEmail = findViewById(R.id.txtEditEmail);
        txtEditEmail.setHint(R.string.hintEmail);
        txtEditPassword = findViewById(R.id.txtEditPassword);
        txtEditPassword.setHint(R.string.hintPassword);

        //Buttons
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);

        //Spinners
        spAge = findViewById(R.id.spinnerAge);
    }

    public void reset(){
        txtEditName.setText("");
        txtInputName.setError("");
        txtEditEmail.setText("");
        txtInputEmail.setError("");
        txtEditPassword.setText("");
        txtInputPassword.setError("");
    }

}
