package com.example.medcmanagementapp;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpPage extends AppCompatActivity {
    EditText nameEditText;
    EditText idEditText;
    EditText emailEditText;
    EditText PhoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        nameEditText = findViewById(R.id.editTextTextPersonName);
        idEditText = findViewById(R.id.editTextTextPersonName2);
        emailEditText = findViewById(R.id.editTextTextPersonName3);
        PhoneEditText = findViewById(R.id.editTextTextPersonName4);
    }
}