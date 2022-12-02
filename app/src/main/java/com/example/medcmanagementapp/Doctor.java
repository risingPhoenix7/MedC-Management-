package com.example.medcmanagementapp;

import static java.time.temporal.ChronoUnit.MINUTES;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

class Doctor {
    private UUID doctorID;
    private String doctorName;
    private String consultation;
    private LocalDateTime availableDateTimeStart;
    private LocalDateTime availableDateTimeEnd;
    private ArrayList<LocalDateTime> alreadyBookedStartTimes = new ArrayList<LocalDateTime>();

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


    @RequiresApi(api = Build.VERSION_CODES.O)
    LocalDateTime checkIfDoctorFree() {
        if (alreadyBookedStartTimes.size() == 0) {
            if (MINUTES.between(availableDateTimeStart, availableDateTimeEnd) > 10) {
                return availableDateTimeStart;
                // if doctors available time is less than 10.
            } else {
                return null;
            }
        } else {
            if (alreadyBookedStartTimes.get(0).equals(availableDateTimeStart)) {
                LocalDateTime a = alreadyBookedStartTimes.get(alreadyBookedStartTimes.size() - 1).plusMinutes(10);
                if (a.compareTo(availableDateTimeEnd) <= 0) {
                    return a;
                } else {
                    return null;
                }
            } else {
                if (MINUTES.between(availableDateTimeStart, alreadyBookedStartTimes.get(0)) > 10) {
                    return alreadyBookedStartTimes.get(0).minusMinutes(10);

                } else {
                    return null;
                }
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private LocalDateTime checkIfTimeIsFuture(LocalDateTime a) {
        if (a.compareTo(LocalDateTime.now()) <= 0) {
            return null;
        } else {
            return a;
        }
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
