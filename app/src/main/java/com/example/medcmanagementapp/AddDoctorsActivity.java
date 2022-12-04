package com.example.medcmanagementapp;

import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class AddDoctorsActivity extends AppCompatActivity {
    Integer startHour, startMinute;
    Integer stopHour, stopMinute;
    Button startTimeButton;
    Button stopTimeButton;
    Button addButton;
    EditText doctorNameInput;
    EditText doctorConsultationInput;
    RadioButton[] dayRadioButtons = new RadioButton[7];


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_doctors);
        startTimeButton = findViewById(R.id.button6);
        stopTimeButton = findViewById(R.id.button7);
        addButton = findViewById(R.id.button);
        doctorNameInput = findViewById(R.id.editDoctorname);
        doctorConsultationInput = findViewById(R.id.editTextTextPersonName33);
        dayRadioButtons[0] = findViewById(R.id.radioButton0);
        dayRadioButtons[1] = findViewById(R.id.radioButton1);
        dayRadioButtons[2] = findViewById(R.id.radioButton2);
        dayRadioButtons[3] = findViewById(R.id.radioButton3);
        dayRadioButtons[4] = findViewById(R.id.radioButton4);
        dayRadioButtons[5] = findViewById(R.id.radioButton5);
        dayRadioButtons[6] = findViewById(R.id.radioButton6);

        stopTimeButton.setOnClickListener(this::stopPopTimePicker);
        startTimeButton.setOnClickListener(this::startPopTimePicker);
        addButton.setOnClickListener(view -> {
                    String name = doctorNameInput.getText().toString();
                    String consultation = doctorConsultationInput.getText().toString();
                    String[] daysSelected = getDaysSelected();
                    if (!name.isEmpty() && !consultation.isEmpty() && startHour != null && stopHour != null && startMinute != null && stopMinute != null && daysSelected.length != 0) {
                        DataClass.noticeBoard.add(new Doctor(name, consultation, startHour, startMinute, stopHour, stopMinute, getDaysSelected()));
                        Toast toast = Toast.makeText(getApplicationContext(), "Successfully added doctor", Toast.LENGTH_SHORT);
                        toast.show();
                        finish();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Some fields are empty", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

        );
    }


    private String[] getDaysSelected() {
        ArrayList<String> listOfDaysAvailable = new ArrayList<String>();
        for (int i = 0; i < 7; i++) {
            if (dayRadioButtons[i].isChecked()) {
                listOfDaysAvailable.add(dayRadioButtons[i].getText().toString());
            }
        }
        String[] a = new String[listOfDaysAvailable.size()];
        a = listOfDaysAvailable.toArray(a);
        return a;
    }

    public void startPopTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = (timePicker, selectedHour, selectedMinute) -> {
            startHour = selectedHour;
            startMinute = selectedMinute;
            startTimeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", startHour, startMinute));
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, startHour == null ? 12 : startHour, startMinute == null ? 5 : startMinute, true);
        timePickerDialog.setTitle("Set Start Time");
        timePickerDialog.show();
    }

    public void stopPopTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = (timePicker, selectedHour, selectedMinute) -> {
            stopHour = selectedHour;
            stopMinute = selectedMinute;
            stopTimeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", stopHour, stopMinute));
        };
        TimePickerDialog timePickerDialog2 = new TimePickerDialog(this, onTimeSetListener, stopHour == null ? 12 : stopHour, stopMinute == null ? 5 : stopMinute, true);
        timePickerDialog2.setTitle("Set Stop Time");
        timePickerDialog2.show();
    }
}