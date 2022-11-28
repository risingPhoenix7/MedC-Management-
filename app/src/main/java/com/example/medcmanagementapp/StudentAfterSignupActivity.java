package com.example.medcmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StudentAfterSignupActivity extends AppCompatActivity {
    int studentid;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_after_signup);
        Intent intent = this.getIntent();
        try {
//            System.out.println("fjnfjnfnjf");
//            System.out.println(intent.getExtras().get("studentid"));
//            studentid=intent.getExtras().get("studentid").toString();
//            studentid = (intent.getStringExtra("studentid"));
            studentid = intent.getIntExtra("studentid", 0);
        } catch (Exception e) {
            studentid = 0;
        }
        name = findViewById(R.id.textView23);
        System.out.println(studentid);
        System.out.println("HUHUH");
        name.setText(Integer.toString(studentid));
    }
}