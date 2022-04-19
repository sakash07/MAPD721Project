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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Member;

public class RegisterActivity extends AppCompatActivity {
    EditText user_name_register, user_email_register, user_password_register, user_repassword_register, user_address_register;
    Button btn_register;
    DatabaseReference dbref;
    RegisterClass register;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user_name_register = (EditText)findViewById(R.id.user_name_register);
        user_email_register = (EditText)findViewById(R.id.user_email_register);
        user_password_register = (EditText)findViewById(R.id.user_password_register);
        user_repassword_register = (EditText)findViewById(R.id.user_repassword_register);
        user_address_register = (EditText)findViewById(R.id.user_address_register);
        btn_register = (Button)findViewById(R.id.btn_register2);
        register = new RegisterClass();
        dbref = FirebaseDatabase.getInstance("https://mapd721project-942f6-default-rtdb.firebaseio.com/").getReference().child("RegisterClass");
        mAuth = FirebaseAuth.getInstance();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register.setFullname(user_name_register.getText().toString().trim());
                register.setEmail(user_email_register.getText().toString().trim());
                register.setPassword(user_password_register.getText().toString().trim());
                register.setRetype_pwd(user_repassword_register.getText().toString().trim());
                register.setAddress(user_address_register.getText().toString().trim());
                //dbref.push().setValue(register);
                //Toast.makeText(RegisterActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();

                if (TextUtils.isEmpty(register.getFullname())) {
                    user_name_register.setError("Fullname cannot be empty");
                    user_name_register.requestFocus();
                } else if (TextUtils.isEmpty(register.getEmail())) {
                    user_email_register.setError("Email cannot be empty");
                    user_email_register.requestFocus();
                }  else if (TextUtils.isEmpty(register.getPassword())) {
                    user_password_register.setError("Password cannot be empty");
                    user_password_register.requestFocus();
                }  else if (TextUtils.isEmpty(register.getRetype_pwd())) {
                    user_repassword_register.setError("Re-type password cannot be empty");
                    user_repassword_register.requestFocus();
                }  else if (TextUtils.isEmpty(register.getAddress())) {
                    user_address_register.setError("Address cannot be empty");
                    user_address_register.requestFocus();
                }else {
                    dbref.push().setValue(register);
                    mAuth.createUserWithEmailAndPassword(register.getEmail(), register.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
    }
}