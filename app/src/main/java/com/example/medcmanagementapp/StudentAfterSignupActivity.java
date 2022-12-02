package com.example.medcmanagementapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StudentAfterSignupActivity extends AppCompatActivity {
    String studentid = "0";
    TextView name;
    Button viewAppointments;
    Button createAppointments;
    Button buyMedicines;
    Button seePurchases;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_after_signup);
        Intent intent = this.getIntent();
        try {
            studentid = intent.getExtras().get("Studentid").toString();
            System.out.println(studentid);
        } catch (Exception e) {
            studentid = "5487";
        }
        name = findViewById(R.id.textView23);
        viewAppointments = findViewById(R.id.button11);
        createAppointments = findViewById(R.id.button12);
        buyMedicines = findViewById(R.id.button13);
        seePurchases = findViewById(R.id.button14);

        viewAppointments.setOnClickListener(view -> {
            Intent appointmentIntent = new Intent(StudentAfterSignupActivity.this, AppointmentListActivity.class);
            appointmentIntent.putExtra("Studentid", studentid);
            startActivity(appointmentIntent);
        });
        createAppointments.setOnClickListener(view -> {
            Intent appointmentIntent = new Intent(StudentAfterSignupActivity.this, CreateAppointment.class);
            appointmentIntent.putExtra("Studentid", studentid);
            startActivity(appointmentIntent);
        });
        buyMedicines.setOnClickListener(view -> {
            Intent appointmentIntent = new Intent(StudentAfterSignupActivity.this, BuyMedicinesActivity.class);
            appointmentIntent.putExtra("Studentid", studentid);
            startActivity(appointmentIntent);
        });
        seePurchases.setOnClickListener(view -> {
            Intent appointmentIntent = new Intent(StudentAfterSignupActivity.this, ViewOrdersActivity.class);
            appointmentIntent.putExtra("Studentid", studentid);
            startActivity(appointmentIntent);
        });
        name.setText(studentid);
    }

}