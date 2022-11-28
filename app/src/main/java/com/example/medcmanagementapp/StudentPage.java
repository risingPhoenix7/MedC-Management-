package com.example.medcmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class StudentPage extends AppCompatActivity {
    Button signUpButton;
    Button singInButton;
    EditText userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_page);
        signUpButton = findViewById (R.id.signUp);
        singInButton = findViewById (R.id.signIn);
        userId = findViewById (R.id.editTextTextPersonName6);
        signUpButton.setOnClickListener(view -> {
            Intent launchActivity1= new Intent(StudentPage.this,SignUpPage.class);
            startActivity(launchActivity1);
        });
        singInButton.setOnClickListener(view -> {
            //TODO:check user id exists
        });
    }
}