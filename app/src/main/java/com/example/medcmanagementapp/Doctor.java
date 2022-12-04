package com.example.medcmanagementapp;

import static java.time.temporal.ChronoUnit.DAYS;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.UUID;

class Doctor {
    private final UUID doctorID;
    private String doctorName;
    private String consultation;
    private LocalDateTime availableDateTimeStart;
    private LocalDateTime availableDateTimeEnd;
    private ArrayList<DayOfWeek> daysAvailable = new ArrayList<DayOfWeek>();
    private ArrayList<LocalDateTime> alreadyBookedStartTimes = new ArrayList<LocalDateTime>();

    public LocalDateTime getAvailableDateTimeStart() {
        return availableDateTimeStart;
    }

    public String[] getDaysOfWeeksArray() {

        String[] a = new String[daysAvailable.size()];
        for (int i = 0; i < daysAvailable.size(); i++) {
            a[i] = daysAvailable.get(i).toString();
        }
        System.out.println(a.length);
        System.out.println("HJDBWHDBKJWDH");
        return a;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<LocalDateTime> getLocalDateTimeStarts(DayOfWeek selectedDay) {
        ArrayList<LocalDateTime> arr = new ArrayList<LocalDateTime>();
//        LocalDateTime nextSelectedDaylocalDateTime = LocalDateTime.now().with(TemporalAdjusters.nextOrSame(selectedDay));
        LocalDateTime startTime = availableDateTimeStart.with(TemporalAdjusters.nextOrSame(selectedDay));
        LocalDateTime endTime = availableDateTimeEnd.plusDays(DAYS.between(availableDateTimeStart, startTime));
        for (LocalDateTime localDateTime = startTime; localDateTime.plusMinutes(10).isBefore(endTime) || localDateTime.plusMinutes(10).isEqual(endTime); localDateTime = localDateTime.plusMinutes(10)) {
            if (checkIfAlreadyBookedSlotsComeInBetween(localDateTime)) {
                System.out.println(localDateTime);
                arr.add(localDateTime);
                alreadyBookedStartTimes.add(localDateTime);
            }
        }
        return arr;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    boolean checkIfAlreadyBookedSlotsComeInBetween(LocalDateTime localDateTime) {
        for (LocalDateTime a : alreadyBookedStartTimes) {
            System.out.println("KJLWDHKJDWBHKJDWBHDJHDWJOHDWHON");
            if (checkIfBetween(a, localDateTime) || checkIfBetween(localDateTime, a)) {
                System.out.println("Going here??");
                return false;
            }
        }
        System.out.println("Not going here?");
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    boolean checkIfBetween(LocalDateTime a, LocalDateTime b) {
        return (a.isAfter(b) || a.isEqual(b)) && a.isBefore(b.plusMinutes(10));
    }

    boolean checkIfDayIsAvailable(DayOfWeek selectedDay) {
        for (DayOfWeek s : daysAvailable) {
            if (s.equals(selectedDay)) {
                return true;
            }
        }
        return false;
    }

    String getAvailableDaysTextField() {
        if (daysAvailable.size() == 0) {
            return "Not available on any day :)";
        } else {
            StringBuilder a = new StringBuilder("Available on ");
            for (int i = 0; i < daysAvailable.size(); i++) {
                a.append(daysAvailable.get(i).name() + " ");
            }
            return a.toString();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    Doctor(String doctorName, String consultation, int startHour, int startMinute, int stopHour, int stopMinute, String[] daysAvailable) {
        this.doctorID = UUID.randomUUID();
        this.doctorName = doctorName;
        this.consultation = consultation;
        for (int i = 0; i < daysAvailable.length; i++) {
            String a = daysAvailable[i];
            if (a.equals("Monday")) {
                this.daysAvailable.add(DayOfWeek.MONDAY);
            } else if (a.equals("Sunday")) {
                this.daysAvailable.add(DayOfWeek.SUNDAY);
            } else if (a.equals("Tuesday")) {
                this.daysAvailable.add(DayOfWeek.TUESDAY);
            } else if (a.equals("Wednesday")) {
                this.daysAvailable.add(DayOfWeek.WEDNESDAY);
            } else if (a.equals("Thursday")) {
                this.daysAvailable.add(DayOfWeek.THURSDAY);
            } else if (a.equals("Friday")) {
                this.daysAvailable.add(DayOfWeek.FRIDAY);
            } else if (a.equals("Saturday")) {
                this.daysAvailable.add(DayOfWeek.SATURDAY);
            }

        }
        this.availableDateTimeStart = getLocalDatetime(startHour, startMinute);
        this.availableDateTimeEnd = getLocalDatetime(stopHour, stopMinute);
        //if the doctor is working overnight shifts
        if (this.availableDateTimeEnd.isBefore(this.availableDateTimeStart)) {
            this.availableDateTimeEnd = this.availableDateTimeEnd.plusDays(1);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    LocalDateTime getLocalDatetime(int startHour, int startMinute) {
        return LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), startHour, startMinute);
    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
//    LocalDateTime getAppointmentSlot() {
//        LocalDateTime currentTime = LocalDateTime.now();
//        if (currentTime.isAfter(availableDateTimeEnd)) {
//            System.out.println("guy");
//            return null;
//        } else if (currentTime.isAfter(availableDateTimeStart)) {
//            if (alreadyBookedStartTimes.size() == 0) {
//                System.out.println("KOI");
//                alreadyBookedStartTimes.add(currentTime);
//                return currentTime;
//            } else {
//                if (currentTime.isAfter(alreadyBookedStartTimes.get(alreadyBookedStartTimes.size() - 1).plusMinutes(10))) {
//                    System.out.println("KOIU");
//                    alreadyBookedStartTimes.add(currentTime);
//                    return currentTime;
//                } else {
//                    System.out.println("KOIUddd");
//                    LocalDateTime a = (alreadyBookedStartTimes.get(alreadyBookedStartTimes.size() - 1).plusMinutes(10));
//                    alreadyBookedStartTimes.add(a);
//                    return a;
//                }
//            }
//        } else {
//            if (alreadyBookedStartTimes.size() == 0) {
//                System.out.println("DRD");
//                if (MINUTES.between(availableDateTimeStart, availableDateTimeEnd) >= 10) {
//                    alreadyBookedStartTimes.add(availableDateTimeStart);
//                    return availableDateTimeStart;
//                } else {
//                    System.out.println("FSHSK");
//                    return null;
//                }
//            } else {
//                LocalDateTime a = alreadyBookedStartTimes.get(alreadyBookedStartTimes.size() - 1).plusMinutes(10);
//                if (MINUTES.between(a, availableDateTimeEnd) >= 10) {
//                    System.out.println("TGSFHAS");
//                    alreadyBookedStartTimes.add(a);
//                    return a;
//                } else {
//                    System.out.println("HDHD");
//                    return null;
//                }
//            }
//        }
//    }


//    @RequiresApi(api = Build.VERSION_CODES.O)
//    LocalDateTime checkIfDoctorFree() {
//        if (alreadyBookedStartTimes.size() == 0) {
//            if (MINUTES.between(availableDateTimeStart, availableDateTimeEnd) > 10) {
//                return availableDateTimeStart;
//                // if doctors available time is less than 10.
//            } else {
//                return null;
//            }
//        } else {
//            if (alreadyBookedStartTimes.get(0).equals(availableDateTimeStart)) {
//                LocalDateTime a = alreadyBookedStartTimes.get(alreadyBookedStartTimes.size() - 1).plusMinutes(10);
//                if (a.compareTo(availableDateTimeEnd) <= 0) {
//                    return a;
//                } else {
//                    return null;
//                }
//            } else {
//                if (MINUTES.between(availableDateTimeStart, alreadyBookedStartTimes.get(0)) > 10) {
//                    return alreadyBookedStartTimes.get(0).minusMinutes(10);
//
//                } else {
//                    return null;
//                }
//            }
//        }
//
//    }


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
}
