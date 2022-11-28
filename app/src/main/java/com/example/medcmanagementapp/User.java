package com.example.medcmanagementapp;

class User
{
    private String name;
    private long bitsID;
    private String mailID;
    private long number;
    void setName(String name) {
        this.name = name;
    }
    String getName() {
        return name;
    }
    void setBitsID(long bitsID) {
        this.bitsID = bitsID;
    }
    long getBitsID() {
        return bitsID;
    }
    void setMailID(String mailID) {
        this.mailID = mailID;
    }
    String getMailID() {
        return mailID;
    }
    void setNumber(long number) {
        this.number = number;
    }
    long getNumber() { return number; }
}
