package com.example.medcmanagementapp;

public class Student {
    private String name;
    private int bitsID;
    private String mailID;
    private String number;

    Student(String name, int bitsID, String mailID, String number) {
        this.name = name;
        this.bitsID = bitsID;
        this.mailID = mailID;
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBitsID(int bitsID) {
        this.bitsID = bitsID;
    }

    public void setMailID(String mailID) {
        this.mailID = mailID;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public int getBitsID() {
        return bitsID;
    }

    public String getMailID() {
        return mailID;
    }

    public String getNumber() {
        return number;
    }
}
