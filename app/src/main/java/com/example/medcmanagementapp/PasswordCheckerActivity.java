package com.example.medcmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PasswordCheckerActivity extends AppCompatActivity {
    Button submitField;
    EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_checker);
        submitField = findViewById(R.id.button19);
        passwordField = findViewById(R.id.editTextTextPersonName19);
        submitField.setOnClickListener(view -> {
            if (passwordField.getText() != null && passwordField.getText().toString().equals("adminpassword")) {
                Toast toast = Toast.makeText(getApplicationContext(), "Welcome!!", Toast.LENGTH_SHORT);
                toast.show();
                Intent launchActivity1 = new Intent(PasswordCheckerActivity.this, AdminPage.class);
                startActivity(launchActivity1);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_SHORT);
                toast.show();
            }

        });
    }
}