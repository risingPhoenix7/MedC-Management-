
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

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AppointmentListAdapter extends ArrayAdapter<Appointment> {
    private boolean isAdmin = false;

    public AppointmentListAdapter(Context context, ArrayList<Appointment> appointments, boolean isAdmin) {
        super(context, R.layout.activity_single_appointment, appointments);
        this.isAdmin = isAdmin;
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
        TextView doctorName = convertView.findViewById(R.id.textView20);
        if (!isAdmin) {
            completeAppointment.setEnabled(false);
            completeAppointment.setVisibility(View.GONE);
        }
        patientID.setText(appointment.getBitsID());
        completeAppointment.setText(appointment.getAdminCompleteButtonText());
        appointmentID.setText(appointment.getAppointmentID());
        status.setText(appointment.getStatus());
        doctorName.setText(appointment.getDoctorID().toString());
        timeString.setText(appointment.getTimestamp().format(DateTimeFormatter.ofPattern("HH:mm")));
        completeAppointment.setOnClickListener(view -> {
            System.out.println("Clicked");
            appointment.setCompleted();
            completeAppointment.setText(appointment.getAdminCompleteButtonText());
        });
        status.setText(appointment.getStatus());
        return convertView;
    }
}
