package com.example.medcmanagementapp;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DataClass {
    static class CustomDoctorTime {
        public CustomDoctorTime(Doctor doctor, LocalDateTime localDateTime) {
            this.doctor = doctor;
            this.localDateTime = localDateTime;
        }

        Doctor doctor;
        LocalDateTime localDateTime;

    }

    static ArrayList<Student> userList = new ArrayList<>();
    static ArrayList<Doctor> noticeBoard = new ArrayList<>();
    static ArrayList<Medicine> medicineList = new ArrayList<>();
    static ArrayList<Appointment> appointmentList = new ArrayList<>();
    static ArrayList<Order> orderList = new ArrayList<>();
    static int totalRevenueDue = 0;
    static int totalRevenueEarned = 0;

    static ArrayList<Order> getStudentOrders(String studentid) {
        ArrayList<Order> a = new ArrayList<>();
        for (Order order : orderList) {
            if (order.getBitsID().equals(studentid)) {
                a.add(order);
            }
        }
        return a;
    }

    static boolean buyMedicine(Medicine m, int quantity, boolean isCash, String studentid) {

        if (checkIfStudentExists(studentid)) {
            if (quantity > m.getDefQuantity()) {
                return false;
            }
            orderList.add(new Order(studentid, m.getItemName(), quantity, isCash ? "CASH" : "LATER"));
            for (Medicine medicine : medicineList) {
                if (medicine.getItemName().equals(m.getItemName())) {
                    medicine.setDefQuantity(medicine.getDefQuantity() - quantity);
                    if (isCash) {
                        totalRevenueEarned += m.getPrice() * quantity;
                    } else {
                        totalRevenueDue += m.getPrice() * quantity;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    static ArrayList<String> getMedicineList() {
        ArrayList<String> a = new ArrayList<String>();
        for (Medicine medicine : medicineList) {
            a.add(medicine.getItemName());
        }
        return a;
    }

    static boolean addMedicine(Medicine a) {
        for (Medicine m : medicineList) {
            if (a.getItemName().equals(m.getItemName())) {
                return false;
            }
        }
        medicineList.add(a);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    static LocalDateTime checkForAppointment(String a, String bitsID) {
        System.out.println(a + bitsID);

        ArrayList<Doctor> doctorsOfRequiredType = new ArrayList<Doctor>();
        for (Doctor d : noticeBoard) {
            if (d.getConsultation().equals(a)) {
                doctorsOfRequiredType.add(d);
            }
        }
        CustomDoctorTime customDoctorTime = getRequiredDoctorAppointment(doctorsOfRequiredType);
        System.out.println("JIJIJIIJ");
        System.out.println(customDoctorTime.localDateTime);
        System.out.println(customDoctorTime.doctor);
        if (customDoctorTime != null || customDoctorTime.localDateTime == null) {
            DataClass.appointmentList.add(new Appointment(bitsID, customDoctorTime.doctor.getDoctorID(), customDoctorTime.localDateTime));
            return customDoctorTime.localDateTime;
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    static CustomDoctorTime getRequiredDoctorAppointment(ArrayList<Doctor> doctorsOfRequiredType) {
        System.out.println("KOKOK");
        System.out.println(doctorsOfRequiredType.size());
        if (doctorsOfRequiredType.size() > 0) {
            Doctor d;
            if (doctorsOfRequiredType.size() == 1) {
                d = doctorsOfRequiredType.get(0);
                System.out.println("OOKOKO");
                System.out.println(d.getDoctorName());
                LocalDateTime localDateTime = d.getAppointmentSlot();
                return new DataClass.CustomDoctorTime(d, localDateTime);
            } else {
                d = randomlyAssignDoctor(doctorsOfRequiredType);
                LocalDateTime appointmentSlot = d.getAppointmentSlot();
                if (appointmentSlot == null) {
                    doctorsOfRequiredType.remove(d);
                    return getRequiredDoctorAppointment(doctorsOfRequiredType);
                } else {
                    return new CustomDoctorTime(d, appointmentSlot);
                }
                // d.randomlyAssignTimeAmongAvailableTimes();
            }
        }
        return null;
    }

    static Doctor randomlyAssignDoctor(ArrayList<Doctor> a) {
        int index = (int) (Math.random() * (a.size() - 1));
        return a.get(index);
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

    static String validateUser(Student a) {
        if (checkIfStudentExists(a.getBitsID())) {
            return "User already exists. Please login";
        } else {
            try {
                if ((int) (Math.log10(Integer.parseInt(a.getBitsID()))) == 7) {
                    String[] b = (a.getMailID().split("@"));
                    if (b.length != 2) {
                        return "Invalid mail id";
                    } else {
                        if (b[1].equals("pilani.bits-pilani.ac.in")) {
                            if ((int) (Math.log10(Integer.parseInt(a.getBitsID()))) == 97) {
                                userList.add(a);
                                return null;
                            } else {
                                return "Invalid mobile number";
                            }
                        } else {
                            return "Enter a valid BITS EMAIL";
                        }
                    }
                } else {
                    return "Invalid Bits ID. Enter a 8 digit";
                }
            } catch (Exception e) {
                return "Something went wrong";
            }

        }
    }

    static boolean checkIfStudentExists(String studentID) {
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

    static ArrayList<Order> getOrderList(String studentid) {
        ArrayList<Order> a = new ArrayList<Order>();
        for (Order o : orderList) {
            if (o.getBitsID().equals(studentid)) {
                a.add(o);
            }
        }
        return a;
    }
}



