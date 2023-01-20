package com.example.catpanion;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    // INPUT OF USER
    static String userName;
    static String userPhone;
    static String userAddr;
    SharedPreferences sp;

    All_Users member;

    ActivityResultLauncher<String> mTakePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_details);

        databaseReference = firebaseDatabase.getReference();
        sp = getSharedPreferences("MyUSerPrefs", Context.MODE_PRIVATE);

        etname = (EditText) findViewById(R.id.editTextName);
        etaddr = (EditText) findViewById(R.id.editTextAddress);
        etphone = (EditText) findViewById(R.id.editTextPhone);
        saveToRealtimeDatabasse = (Button) findViewById(R.id.create_profileBTN);
        imguser = (ImageView) findViewById(R.id.user_img);
        progressBar = (ProgressBar) findViewById(R.id.progressbar_signupDetails);

        saveToRealtimeDatabasse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName = etname.getText().toString();
                String getPhone = etphone.getText().toString();
                String getAddr = etaddr.getText().toString();

                SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = saved_values.edit();
                editor.putString("nameOfUser", getName);
                editor.putString("phoneOfUser", getPhone);
                editor.putString("addrOfUser", getAddr);
                editor.commit();

                Toast.makeText(SignUpDetails.this, "Information saved", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(SignUpDetails.this, Home.class);
                //i.putExtra("user_name", getName);
                //i.putExtra("user_phone", getPhone);
                //i.putExtra("user_addr", getAddr);
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