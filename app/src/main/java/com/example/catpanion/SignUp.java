package com.example.catpanion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    //  GO TO LOGIN ACTIVITY
    public void toLogin(View v){
        Intent i = new Intent(SignUp.this, MainActivity.class);
        startActivity(i);
    }

    //  GO TO SIGNUP DETAILS ACTIVITY
    public void toSignupDetails(View view) {
        Intent i = new Intent(SignUp.this, SignUpDetails.class);
        startActivity(i);
    }
}