package com.example.catpanion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.annotation.Nullable;

public class Profile_Fragment extends Fragment implements View.OnClickListener {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

/**/
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        TextView textView = getActivity().findViewById(R.id.txtViewProfile);
        textView.setOnClickListener(this);

    }


    // To change user profile
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtViewProfile:
            Intent intent = new Intent(getActivity(), SignUpDetails.class);
            startActivity(intent);
        }
    }
}