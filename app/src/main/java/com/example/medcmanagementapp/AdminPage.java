package com.example.medcmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminPage extends AppCompatActivity {
    Button goToDoctorsPage;
    Button viewInventory;
    Button viewAppointments;
    TextView showTotalRevenue;
    Button goToOrdersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        Intent intent = this.getIntent();

        goToDoctorsPage = findViewById(R.id.button2);
        viewInventory = findViewById(R.id.button3);
        viewAppointments = findViewById(R.id.button5);
        showTotalRevenue = findViewById(R.id.textView2);
        showTotalRevenue.setText(DataClass.getTotalRevenue());
        goToOrdersList = findViewById(R.id.button16);
        if (intent.getStringExtra("isMedicalShopOwner") != null && intent.getStringExtra("isMedicalShopOwner").equals("true")) {
            viewAppointments.setEnabled(false);
            viewAppointments.setVisibility(View.GONE);
            goToDoctorsPage.setEnabled(false);
            goToDoctorsPage.setVisibility(View.GONE);
        }
        goToOrdersList.setOnClickListener(view -> {
            Intent launchActivity1 = new Intent(AdminPage.this, ViewOrdersActivity.class);
            startActivity(launchActivity1);
        });
        goToDoctorsPage.setOnClickListener(view -> {
            Intent launchActivity1 = new Intent(AdminPage.this, DoctorsListView.class);
            startActivity(launchActivity1);
        });
        viewInventory.setOnClickListener(view -> {
            Intent launchActivity1 = new Intent(AdminPage.this, ViewInventoryActivity.class);
            startActivity(launchActivity1);
        });
        viewAppointments.setOnClickListener(view -> {
            Intent launchActivity1 = new Intent(AdminPage.this, AppointmentListActivity.class);
            startActivity(launchActivity1);
        });
    }
}