package com.example.medcmanagementapp;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataClass {

    static ArrayList<Student> userList = new ArrayList<>();
    static ArrayList<Doctor> noticeBoard = new ArrayList<>();
    static ArrayList<Medicine> medicineList = new ArrayList<>();
    static ArrayList<Appointment> appointmentList = new ArrayList<>();
    static ArrayList<Order> orderList = new ArrayList<>();
    static int totalRevenueDue = 0;
    static int totalRevenueEarned = 0;

    static Appointment checkandCreateAppointment(LocalDateTime localDateTime, Integer indexOfDoctorInList, String studentID) {
        if(noticeBoard.get(indexOfDoctorInList).getAlreadyBookedStartTimes().contains(localDateTime)){
            return null;
        }
        noticeBoard.get(indexOfDoctorInList).addAlreadyBookedStartTime(localDateTime);
        Appointment appointment = new Appointment(studentID, noticeBoard.get(indexOfDoctorInList).getDoctorID(), localDateTime);
        appointmentList.add(appointment);
        return appointment;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    static ArrayList<LocalDateTime> getAvailableTimes(Integer indexInDoctorsList, String selectedDay) {
        System.out.println("DWIUKJDHKWJDHDKJWB");
        ArrayList<LocalDateTime> arrayList = new ArrayList<LocalDateTime>();
        ArrayList<LocalDateTime> localDateTimeArrayList = new ArrayList<LocalDateTime>();
        if (noticeBoard.size() == 0) {
            return arrayList;
        }
        Doctor d = noticeBoard.get(indexInDoctorsList);
        if (d.checkIfDayIsAvailable(DayOfWeek.valueOf(selectedDay))) {
            return d.getLocalDateTimeStarts(DayOfWeek.valueOf(selectedDay));
        } else {
            return localDateTimeArrayList;
        }
    }


//    static ArrayList<LocalDateTime> getAvailableTimeSlotsInStringFormat(String selectedSpecialisation, String selectedDayOfWeek) {
//        ArrayList<LocalDateTime> localDateTimeArrayList = new ArrayList<>();
//        if (selectedSpecialisation == null || selectedDayOfWeek == null) {
//            return localDateTimeArrayList;
//        } else {
//
//        }
//    }

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
//    static LocalDateTime checkForAppointment(String a, String bitsID) {
//        System.out.println(a + bitsID);
//
//        ArrayList<Doctor> doctorsOfRequiredType = new ArrayList<Doctor>();
//        for (Doctor d : noticeBoard) {
//            if (d.getConsultation().equals(a)) {
//                doctorsOfRequiredType.add(d);
//            }
//        }
//        CustomDoctorTime customDoctorTime = getRequiredDoctorAppointment(doctorsOfRequiredType);
//        System.out.println("JIJIJIIJ");
//        System.out.println(customDoctorTime.localDateTime);
//        System.out.println(customDoctorTime.doctor);
//        if (customDoctorTime != null || customDoctorTime.localDateTime == null) {
//            DataClass.appointmentList.add(new Appointment(bitsID, customDoctorTime.doctor.getDoctorID(), customDoctorTime.localDateTime));
//            return customDoctorTime.localDateTime;
//        }
//        return null;
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    static CustomDoctorTime getRequiredDoctorAppointment(ArrayList<Doctor> doctorsOfRequiredType) {
//        System.out.println("KOKOK");
//        System.out.println(doctorsOfRequiredType.size());
//        if (doctorsOfRequiredType.size() > 0) {
//            Doctor d;
//            if (doctorsOfRequiredType.size() == 1) {
//                d = doctorsOfRequiredType.get(0);
//                System.out.println("OOKOKO");
//                System.out.println(d.getDoctorName());
//                LocalDateTime localDateTime = d.getAppointmentSlot();
//                return new DataClass.CustomDoctorTime(d, localDateTime);
//            } else {
//                d = randomlyAssignDoctor(doctorsOfRequiredType);
//                LocalDateTime appointmentSlot = d.getAppointmentSlot();
//                if (appointmentSlot == null) {
//                    doctorsOfRequiredType.remove(d);
//                    return getRequiredDoctorAppointment(doctorsOfRequiredType);
//                } else {
//                    return new CustomDoctorTime(d, appointmentSlot);
//                }
//                // d.randomlyAssignTimeAmongAvailableTimes();
//            }
//        }
//        return null;
//    }

//    static Doctor randomlyAssignDoctor(ArrayList<Doctor> a) {
//        int index = (int) (Math.random() * (a.size() - 1));
//        return a.get(index);
//    }

    static String getTotalRevenue() {
        return totalRevenueDue + " Due\n" + totalRevenueEarned + " Earned";
    }

    static String[] getDoctorAvailableDays(Integer position) {
        System.out.println("LDFKJDLKJD");
        System.out.println(noticeBoard.size());
        System.out.println(position);
        if (position != null && noticeBoard.size() != 0 && position < noticeBoard.size() && position >= 0) {
            return noticeBoard.get(position).getDaysOfWeeksArray();
        } else {
            return new String[0];
        }
    }

    static String[] getDoctorSpecialisations() {
        ArrayList<String> a = new ArrayList<String>();
        for (Doctor d : noticeBoard) {
            a.add(d.getConsultation() + " Dr." + d.getDoctorName());
        }
        String[] b = new String[a.size()];
        b = a.toArray(b);
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

    public static boolean isValidMobileNo(String str) {
//(0/91): number starts with (0/91)
//[7-9]: starting of the number may contain a digit between 0 to 9
//[0-9]: then contains digits 0 to 9
        Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}");
//the matcher() method creates a matcher that will match the given input against this pattern
        Matcher match = ptrn.matcher(str);
//returns a boolean value
        return (match.find() && match.group().equals(str));
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
                            if (isValidMobileNo(a.getNumber())) {
                                System.out.println("KJDHKJDBJOKNWDBBDWKJWDBBJOWD");
                                userList.add(a);
                                System.out.println(userList.size());
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
            if (s.getBitsID().equals(studentID)) {
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



