package com.example.medcmanagementapp;

import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CreateAppointment extends AppCompatActivity {
    Spinner dropdDownMenu;
    Button chooseDate;
    Button submitButton;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);
        dropdDownMenu = findViewById(R.id.spinner);
        submitButton = findViewById(R.id.button17);

        ArrayAdapter<String> dropdownAdapter = new ArrayAdapter<String>(CreateAppointment.this, android.R.layout.simple_list_item_1, DataClass.getDoctorSpecialisations());
        dropdownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdDownMenu.setAdapter(dropdownAdapter);
        submitButton.setOnClickListener(view->{
           boolean a= DataClass.checkForAppointment(dropdDownMenu.getSelectedItem().toString());
        });
    }
}