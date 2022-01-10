package com.example.socialfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;

import com.google.android.material.textfield.TextInputLayout;

public class login extends AppCompatActivity {

    TextInputLayout email,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        email= findViewById(R.id.loginemail);
        pass= findViewById(R.id.loginpass);

        validation();


    }
    protected void validation(){

        String emai= email.getEditText().getText().toString().trim();
        String pas= pass.getEditText().getText().toString().trim();

        if(emai.isEmpty()){

            email.setError("Email cannot be empty");
        email.requestFocus();
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emai).matches()){
            email.setError("Please enter valid email");
            email.requestFocus();
        }
        if(pas.isEmpty()){
            pass.setError("password cannot be empty");
            pass.requestFocus();
        }
        if(pas.length()<6){
            pass.setError("Please enter valid password");
            pass.requestFocus();
        }

    }
}