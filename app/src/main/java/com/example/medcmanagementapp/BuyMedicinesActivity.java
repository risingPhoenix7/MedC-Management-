package com.example.medcmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BuyMedicinesActivity extends AppCompatActivity {
    Spinner medicineDropdDownMenu;
    Button buyButton;
    EditText quantityField;
    TextView totalPrice;
    Medicine medicine;
    String studentid;
    RadioGroup modeOfPayment;

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        medicine = DataClass.medicineList.get(pos);
        quantityField.setText(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicines);
        Intent intent = this.getIntent();
        studentid = intent.getStringExtra("Studentid");
        medicineDropdDownMenu = findViewById(R.id.spinner2);
        buyButton = findViewById(R.id.button15);
        quantityField = findViewById(R.id.editTextTextPersonName18);
        totalPrice = findViewById(R.id.textView27);
        modeOfPayment = findViewById(R.id.singlecorrect);
        modeOfPayment.check(R.id.studentacc);
        ArrayAdapter<String> dropdownAdapter = new ArrayAdapter<String>(BuyMedicinesActivity.this, android.R.layout.simple_list_item_1, DataClass.getMedicineList());
        dropdownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medicineDropdDownMenu.setAdapter(dropdownAdapter);
        medicineDropdDownMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                medicine = DataClass.medicineList.get(position);
                quantityField.setText("0");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                medicine = null;
                quantityField.setText("0");
            }
        });
        buyButton.setOnClickListener(view -> {
            RadioButton radioButton = findViewById(modeOfPayment.getCheckedRadioButtonId());
            if (!(quantityField.getText().toString().isEmpty())) {
                if (medicine != null && Integer.parseInt(quantityField.getText().toString()) > 0) {
                    boolean a=DataClass.buyMedicine(medicine, Integer.parseInt(quantityField.getText().toString()), radioButton.getText().equals("Cash"), Integer.parseInt(studentid));
                    if(a){
                        Toast toast = Toast.makeText(getApplicationContext(), "Bought successfully", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Error occured", Toast.LENGTH_SHORT);
                    toast.show();
                }
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Enter quantity", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        quantityField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.println("huhuhuhuhuhu");
                System.out.println(s);
                if (!(s.toString().isEmpty())) {
                    int quantity = Integer.parseInt(s.toString());
                    if (medicine != null) {
                        if (quantity <= medicine.getDefQuantity()) {
                            totalPrice.setText(Float.toString(medicine.getPrice() * quantity));
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Only " + medicine.getStringQuantity() + " numbers available ", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Please select an item first", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                else{
                    totalPrice.setText("0");
                }


            }
        });
    }
}