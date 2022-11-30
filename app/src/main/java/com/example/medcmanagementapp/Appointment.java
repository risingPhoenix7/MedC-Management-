package com.example.medcmanagementapp;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.util.UUID;

class Appointment {


    private final UUID appointmentID;
    private final String bitsID;
    private final UUID doctorID;
    private final LocalDateTime timestamp;
    private boolean isCompleted;

    public boolean isCompleted() {
        return isCompleted;
    }
    public String getAdminCompleteButtonText() {
        return isCompleted() ? "COMPLETED" : "COMPLETE";
    }

    public String getBitsID() {
        return bitsID;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getTimeStamp() {
        return timestamp.getDayOfMonth() + "th " + timestamp.getMonth() + "\n" + timestamp.getHour() + ":" + timestamp.getMinute();
    }

    public String getAppointmentID() {
        return appointmentID.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getStatus() {
        if (timestamp.compareTo(LocalDateTime.now()) > 0) {
            return "PENDING";
        } else if (isCompleted) {
            return "COMPLETED";
        } else {
            return "WAITING";
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setCompleted() {
        if (timestamp.compareTo(LocalDateTime.now()) < 0) {
            isCompleted = true;
        }
    }

    public Appointment(String bitsID, UUID doctorID, LocalDateTime timestamp) {
        this.appointmentID = UUID.randomUUID();
        this.bitsID = bitsID;
        this.doctorID = doctorID;
        this.timestamp = timestamp;
        this.isCompleted = false;
    }

}
    
    
    
    
