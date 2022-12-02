package com.example.medcmanagementapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medcmanagementapp.databinding.ActivityViewOrdersBinding;

public class ViewOrdersActivity extends AppCompatActivity {
    ActivityViewOrdersBinding binding;
    OrdersListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = this.getIntent();
        String studentid = intent.getStringExtra("Studentid");

        listAdapter = new OrdersListAdapter(ViewOrdersActivity.this, studentid == null ? DataClass.orderList : DataClass.getStudentOrders(studentid));
        binding.viewInventoryList.setAdapter(listAdapter);

    }

    @Override
    protected void onResume() {
        listAdapter.notifyDataSetChanged();
        super.onResume();
    }

}