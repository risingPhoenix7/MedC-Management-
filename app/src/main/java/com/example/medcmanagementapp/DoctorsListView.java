package com.example.medcmanagementapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medcmanagementapp.databinding.ActivityDoctorsListViewBinding;

public class DoctorsListView extends AppCompatActivity {
    ActivityDoctorsListViewBinding binding;
    Button addDoctorsPage;
    DoctorListAdapter listAdapter;
    boolean isEditable = true;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityDoctorsListViewBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        addDoctorsPage = findViewById(R.id.button4);
        listAdapter = new DoctorListAdapter(DoctorsListView.this, DataClass.noticeBoard);
        binding.doctorslistid.setAdapter(listAdapter);
        binding.doctorslistid.setClickable(true);
        Intent intent = this.getIntent();
        if (intent.getStringExtra("Studentid") != null) {
            addDoctorsPage.setEnabled(false);
            addDoctorsPage.setVisibility(View.GONE);
            isEditable = false;
        }
        if (isEditable) {
            binding.doctorslistid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(DoctorsListView.this, EditDoctorsActivity.class);
                    intent.putExtra("position", Integer.toString(i));
                    startActivity(intent);
                }
            });
        }
        addDoctorsPage.setOnClickListener(view -> {
            Intent launchActivity1 = new Intent(DoctorsListView.this, AddDoctorsActivity.class);
            startActivity(launchActivity1);
        });
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        listAdapter.notifyDataSetChanged();
        super.onResume();
    }
}
