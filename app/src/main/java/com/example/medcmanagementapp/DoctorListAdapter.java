package com.example.medcmanagementapp;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class DoctorListAdapter extends ArrayAdapter<Doctor> {
    public DoctorListAdapter(Context context, ArrayList<Doctor> doctorArrayList) {
        super(context, R.layout.activity_single_doctor_view, doctorArrayList);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Doctor doctor = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_single_doctor_view, parent, false);
        }
        TextView doctorname = convertView.findViewById(R.id.editTextTextPersonName7);
        TextView consultation = convertView.findViewById(R.id.editTextTextPersonName8);
        TextView id = convertView.findViewById(R.id.textView3);
        TextView visitingHours = convertView.findViewById(R.id.editTextTextPersonName10);
        doctorname.setText(doctor.getDoctorName());
        consultation.setText(doctor.getConsultation());
        id.setText(doctor.getDoctorID().toString());
        visitingHours.setText(doctor.getAvailableTimeSlot());

        return convertView;
    }
}
