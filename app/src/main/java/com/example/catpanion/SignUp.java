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

public class SignUp extends AppCompatActivity {

    EditText emailET, passET;
    Button signupBTN;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailET = (EditText) findViewById(R.id.editTextEmail);
        passET = (EditText) findViewById(R.id.editTextPassword);
        signupBTN = (Button) findViewById(R.id.signUpBTN);
        progressBar = (ProgressBar) findViewById(R.id.progressbar_signup);
        mAuth = FirebaseAuth.getInstance();

    }

    //  GO TO LOGIN ACTIVITY
    public void toLogin(View v){
        Intent i = new Intent(SignUp.this, SignUpDetails.class);
        startActivity(i);
    }

    //  CHECK USER INPUT
    public void toSignupDetails(View view) {
        String email = emailET.getText().toString();
        String pass = passET.getText().toString();

        if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(pass)){
            progressBar.setVisibility(View.VISIBLE);

            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        toSignupInfo();
                        progressBar.setVisibility(View.INVISIBLE);
                    } else {
                        progressBar.setVisibility(View.INVISIBLE);
                        String error = task.getException().getMessage();
                        Toast.makeText(SignUp.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(SignUp.this, "Please enter your email and password.", Toast.LENGTH_SHORT).show();
        }
    }

    //  GO TO SIGNUP DETAILS ACTIVITY [MIGHT CHANGE LATER !!!]
    private void toSignupInfo() {
        Intent i = new Intent(SignUp.this, SignUpDetails.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            toSignupInfo();
        }
    }
}