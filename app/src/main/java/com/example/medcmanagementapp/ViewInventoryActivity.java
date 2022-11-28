package com.example.medcmanagementapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medcmanagementapp.databinding.ActivityViewInventoryBinding;

public class ViewInventoryActivity extends AppCompatActivity {
    ActivityViewInventoryBinding binding;
    Button addInventoryPage;
    InventoryListAdapter listAdapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewInventoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addInventoryPage = findViewById(R.id.add_medicines);
        listAdapter = new InventoryListAdapter(ViewInventoryActivity.this, DataClass.medicineList);
        binding.viewInventoryList.setAdapter(listAdapter);
        binding.viewInventoryList.setClickable(true);
        binding.viewInventoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ViewInventoryActivity.this, EditMedicineActivity.class);
                intent.putExtra("position", i);
                startActivity(intent);
            }
        });

        addInventoryPage.setOnClickListener(view -> {
            Intent launchActivity1 = new Intent(ViewInventoryActivity.this, AddMedicinesActivity.class);
            startActivity(launchActivity1);
        });

    }

    @Override
    protected void onResume() {
        listAdapter.notifyDataSetChanged();
        super.onResume();
    }
}
