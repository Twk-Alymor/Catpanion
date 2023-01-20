package com.example.catpanion;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import javax.annotation.Nullable;


public class Home_Fragment extends Fragment {

    ImageButton heartBTN, heartBTN2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // FOR WIDGETS
        View rootView = inflater.inflate(R.layout.fragment_home_, container, false);
        View view = getLayoutInflater().inflate(R.layout.fragment_home_,container, false);


        heartBTN = (ImageButton) view.findViewById(R.id.heartButton);
        heartBTN2 = (ImageButton) view.findViewById(R.id.heartButton2);

        // HEART AND UNHEART
        heartBTN.setOnClickListener(hearted);
        heartBTN2.setOnClickListener(hearted);


        // INFLATE THE LAYOUT FOR THIS FRAGMENT
            // return inflater.inflate(R.layout.fragment_home_, container, false);
        return view;

    }


    /** HEART AND UNHEART **/
    public View.OnClickListener hearted = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.heartButton:
                    if(heartBTN.getDrawable().getConstantState() ==
                            getResources().getDrawable(R.drawable.no_heart).getConstantState()){

                        heartBTN.setImageResource(R.drawable.yes_heart);
                        Toast.makeText(getActivity(), "Added to Favorites!", Toast.LENGTH_SHORT).show();
                    } else {
                        heartBTN.setImageResource(R.drawable.no_heart);
                    }
                    break;

                case R.id.heartButton2:
                    if(heartBTN2.getDrawable().getConstantState() ==
                            getResources().getDrawable(R.drawable.no_heart).getConstantState()){

                        heartBTN2.setImageResource(R.drawable.yes_heart);
                    } else {
                        heartBTN2.setImageResource(R.drawable.no_heart);
                        Toast.makeText(getActivity(), "Added to Favorites!", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

}