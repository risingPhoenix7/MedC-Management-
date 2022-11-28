
package com.example.medcmanagementapp;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class AppointmentListAdapter extends ArrayAdapter<Appointment> {
    public AppointmentListAdapter(Context context, ArrayList<Appointment> appointments) {
        super(context, R.layout.activity_single_appointment, appointments);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Appointment appointment = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_single_appointment, parent, false);
        }
        TextView patientID = convertView.findViewById(R.id.textView19);
        Button completeAppointment = convertView.findViewById(R.id.button10);
        TextView appointmentID = convertView.findViewById(R.id.textView21);
        TextView status = convertView.findViewById(R.id.textView18);
        TextView timeString = convertView.findViewById(R.id.textView16);

        patientID.setText(appointment.getBitsID());
        completeAppointment.setText(appointment.isCompleted() ? "COMPLETED" : "COMPLETE");
        appointmentID.setText(appointment.getAppointmentID());
        status.setText(appointment.getStatus());
        timeString.setText(appointment.getTimeStamp());
        completeAppointment.setOnClickListener(view -> {
            appointment.setCompleted();
            completeAppointment.setText(appointment.isCompleted() ? "COMPLETED" : "COMPLETE");
        });
        status.setText(appointment.getStatus());
        return convertView;
    }
}
