package com.example.medcmanagementapp;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.util.UUID;

class Doctor {
    private UUID doctorID;
    private String doctorName;
    private String consultation;
    private LocalDateTime availableDateTimeStart;
    private LocalDateTime availableDateTimeEnd;

    @RequiresApi(api = Build.VERSION_CODES.O)
    Doctor(String doctorName, String consultation, int startHour, int startMinute, int stopHour, int stopMinute) {
        this.doctorID = UUID.randomUUID();
        this.doctorName = doctorName;
        this.consultation = consultation;
        this.availableDateTimeStart = getLocalDatetime(startHour, startMinute);
        this.availableDateTimeEnd = getLocalDatetime(stopHour, stopMinute);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    LocalDateTime getLocalDatetime(int startHour, int startMinute) {
        return LocalDateTime.of(2022, 5, 5, startHour, startMinute);
    }

    UUID getDoctorID() {
        return doctorID;
    }

    void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    String getDoctorName() {
        return doctorName;
    }

    void setConsultation(String consultation) {
        this.consultation = consultation;
    }

    String getConsultation() {
        return consultation;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void setAvailableDateTimeStart(int startHour, int startMinute) {
        this.availableDateTimeStart = getLocalDatetime(startHour, startMinute);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void setAvailableDateTimeEnd(int startHour, int startMinute) {
        this.availableDateTimeEnd = getLocalDatetime(startHour, startMinute);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    String getAvailableTimeSlot() {
        String a = this.availableDateTimeStart.getHour() + ":" + this.availableDateTimeStart.getMinute() + " to " + this.availableDateTimeEnd.getHour() + ":" + this.availableDateTimeEnd.getMinute();
        return a;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    Integer getStartHour() {
        return this.availableDateTimeStart.getHour();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    Integer getStartMinute() {
        return this.availableDateTimeStart.getMinute();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    Integer getStopHour() {
        return this.availableDateTimeEnd.getHour();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    Integer getStopMinute() {
        return this.availableDateTimeEnd.getMinute();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    String getStartTime() {
        return this.availableDateTimeStart.getHour() + ":" + this.availableDateTimeStart.getMinute();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    String getStopTime() {
        return this.availableDateTimeEnd.getHour() + ":" + this.availableDateTimeEnd.getMinute();
    }
}
