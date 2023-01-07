package com.example.catpanion;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;


public class Home_Fragment extends Fragment {

    ImageButton heartBTN;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // FOR WIDGETS
        View rootView = inflater.inflate(R.layout.fragment_home_, container, false);
        View view = getLayoutInflater().inflate(R.layout.fragment_home_,container, false);

        // HEART AND UNHEART
        heartBTN = (ImageButton) view.findViewById(R.id.heartButton);
        heartBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(heartBTN.getDrawable().getConstantState() ==
                        getResources().getDrawable(R.drawable.no_heart).getConstantState()){

                    heartBTN.setImageResource(R.drawable.yes_heart);
                } else {
                    heartBTN.setImageResource(R.drawable.no_heart);
                }
            }
        });

        // INFLATE THE LAYOUT FOR THIS FRAGMENT
            // return inflater.inflate(R.layout.fragment_home_, container, false);
        return view;

    }
}