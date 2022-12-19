package com.example.catpanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    ImageButton heartBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // APP BAR
        findViewById(R.id.quicktipsImagebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQuickTipDialog();
            }
        });

        // FOR WIDGETS
        heartBTN = (ImageButton) findViewById(R.id.heartButton);

    }

    // SHOW QUICK TIP DIALOG
    private void showQuickTipDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Home.this, R.style.QuickTip_Theme);
        View view = LayoutInflater.from(Home.this).inflate(
                R.layout.quicktip_layout,findViewById(R.id.layoutQuickTipContainer)
        );
        builder.setView(view);
        // Quick Tip Message
        ((TextView) view.findViewById(R.id.quickTipMessage))
                .setText("Catnip is very good for the cats since it acts as a sedative, which " +
                        "reduces their anxiety, stress, and depression");

        ((Button) view.findViewById(R.id.quickTipAction)).setText("Close");

        final AlertDialog quickTipDialog = builder.create();

        view.findViewById(R.id.quickTipAction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quickTipDialog.dismiss();
                Toast.makeText(Home.this, "Meow :D", Toast.LENGTH_SHORT).show();
            }
        });

        if(quickTipDialog.getWindow() != null){
            quickTipDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        quickTipDialog.show();
    }


    // HEART AND UNHEART (Ayaw gumana ng unheart)
    public void hearted (View v){
        if(heartBTN.getDrawable().getConstantState() ==
                getResources().getDrawable(R.drawable.no_heart).getConstantState()){

            heartBTN.setImageResource(R.drawable.yes_heart);
        } else {
            heartBTN.setImageResource(R.drawable.no_heart  );
        }

    }
}