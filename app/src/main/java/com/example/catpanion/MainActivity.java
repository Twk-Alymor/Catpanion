package com.example.catpanion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText emailET, passET;
    Button loginBTN;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailET = (EditText) findViewById(R.id.editTextEmail);
        passET = (EditText) findViewById(R.id.editTextPassword);
        loginBTN = (Button) findViewById(R.id.loginBTN);
        progressBar = (ProgressBar) findViewById(R.id.progressbar_login);
        mAuth = FirebaseAuth.getInstance();
    }

    // GO TO SIGN UP ACTIVITY
    public void toSignup(View v){
        Intent i = new Intent(MainActivity.this, SignUp.class);
        startActivity(i);
    }

    // GO TO HOME ACTIVITY
    private void toHome(){
        Intent i = new Intent(MainActivity.this, Home.class);
        startActivity(i);
        finish();
    }

    // CHECK USER INPUT
    public void userLoggedIn(View v){
        String email = emailET.getText().toString();
        String pass = passET.getText().toString();

        if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(pass)){
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        toHome();
                        progressBar.setVisibility(View.INVISIBLE);
                    } else {
                        progressBar.setVisibility(View.INVISIBLE);
                        String error = task.getException().getMessage();
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(MainActivity.this, "Please enter your email and password.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            Intent i = new Intent(MainActivity.this, Home.class);
            startActivity(i);
            finish();
        }
    }
}