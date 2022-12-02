package com.example.medcmanagementapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddMedicinesActivity extends AppCompatActivity {
    EditText medicineName;
    EditText medicineQuantity;
    EditText medicinePrice;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicines);
        medicineName = findViewById(R.id.editTextTextPersonName12);
        medicineQuantity = findViewById(R.id.editTextTextPersonName13);
        medicinePrice = findViewById(R.id.editTextTextPersonName14);
        addButton = findViewById(R.id.button8);
        addButton.setOnClickListener(view -> {
                    if (!medicineName.getText().toString().isEmpty() &&
                            !medicineQuantity.getText().toString().isEmpty()
                            && !medicinePrice.getText().toString().isEmpty()) {
                        try {
                            int a = Integer.parseInt(medicineQuantity.getText().toString());
                            float b = Float.parseFloat(medicinePrice.getText().toString());
                            if (a >= 0 && b >= 0) {
                                if(DataClass.addMedicine(new Medicine(medicineName.getText().toString(), b, a))){
                                    Toast toast = Toast.makeText(getApplicationContext(), "Added successfully", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                                else{
                                    Toast toast = Toast.makeText(getApplicationContext(), "Such a medicine already exists", Toast.LENGTH_SHORT);
                                    toast.show();
                                }

                            }

                        } catch (Exception e) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Error in data fields", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Some fields are empty", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

        );


    }
}