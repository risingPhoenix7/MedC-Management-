package com.example.medcmanagementapp;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
class Appointment
{
    private String bitsID;
    private String doctorID;
    private final LocalDateTime timestamp;
    private LocalDateTime appointmentDate;
    private LocalDateTime appointmentTime;

    @RequiresApi(api = Build.VERSION_CODES.O)
    Appointment() { this.timestamp = LocalDateTime.now(); }
    void setBitsID(String bitsID) { this.bitsID = bitsID; }
    void setDoctorID(String doctorID) { this.doctorID = doctorID; }
    void setAppointmentDate(LocalDateTime d) { appointmentDate = d; }
    void setAppointmentTime(LocalDateTime d) { appointmentTime = d; }
    String getBITS_ID() { return bitsID; }
    String getDoctor_ID() { return doctorID; }
    LocalDateTime getTimestamp() { return timestamp; }
    LocalDateTime getAppointmentDate() { return appointmentDate; }
    LocalDateTime getAppointmentTime() { return appointmentTime; }

}
    
    
    
    
