package com.example.socialfitness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.socialfitness.models.usermodel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    Button btnsignup;
    FirebaseAuth auth;
    TextInputEditText user, email, pass, phone;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();

        btnsignup = findViewById(R.id.btnsignup);
        user = findViewById(R.id.textinputlayout1);
        email = findViewById(R.id.textinputlayout2);
        pass = findViewById(R.id.textinputlayou3);
        phone = findViewById(R.id.textinputlayout4);
        auth = FirebaseAuth.getInstance();


        reference= FirebaseDatabase.getInstance().getReference();



        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uservalidation();
                auth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                           String uid= task.getResult().getUser().getUid();

                            usermodel model = new usermodel(user.getText().toString(),email.getText().toString(),pass.getText().toString(),phone.getText().toString());
                            reference.child("users").child(uid).setValue(model);
                            Toast.makeText(Signup.this  , "Signup Successfull", Toast.LENGTH_SHORT).show();
                            user.setText("");
                            email.setText("");
                            pass.setText("");
                            phone.setText("");
                            Intent intent= new Intent(Signup.this,verifyphone.class);
                            intent.putExtra("phoneno",phone.getText().toString());
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(Signup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }
        });
    }

    private void instances() {


    }

    private void uservalidation() {

        String username = user.getText().toString().trim();
        String emaill = email.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String phonee = phone.getText().toString().trim();

        if (username.isEmpty()) {
            user.setError("Full name is required");
            user.requestFocus();
            return;
        }
        if (emaill.isEmpty()) {
            email.setError("Email is required");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emaill).matches()) {
            email.setError("Please provide valid email ");
            email.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            pass.setError("Password is required");
            return;
        }
        if (password.length() < 6) {
            pass.setError("Password length shouldn't be less than 6 characters! ");
            pass.requestFocus();
            return;
        }
        if (phonee.isEmpty()) {
            phone.setError("PhoneNo is required!");
            phone.requestFocus();
            return;
        }
        if (phonee.length() < 10) {
            phone.setError("PhoneNo shouldn't be less than 10 digit!");
            phone.requestFocus();
            return;
        }


    }

}