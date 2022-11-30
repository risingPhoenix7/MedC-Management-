package com.example.medcmanagementapp;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class DataClass {
    static ArrayList<Student> userList = new ArrayList<Student>();
    static ArrayList<Doctor> noticeBoard = new ArrayList<Doctor>();
    static ArrayList<Medicine> medicineList = new ArrayList<Medicine>();
    static ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
    static ArrayList<Order> orderList = new ArrayList<Order>();
    static int totalRevenueDue = 0;
    static int totalRevenueEarned = 0;

    @RequiresApi(api = Build.VERSION_CODES.O)
    static boolean checkForAppointment(String a) {
        ArrayList<Doctor> doctorsOfRequiredType = new ArrayList<Doctor>();
        for (Doctor d : noticeBoard) {
            if (d.getConsultation().equals(a)&&d.checkIfDoctorFree()) {
                doctorsOfRequiredType.add(d);
            }

        }
        //TODO: randomly assign a doctor in a randomly assigned time.
return true;
    }

    static String getTotalRevenue() {
        return totalRevenueDue + " Due\n" + totalRevenueEarned + " Earned";
    }

    static String[] getDoctorSpecialisations() {
        ArrayList<String> a = new ArrayList<String>();
        for (Doctor d : noticeBoard) {
            a.add(d.getConsultation());

        }
        System.out.println("HUHUYYUY");
        a.add("GENERAL");
        a.add("PKIK");
        a.add("POIKJ");
        System.out.println(a.size());
        String[] b = new String[a.size()];
        a.toArray(b);
        System.out.println("HUHUHUH");
        System.out.println(b.length);
        return b;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    static void setDoctorFields(int position, String name, String consultation, int startHour, int startMinute, int stopHour, int stopMinute) {
        noticeBoard.get(position).setDoctorName(name);
        noticeBoard.get(position).setConsultation(consultation);
        noticeBoard.get(position).setAvailableDateTimeStart(startHour, startMinute);
        noticeBoard.get(position).setAvailableDateTimeEnd(stopHour, stopMinute);
    }

    static void setMedicineFields(int position, String name, float price, int quantity) {
        medicineList.get(position).setItemName(name);
        medicineList.get(position).setPrice(price);
        medicineList.get(position).setDefQuantity(quantity);

    }

    static boolean validateUser(Student a) {
        if (checkIfStudentExists(a.getBitsID())) {
            return false;
        } else {
            userList.add(a);
            return true;
        }
    }

    static boolean checkIfStudentExists(Integer studentID) {
        for (Student s : userList) {
            if (s.getBitsID() == studentID) {
                return true;
            }
        }
        return false;
    }

    static ArrayList<Appointment> getAppointmentList(String studentid) {
        ArrayList<Appointment> a = new ArrayList<Appointment>();
        for (Appointment s : appointmentList) {
            if (s.getBitsID().equals(studentid)) {
                a.add(s);
            }
        }
        return a;
    }
}
