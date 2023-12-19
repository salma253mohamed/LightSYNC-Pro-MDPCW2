package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    FirebaseAuth auth;
    EditText reguser,regpass;
    Button signUp,gotoLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        reguser = findViewById(R.id.username);
        regpass = findViewById(R.id.password);
        signUp = findViewById(R.id.signup);
        gotoLogin = findViewById(R.id.go2login);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = reguser.getText().toString().trim();
                String pass = regpass.getText().toString().trim();

                if(user.isEmpty()){
                    reguser.setError("Email cannot be empty");
                }
                if (pass.isEmpty()){
                    regpass.setError("Password cannot be empty");
                }else {
                    auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignUp.this, "SignUp successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUp.this,Login.class));
                            }else {
                                Toast.makeText(SignUp.this, "Signup failed"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, Login.class));
            }
        });

    }
}



