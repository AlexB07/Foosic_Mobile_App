package com.bartram.uk_alexander.ac.yorksj.foosicapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class LoginScreen extends Fragment {

    private Button btnSubmit;
    private TextInputLayout tilUsername;
    private TextInputLayout tilPassword;
    private loginSQL sqlLogin = new loginSQL();
    private String result = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_login_screen, container, false);

        tilUsername = view.findViewById(R.id.textInputLayout);
        tilPassword = view.findViewById(R.id.txtInputPassword);
        tilUsername.setHint("Email Address");
        tilPassword.setHint("Password");

        sqlLogin.parent = this;





            return view;
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnSubmit:
                String username = tilUsername.getEditText().getText().toString();
                String password = tilPassword.getEditText().getText().toString();

                sqlLogin.execute(username, password);

                if (!result.equals("")){
                    Toast.makeText(getActivity(), "User signed", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "User denied", Toast.LENGTH_SHORT).show();
                }

                break;

        }

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
