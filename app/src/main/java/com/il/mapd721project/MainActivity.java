package com.il.mapd721project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText user_email, user_password;
    Button btnLogin, btnRegister;
    FirebaseAuth mAuth;
    //RegisterClass register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_email = findViewById(R.id.email_login);
        user_password = findViewById(R.id.password_login);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register1);
        mAuth = FirebaseAuth.getInstance();
        //register = new RegisterClass();

        // redirect to Register page code
        //Button btnregister = findViewById(R.id.btn_register1);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private  void loginUser() {
        String email = user_email.getText().toString();
        String password = user_password.getText().toString();

        if (TextUtils.isEmpty(email)) {
            user_email.setError("Email cannot be empty");
            user_email.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            user_password.setError("Password cannot be empty");
            user_password.requestFocus();
        } else {

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, Homepage.class));
                    } else {
                        Toast.makeText(MainActivity.this, "Log In Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}