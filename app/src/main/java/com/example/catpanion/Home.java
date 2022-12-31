package com.example.catpanion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NonNls;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    ImageButton heartBTN;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();

        // QUICK TIPS
        findViewById(R.id.quicktipsImagebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQuickTipDialog();
            }
        });

        // NAVIGATION VIEW
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Profile_Fragment()).commit();
            navigationView.setCheckedItem(R.id.nav_profile);
        }


        // FOR WIDGETS
        heartBTN = (ImageButton) findViewById(R.id.heartButton);

    }

    // NAVIGATION VIEW
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()){
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Profile_Fragment()).commit();
                break;

            case R.id.nav_notifications:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Notifications_Fragment()).commit();
                break;

            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Settings_Fragment()).commit();
                break;

            case R.id.nav_help:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Help_Fragment()).commit();
                break;

            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new About_Fragment()).commit();
                break;

            case R.id.nav_logout:
                firebaseAuth.signOut();
                Toast.makeText(this, "User signed out!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Home.this, MainActivity.class);
                startActivity(i);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // LOGOUT
    public void logout(View v){

        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            Intent i = new Intent(Home.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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



    // HEART AND UNHEART
    public void hearted (View v){
        if(heartBTN.getDrawable().getConstantState() ==
                getResources().getDrawable(R.drawable.no_heart).getConstantState()){

            heartBTN.setImageResource(R.drawable.yes_heart);
        } else {
            heartBTN.setImageResource(R.drawable.no_heart  );
        }

    }
}