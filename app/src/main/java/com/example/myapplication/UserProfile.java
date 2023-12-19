package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class UserProfile extends AppCompatActivity {

    Button gotocolor,gotoscene,gotolist,go2connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_profile);

        ScrollView mainLayout = findViewById(R.id.userProfile);
        Animation slideDownAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        mainLayout.startAnimation(slideDownAnimation);
        gotocolor = findViewById(R.id.go2colorScreen);
        gotoscene = findViewById(R.id.go2createScreen);
        gotolist = findViewById(R.id.go2ListScreen);
        go2connect = findViewById(R.id.connectBt);

        go2connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this,
                        ConnectThread.class));
            }
        });

        gotocolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, ChooseColor.class));
            }
        });
        gotoscene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, CreateScene.class));
            }
        });
        gotolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, ListColors.class));
            }
        });
    }
}