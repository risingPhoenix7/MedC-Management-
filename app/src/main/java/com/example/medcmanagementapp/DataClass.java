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

    static String getTotalRevenue() {
        return totalRevenueDue + " Due\n" + totalRevenueEarned + " Earned";
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

    static boolean checkIfStudentExists(Integer studentID) {
        for (Student s : userList) {
            if (s.getBitsID() == studentID) {
                return true;
            }
        }
        return false;
    }
}
