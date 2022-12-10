package com.example.catpanion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignUpDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_details);
    }

    public void Signup_Done(View v){
        Intent i = new Intent(SignUpDetails.this, Welcome.class);
        startActivity(i);
    }
}