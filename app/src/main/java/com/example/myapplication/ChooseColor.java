package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseColor extends AppCompatActivity {
    ImageView colorPicker;
    SeekBar BrightnessBar;
    View hexView,rgbView;
    Bitmap bitmap;
    Button go2home;
    boolean success = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_color);
        go2home = findViewById(R.id.backButton);
        colorPicker= findViewById(R.id.colorpicker);
        hexView = findViewById(R.id.hexColorView);
        BrightnessBar = (SeekBar) findViewById(R.id.brightness);
        colorPicker.setDrawingCacheEnabled(true);
        colorPicker.buildDrawingCache(true);
        colorPicker.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
                    bitmap = colorPicker.getDrawingCache();
                    int pixels = bitmap.getPixel((int)event.getX(),(int) event.getY());

                    int r = Color.red(pixels);
                    int g = Color.green(pixels);
                    int b = Color.blue(pixels);

                    String Hex = "#"+ Integer.toHexString(pixels);
                    hexView.setBackgroundColor(Color.rgb(r,g,b));
                }
                return true;
            }
        });

        go2home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseColor.this,UserProfile.class));
            }
        });
//       //Setting the brightness levels
        BrightnessBar.setMax(255);
//        BrightnessBar.setProgress(getBrightness());
//        getPermission();
//        BrightnessBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                if(fromUser && success){
//                    setBrightness(progress);
//                }
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                if(!success){
//                    Toast.makeText(ChooseColor.this, "Permission not granted", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        // Apply slide-up animation
        LinearLayout splashLayout = findViewById(R.id.chooseColor);
        Animation slideUpAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        splashLayout.startAnimation(slideUpAnimation);

        // Start the MainActivity after the animation completes
        slideUpAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
//    private void setBrightness(int brightness){
//        if (brightness < 0){
//            brightness = 0;
//        } else if (brightness > 255) {
//            brightness = 255;
//        }
//        ContentResolver contentResolver = getApplicationContext().getContentResolver();
//        Settings.System.putInt(contentResolver,Settings.System.SCREEN_BRIGHTNESS,brightness);
//    }
//
//    private int getBrightness(){
//        int brightness = 100;
//        try {
//            ContentResolver contentResolver = getApplicationContext().getContentResolver();
//            brightness = Settings.System.getInt(contentResolver,Settings.System.SCREEN_BRIGHTNESS);
//        }catch (Settings.SettingNotFoundException e){
//            e.printStackTrace();
//        }
//        return brightness;
//    }
//
//    private void getPermission(){
//        boolean value;
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            value = Settings.System.canWrite(getApplicationContext());
//            if(value){
//                success = true;
//            }else {
//                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
//                intent.setData(Uri.parse("package: "+ getApplicationContext().getPackageName()));
//                startActivityForResult(intent,1000);
//            }
//        }
//    }
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1000) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                boolean value = Settings.System.canWrite(getApplicationContext());
//                if (value) {
//                    success = true;
//                } else {
//                    Toast.makeText(ChooseColor.this, "Permission not granted", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }
}
