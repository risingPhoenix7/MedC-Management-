package com.example.medcmanagementapp;

import android.content.Intent;
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
    String studentid;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityAppointmentListBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        Intent thisIndent = this.getIntent();
        studentid = thisIndent.getStringExtra("Studentid");
        listAdapter = new AppointmentListAdapter(AppointmentListActivity.this, studentid == null ? DataClass.appointmentList : DataClass.getAppointmentList(studentid), studentid == null);
        binding.viewAppointmentList.setAdapter(listAdapter);
    }

    @Override
    protected void onResume() {
        listAdapter.notifyDataSetChanged();
        super.onResume();
    }
}