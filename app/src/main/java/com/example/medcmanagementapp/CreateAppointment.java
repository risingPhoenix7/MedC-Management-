package com.example.medcmanagementapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CreateAppointment extends AppCompatActivity {
    Spinner dropdDownMenu;
    Button submitButton;
    String bitsID;
    Spinner daySelector;
    Spinner timeSelector;
    Button showTimeSelector;
    ArrayList<LocalDateTime> localDateTimeArrayListReturnedFromFunction;
    String[] availableDays;
    String[] availableTimeSlots;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);
        dropdDownMenu = findViewById(R.id.spinner);
        submitButton = findViewById(R.id.button17);
        Intent intent = this.getIntent();
        bitsID = intent.getStringExtra("Studentid");
        daySelector = findViewById(R.id.spinner3);
        timeSelector = findViewById(R.id.spinner4);
        showTimeSelector = findViewById(R.id.button21);
        timeSelector.setVisibility(View.GONE);
//        String[] dayList = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        ArrayAdapter<String> dropdownAdapter = new ArrayAdapter<String>(CreateAppointment.this, android.R.layout.simple_list_item_1, DataClass.getDoctorSpecialisations());
        dropdownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdDownMenu.setAdapter(dropdownAdapter);
        updateAvailableDays();
        ArrayAdapter<String> dropdownAdapter2 = new ArrayAdapter<String>(CreateAppointment.this, android.R.layout.simple_list_item_1, availableDays);
        dropdownAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySelector.setAdapter(dropdownAdapter2);
        updateAvailableTimeSlots();
        ArrayAdapter<String> dropdownAdapter3 = new ArrayAdapter<String>(CreateAppointment.this, android.R.layout.simple_list_item_1, availableTimeSlots);
        dropdownAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSelector.setAdapter(dropdownAdapter3);
        showTimeSelector.setOnClickListener(view -> {
            if (dropdDownMenu.getSelectedItem() != null && daySelector.getSelectedItem() != null) {
                timeSelector.setVisibility(View.VISIBLE);
                updateAvailableTimeSlots();
                timeSelector.setAdapter(new ArrayAdapter<String>(CreateAppointment.this, android.R.layout.simple_list_item_1, availableTimeSlots));

            }
        });

        dropdDownMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("KJWDBDWNBDWNBWD");
//                dropdownAdapter2.clear();
//                dropdownAdapter2 = new ArrayAdapter<String>(CreateAppointment.this, android.R.layout.simple_list_item_1, DataClass.getDoctorAvailableDays(dropdDownMenu.getSelectedItemPosition()));
                updateAvailableDays();
                updateAvailableTimeSlots();
                timeSelector.setAdapter(new ArrayAdapter<String>(CreateAppointment.this, android.R.layout.simple_list_item_1, availableTimeSlots));
                daySelector.setAdapter(new ArrayAdapter<String>(CreateAppointment.this, android.R.layout.simple_list_item_1, availableDays));
            }

            public void onNothingSelected(AdapterView<?> parent) {
                System.out.println("Nothing selected");

            }
        });


        submitButton.setOnClickListener(view -> {
            Appointment appointment;
            System.out.println("ihdwghjwndbjhwbddwn");
            System.out.println(daySelector.getSelectedItem());
            System.out.println(timeSelector.getSelectedItem());
            System.out.println(localDateTimeArrayListReturnedFromFunction);
            System.out.println(bitsID);
            System.out.println("KJWDHKDWHBKDWN");
            if (daySelector.getSelectedItem() != null && timeSelector.getSelectedItem() != null && localDateTimeArrayListReturnedFromFunction != null && bitsID != null) {
                appointment = DataClass.checkandCreateAppointment(localDateTimeArrayListReturnedFromFunction.get(timeSelector.getSelectedItemPosition()), dropdDownMenu.getSelectedItemPosition(), bitsID);
                Toast toast = Toast.makeText(getApplicationContext(), "Appointment created at" + appointment.getTimestamp().format(DateTimeFormatter.ofPattern("HH:mm")) + ". Please be on time", Toast.LENGTH_SHORT);
                toast.show();
                finish();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Please complete all fields.", Toast.LENGTH_SHORT);
                toast.show();

            }

        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    String[] getFormattedLocalDateTimeArray(Integer id, String selectedDay) {
        System.out.println("WDKJHKDJBHKJDWGBKHDNWVKHWND");
        System.out.println(id);
        System.out.println(selectedDay);
        localDateTimeArrayListReturnedFromFunction = DataClass.getAvailableTimes(id, selectedDay);
        String[] array = new String[localDateTimeArrayListReturnedFromFunction.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = localDateTimeArrayListReturnedFromFunction.get(i).format(DateTimeFormatter.ofPattern("MM-dd HH:mm:ss"));
        }
        return array;
    }

    void updateAvailableDays() {
        availableDays = DataClass.getDoctorAvailableDays(dropdDownMenu.getSelectedItemPosition());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void updateAvailableTimeSlots() {
        availableTimeSlots = getFormattedLocalDateTimeArray(dropdDownMenu.getSelectedItemPosition(), daySelector.getSelectedItem().toString());
    }

}
