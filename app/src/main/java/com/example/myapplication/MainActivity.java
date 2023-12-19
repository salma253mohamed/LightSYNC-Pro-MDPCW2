package com.example.myapplication;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.util.Pair;
import android.view.View;


import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    //variables
    Animation topAnim;
    ImageView image;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        //Hooks
        image = findViewById(R.id.imageView);
        image.setAnimation(topAnim);
        int SPLASH_SCREEN = 3500; // Splash screen duration in milliseconds

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, Login.class);
            Pair<View,String>[] pairs = new Pair[1];
            pairs[0] = new Pair<View, String>(image, "logo");
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                startActivity(intent, options.toBundle());
            }
            finish();
        }, SPLASH_SCREEN);


    }
}
