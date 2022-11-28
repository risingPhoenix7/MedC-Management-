package com.example.medcmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditMedicineActivity extends AppCompatActivity {
    TextView idTextField;
    EditText medicineNameField;
    EditText medicinePriceField;
    EditText medicineQuantityField;
    Button submitButton;
    String name;
    Float price;
    int quantity;
    Medicine medicine;
    int position=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_medicine);
        idTextField = findViewById(R.id.textView9);
        medicineNameField = findViewById(R.id.editTextTextPersonName15);
        medicinePriceField = findViewById(R.id.editTextTextPersonName16);
        medicineQuantityField = findViewById(R.id.editTextTextPersonName17);
        submitButton = findViewById(R.id.button9);
        Intent intent = this.getIntent();
        if (intent != null) {
            System.out.println(intent.getStringExtra("position"));
            position = (intent.getIntExtra("position", 0));
            medicine = DataClass.medicineList.get(position);
        }
        name = medicine.getItemName();
        price = medicine.getPrice();
        quantity = medicine.getDefQuantity();
        idTextField.setText(medicine.getItemID());
        medicineNameField.setText(name);
        medicinePriceField.setText(medicine.getStringPrice());
        medicineQuantityField.setText(medicine.getStringQuantity());

        submitButton.setOnClickListener(view -> {
            if (!medicineNameField.getText().toString().isEmpty() && !medicineQuantityField.getText().toString().isEmpty() && !medicinePriceField.getText().toString().isEmpty()) {
                try {
                    DataClass.setMedicineFields(position, medicineNameField.getText().toString(), Float.parseFloat(medicinePriceField.getText().toString()), Integer.parseInt(medicineQuantityField.getText().toString()));
                    Toast toast = Toast.makeText(getApplicationContext(), "Successfully edited medicine", Toast.LENGTH_SHORT);
                    toast.show();
                    finish();
                } catch (Exception e) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Error in some or multiple fields", Toast.LENGTH_SHORT);
                    toast.show();
                }
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Please provide non empty fields", Toast.LENGTH_SHORT);
                toast.show();
            }

        });

    }
}