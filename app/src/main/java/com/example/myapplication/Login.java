package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.nio.charset.StandardCharsets;

public class Login extends AppCompatActivity {
    FirebaseAuth auth;
    Button callSignUp,logInBtn;
    ImageView image;
    TextView logotext, logotext2;
    EditText username, password;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        //Hooks
        callSignUp = findViewById(R.id.signup);
        image = findViewById(R.id.imageView);
        logotext = findViewById(R.id.intro);
        logotext2 = findViewById(R.id.intro2);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        logInBtn = findViewById(R.id.login);
        logInBtn.setOnClickListener(view -> {
            // Handle the login button click
            loginUser(view);
        });
        callSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this,SignUp.class);

            Pair[] pairs = new Pair[7];
            pairs[0] = new Pair<View,String>(image,"logo");
            pairs[1] = new Pair<View,String>(logotext,"logoText");
            pairs[2] = new Pair<View,String>(logotext2,"logoText2");
            pairs[3] = new Pair<View,String>(username,"username_tran");
            pairs[4] = new Pair<View,String>(password,"password_tran");
            pairs[5] = new Pair<View,String>(logInBtn,"go_tran");
            pairs[6] = new Pair<View,String>(callSignUp,"login2signup_tran");



            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
                startActivity(intent,options.toBundle());
            }
        });
    }
    public void loginUser(View view) {
        String email = username.getText().toString();
        String pass = password.getText().toString();

        // Validate email and password
        if (validateEmail(email) && validatePassword(pass)) {
            // Delegate the authentication logic to a separate method
            authenticateUser(email, pass);
        }
    }

    boolean validateEmail(String email) {
        if (email.isEmpty()) {
            username.setError("Email cannot be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            username.setError("Please enter a valid email");
            return false;
        }
        return true;
    }

    boolean validatePassword(String password) {
        if (password.isEmpty()) {
            this.password.setError("Password cannot be empty");
            return false;
        }
        return true;
    }

    void authenticateUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, UserProfile.class));
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                });
    }
}

