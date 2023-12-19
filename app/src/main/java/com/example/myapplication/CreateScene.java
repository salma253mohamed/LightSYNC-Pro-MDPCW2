package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateScene extends AppCompatActivity {

    ImageView colorpicker;
    View Color1View, Color2View, Color3View;

    String colorValue1, colorValue2, colorValue3;
    TextView SceneName;
    Bitmap bitmap;
    Button create, go2home;
    private boolean isFirstViewColored, isSecondViewColored = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_scene);

        colorpicker = findViewById(R.id.colorpicker);
        Color1View = findViewById(R.id.ChooseColor1View);
        Color2View = findViewById(R.id.ChooseColor2View);
        Color3View = findViewById(R.id.ChooseColor3View);
        create = findViewById(R.id.createScenebt);
        go2home = findViewById(R.id.backButton);
        SceneName = findViewById(R.id.SceneInput);

        // Get color values from the views
        colorValue1 = getColorAsString(Color1View);
        colorValue2 = getColorAsString(Color2View);
        colorValue3 = getColorAsString(Color3View);

        ImageView colorPicker = findViewById(R.id.colorpicker);
        colorPicker.setDrawingCacheEnabled(true);
        colorPicker.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    bitmap = colorPicker.getDrawingCache();
                    int pixels = bitmap.getPixel((int) event.getX(), (int) event.getY());

                    int selectedColor = Color.rgb(Color.red(pixels), Color.green(pixels), Color.blue(pixels));

                    if (!isFirstViewColored) {
                        showColorPickerDialog(selectedColor, Color1View);
                        Log.d("My Tag",getColorAsString(Color1View));
                        //Color1View.setBackgroundColor(selectedColor);
                        isFirstViewColored = true;
                    } else if (isFirstViewColored) {
                        showColorPickerDialog(selectedColor, Color2View);
                        Log.d("My Tag",getColorAsString(Color2View));
                        //Color2View.setBackgroundColor(selectedColor);
                        isSecondViewColored = true;
                    } else if (isFirstViewColored && isSecondViewColored) {
                        showColorPickerDialog(selectedColor, Color3View);
                        //Color3View.setBackgroundColor(selectedColor);

                    }
                }
                return true;
            }
        });
        go2home.setOnClickListener(v -> startActivity(new Intent(CreateScene.this, UserProfile.class)));
        create.setOnClickListener(v -> {
            // Get the text from the TextView as CharSequence
            CharSequence sceneNameText = SceneName.getText();

            // Convert CharSequence to String
            String scenetext = sceneNameText.toString();

            String colorName = scenetext;  // Replace with actual user input

            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference userReference = FirebaseDatabase.getInstance().getReference().child("users");
            if (currentUser != null) {
                String id = currentUser.getUid();
                UserColors colorsObject = new UserColors( colorName, colorValue1,colorValue2);
                Log.d("My Tag", String.valueOf(colorsObject));
                userReference.push().setValue(colorsObject);
                Toast.makeText(this, "Data added", Toast.LENGTH_LONG).show();
                // Update the color under the user ID
                userReference.child(id).setValue(colorsObject).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Update was successful
                        Toast.makeText(getApplicationContext(), "Data added successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        // If there is an error, log it or show a message
                        Log.d("FirebaseError", "Data could not be added: " + task.getException().getMessage());
                    }
                });
            }

        });
    }

    private void storeColorsInDatabase(String colorName, List<String> selectedColors) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            String userId = currentUser.getUid();

            // Reference to the users node in the Realtime Database
            DatabaseReference usersReference = FirebaseDatabase.getInstance().getReference().child("users");
            Map<String, List> users = new HashMap<>();
            users.put(userId, selectedColors);
            usersReference.setValue(users);
            // Retrieve user data
//            usersReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    if (dataSnapshot.exists()) {
//                        // Assuming 'name' is the key for the username
//                        String userName = dataSnapshot.child("users").getValue(String.class);
//
//                        // Now, you can use 'userId', 'userName', and 'colorName' in your database operations
//                        UserColors userColors = new UserColors(userId, userName, selectedColors);
//
//                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//
//                        // Add the data to the "users" node
//                        databaseReference.child("users").child(userId).child("userColors").setValue(selectedColors);
//                        databaseReference.child("users").child(userId).child("Scenename").setValue(colorName);
//
//                        usersReference.setValue(userColors)
//                                .addOnSuccessListener(aVoid -> {
//                                    // Data successfully saved
//                                    Toast.makeText(getApplicationContext(), "Data stored successfully", Toast.LENGTH_SHORT).show();
//                                })
//                                .addOnFailureListener(e -> {
//                                    // Handle the error
//                                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                                });
//                    }


//                }
//
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    Toast.makeText(getApplicationContext(), "Data didn't store successfully", Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }

    private void showColorPickerDialog(int color, View view) {
        // Set the background color of the first view
        view.setBackgroundColor(color);
    }

    public static String getColorAsString(View view) {
        String colorString = null;
        if (view != null) {
            // Get the background drawable
            android.graphics.drawable.Drawable backgroundDrawable = view.getBackground();

            // If the background is a ColorDrawable, extract the color
            if (backgroundDrawable instanceof ColorDrawable) {
                int color = ((ColorDrawable) backgroundDrawable).getColor();
                colorString = String.format("#%06X", 0xFFFFFF & color);
                // Convert the color integer to a hex string
            }
        }
        return colorString;
    }

    public void setColorValue2(String colorValue2) {
        this.colorValue2 = colorValue2;
    }

    public void setColorValue1(String colorValue1) {
        this.colorValue1 = colorValue1;
    }

    public void setColorValue3(String colorValue3) {
        this.colorValue3 = colorValue3;
    }
}
