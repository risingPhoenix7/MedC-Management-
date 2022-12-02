package com.example.medcmanagementapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateAppointment extends AppCompatActivity {
    Spinner dropdDownMenu;
    Button submitButton;
    String bitsID;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);
        dropdDownMenu = findViewById(R.id.spinner);
        submitButton = findViewById(R.id.button17);
        Intent intent = this.getIntent();
        bitsID = intent.getStringExtra("Studentid");

        ArrayAdapter<String> dropdownAdapter = new ArrayAdapter<String>(CreateAppointment.this, android.R.layout.simple_list_item_1, DataClass.getDoctorSpecialisations());
        dropdownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdDownMenu.setAdapter(dropdownAdapter);
        submitButton.setOnClickListener(view -> {
            LocalDateTime a = DataClass.checkForAppointment(dropdDownMenu.getSelectedItem().toString(), bitsID);
            Toast toast;
            if (a != null) {
                toast = Toast.makeText(getApplicationContext(), "Appointment created at" + a.format(DateTimeFormatter.ofPattern("HH:mm")) + ". Please be on time", Toast.LENGTH_SHORT);
            } else {
                toast = Toast.makeText(getApplicationContext(), "Could not get appointment today. Please try tomorrow.", Toast.LENGTH_SHORT);
            }
            toast.show();
            finish();
        });
    }
}