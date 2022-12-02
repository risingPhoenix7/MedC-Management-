package com.example.medcmanagementapp;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medcmanagementapp.databinding.ActivityEditDoctorsBinding;

import java.util.Locale;

public class EditDoctorsActivity extends AppCompatActivity {
    ActivityEditDoctorsBinding activityEditDoctorsBinding;
    EditText doctorNameField;
    TextView doctorID;
    EditText doctorConsultation;
    Button openStartTimePickerButton;
    Button openStopTimePickerButton;
    Button submitButton;
    Doctor doctor;
    Integer startHour, startMinute;
    Integer stopHour, stopMinute;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int position = 0;
        activityEditDoctorsBinding = ActivityEditDoctorsBinding.inflate(getLayoutInflater());
        setContentView(activityEditDoctorsBinding.getRoot());
        doctorNameField = findViewById(R.id.editDoctorNameid);
        doctorID = findViewById(R.id.iddisplaydoctor);
        doctorConsultation = findViewById(R.id.editConsultation);
        openStartTimePickerButton = findViewById(R.id.buttonstarttime);
        openStopTimePickerButton = findViewById(R.id.selectendtime);
        submitButton = findViewById(R.id.submitbutton);
        Intent intent = this.getIntent();
        if (intent != null) {
            System.out.println(intent.getStringExtra("position"));
            position = Integer.parseInt(intent.getStringExtra("position"));
            doctor = DataClass.noticeBoard.get(position);
        }
        stopHour = doctor.getStopHour();
        startHour = doctor.getStartHour();
        startMinute = doctor.getStartMinute();
        stopMinute = doctor.getStopMinute();
        doctorNameField.setText(doctor.getDoctorName());
        doctorID.setText(doctor.getDoctorID().toString());
        doctorConsultation.setText(doctor.getConsultation());
        openStartTimePickerButton.setText(doctor.getStartTime());
        openStopTimePickerButton.setText(doctor.getStopTime());
        openStopTimePickerButton.setOnClickListener(this::stopPopTimePicker);
        int finalPosition = position;
        openStartTimePickerButton.setOnClickListener(view->{
            Toast toast = Toast.makeText(getApplicationContext(), "Start time cannot be edited.", Toast.LENGTH_SHORT);
            toast.show();
        });
        submitButton.setOnClickListener(view -> {
            String name = doctorNameField.getText().toString();
            String consultation = doctorConsultation.getText().toString();
            if (!name.isEmpty() && !consultation.isEmpty()) {

                DataClass.setDoctorFields(finalPosition,
                        name,
                        consultation,
                        startHour,
                        startMinute,
                        stopHour,
                        stopMinute);
                Toast toast = Toast.makeText(getApplicationContext(), "Successfully edited doctor", Toast.LENGTH_SHORT);
                toast.show();
                finish();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Some fields are empty", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void stopPopTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                stopHour = selectedHour;
                stopMinute = selectedMinute;
                openStopTimePickerButton.setText(String.format(Locale.getDefault(), "%02d:%02d", stopHour, stopMinute));
            }
        };
        TimePickerDialog timePickerDialog2 = new TimePickerDialog(this, onTimeSetListener, stopHour == null ? 12 : stopHour, stopMinute == null ? 5 : stopMinute, true);
        timePickerDialog2.setTitle("Set Stop Time");
        timePickerDialog2.show();
    }

}