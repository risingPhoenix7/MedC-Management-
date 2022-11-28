package com.example.medcmanagementapp;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medcmanagementapp.databinding.ActivityAppointmentListBinding;

import java.time.LocalDateTime;
import java.util.UUID;

public class AppointmentListActivity extends AppCompatActivity {
    ActivityAppointmentListBinding binding;
    AppointmentListAdapter listAdapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DataClass.appointmentList.add(new Appointment("f2020", UUID.randomUUID(), LocalDateTime.now()));
        binding = ActivityAppointmentListBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        listAdapter = new AppointmentListAdapter(AppointmentListActivity.this, DataClass.appointmentList);
        binding.viewAppointmentList.setAdapter(listAdapter);
    }

    @Override
    protected void onResume() {
        listAdapter.notifyDataSetChanged();
        super.onResume();
    }
}