package com.example.medcmanagementapp;

import java.util.UUID;

class Order {

    public String getMedicineName() {
        return medicineName;
    }

    public UUID getOrderID() {
        return orderID;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public Order(int bitsID, String medicineName, int orderQuantity, String paymentMethod) {
        this.orderID = UUID.randomUUID();
        this.bitsID = bitsID;
        this.medicineName = medicineName;
        this.orderQuantity = orderQuantity;
        this.paymentMethod = paymentMethod;
    }

    private int bitsID;
    private UUID orderID;
    private String medicineName;
    private int orderQuantity;
    private String paymentMethod;

    long getBitsID() {
        return bitsID;
    }

    void setBitsID(int bitsID) {
        this.bitsID = bitsID;
    }

    int getOrderQuantity() {
        return orderQuantity;
    }
    String getStringOrderQuantity() {
        return Integer.toString(orderQuantity);
    }

    void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    String getPaymentMethod() {
        return paymentMethod;
    }

    void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
