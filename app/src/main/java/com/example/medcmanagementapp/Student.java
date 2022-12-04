package com.example.medcmanagementapp;

public class Student {
    private String name;
    private String bitsID;
    private String mailID;
    private String number;

    public String getName() {
        return name;
    }

    public String getBitsID() {
        return bitsID;
    }

    public void setBitsID(String bitsID) {
        this.bitsID = bitsID;
    }

    Student(String name, String bitsID, String mailID, String number) {
        this.name = name;
        this.bitsID = bitsID;
        this.mailID = mailID;
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setMailID(String mailID) {
        this.mailID = mailID;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMailID() {
        return mailID;
    }

    public String getNumber() {
        return number;
    }
}
