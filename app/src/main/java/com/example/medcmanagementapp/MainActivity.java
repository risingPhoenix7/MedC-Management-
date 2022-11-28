package com.example.medcmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button adminButton;
    Button studentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adminButton = findViewById(R.id.adminbutton);
        studentButton = findViewById(R.id.studentButton);
        adminButton.setOnClickListener(view -> {
            Intent launchActivity1 = new Intent(MainActivity.this, AdminPage.class);
            startActivity(launchActivity1);
        });
        studentButton.setOnClickListener(view -> {
            Intent launchActivity2 = new Intent(MainActivity.this, StudentPage.class);
            startActivity(launchActivity2);
        });
    }
}