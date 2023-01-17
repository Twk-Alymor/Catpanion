package com.example.catpanion;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.catpanion.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class SignUpDetails extends AppCompatActivity {

    EditText etname, etaddr, etphone;
    ImageView imguser;
    Button saveToRealtimeDatabasse;
    ProgressBar progressBar;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;

    All_Users member;

    ActivityResultLauncher<String> mTakePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_details);

        databaseReference = firebaseDatabase.getReference();

        etname = (EditText) findViewById(R.id.editTextName);
        etaddr = (EditText) findViewById(R.id.editTextAddress);
        etphone = (EditText) findViewById(R.id.editTextPhone);
        saveToRealtimeDatabasse = (Button) findViewById(R.id.create_profileBTN);
        imguser = (ImageView) findViewById(R.id.user_img);
        progressBar = (ProgressBar) findViewById(R.id.progressbar_signupDetails);

        saveToRealtimeDatabasse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpDetails.this, Home.class);
                String getName = etname.getText().toString();
                String getPhone = etphone.getText().toString();
                String getAddr = etaddr.getText().toString();

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("name", getName);
                hashMap.put("phone", getPhone);
                hashMap.put("address", getAddr);

                databaseReference.child("Users")
                        .child(getName)
                        .setValue(hashMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(SignUpDetails.this, "Data added sucessfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignUpDetails.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                startActivity(i);
            }
        });
    }

    // TO WELCOME
    public void Signup_Done(View v){
        Intent i = new Intent(SignUpDetails.this, Welcome.class);
        startActivity(i);
    }
}