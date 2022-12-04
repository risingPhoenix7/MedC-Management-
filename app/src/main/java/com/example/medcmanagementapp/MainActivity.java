package com.example.medcmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button adminButton;
    Button studentButton;
    Button medicalShopOwnerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adminButton = findViewById(R.id.adminbutton);
        studentButton = findViewById(R.id.studentButton);
        medicalShopOwnerButton = findViewById(R.id.medicalShopOwnerButton);
        adminButton.setOnClickListener(view -> {
            Intent launchActivity1 = new Intent(MainActivity.this, PasswordCheckerActivity.class);
            startActivity(launchActivity1);
        });
        medicalShopOwnerButton.setOnClickListener(view -> {
            Intent launchActivity1 = new Intent(MainActivity.this, AdminPage.class);
            launchActivity1.putExtra("isMedicalShopOwner","true");
            startActivity(launchActivity1);
        });
        studentButton.setOnClickListener(view -> {
            Intent launchActivity2 = new Intent(MainActivity.this, StudentPage.class);
            startActivity(launchActivity2);
        });
    }
}