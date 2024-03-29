package com.example.medcmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentPage extends AppCompatActivity {
    Button signUpButton;
    Button singInButton;
    EditText userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_page);
        signUpButton = findViewById(R.id.signUp);
        singInButton = findViewById(R.id.signIn);
        userId = findViewById(R.id.editTextTextPersonName6);
        signUpButton.setOnClickListener(view -> {
            Intent launchActivity1 = new Intent(StudentPage.this, SignUpPage.class);
            startActivity(launchActivity1);
        });
        singInButton.setOnClickListener(view -> {
            try {
                if (DataClass.checkIfStudentExists((userId.getText().toString()))) {
                    Intent launchActivity = new Intent(StudentPage.this, StudentAfterSignupActivity.class);
                    String a = (userId.getText().toString());
                    launchActivity.putExtra("Studentid", a);
                    startActivity(launchActivity);
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT);
                    toast.show();
                }

            } catch (Exception e) {
                Toast toast = Toast.makeText(getApplicationContext(), "Check your details again", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}