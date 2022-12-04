package com.example.medcmanagementapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpPage extends AppCompatActivity {
    EditText nameEditText;
    EditText idEditText;
    EditText emailEditText;
    EditText PhoneEditText;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        nameEditText = findViewById(R.id.editTextTextPersonName);
        idEditText = findViewById(R.id.editTextTextPersonName2);
        emailEditText = findViewById(R.id.editTextTextPersonName3);
        PhoneEditText = findViewById(R.id.editTextTextPersonName4);
        submitButton = findViewById(R.id.button);
        submitButton.setOnClickListener(view -> {
            try {
                if (!nameEditText.getText().toString().isEmpty() && !idEditText.getText().toString().isEmpty() && !emailEditText.getText().toString().isEmpty() && !PhoneEditText.getText().toString().isEmpty()) {
                    String a = DataClass.validateUser(new Student(nameEditText.getText().toString(), (idEditText.getText().toString()), emailEditText.getText().toString(), PhoneEditText.getText().toString()));
                    if (a ==null) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Successfully added user. Please login", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), a, Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }
            } catch (Exception e) {
                System.out.println(e);
                Toast toast = Toast.makeText(getApplicationContext(), "Could not add user", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}