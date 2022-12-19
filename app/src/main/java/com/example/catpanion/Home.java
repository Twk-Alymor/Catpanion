package com.example.catpanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Home extends AppCompatActivity {

    ImageButton heartBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        heartBTN = (ImageButton) findViewById(R.id.heartButton);

    }

    public void hearted (View v){
        if(heartBTN.getDrawable().getConstantState() ==
                getResources().getDrawable(R.drawable.no_heart).getConstantState()){

            heartBTN.setBackgroundResource(R.drawable.yes_heart);
        }

        else if(heartBTN.getDrawable().getConstantState() ==
                getResources().getDrawable(R.drawable.yes_heart).getConstantState()) {

            heartBTN.setBackgroundResource(R.drawable.no_heart);
        }

    }
}