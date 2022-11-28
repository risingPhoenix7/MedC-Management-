package com.example.medcmanagementapp;

class Order
{
    private long bitsID;
    private long orderItemID;
    private int orderQuantity;
    private String paymentMethod;
    long getBitsID() { return bitsID; }
    void setBitsID(long bitsID) { this.bitsID = bitsID; }
    long getOrderItemID() { return orderItemID; }
    void setOrderItemID(long orderItemID) { this.orderItemID = orderItemID; }
    int getOrderQuantity() { return orderQuantity; }
    void setOrderQuantity(int orderQuantity) { this.orderQuantity = orderQuantity; }
    String getPaymentMethod() { return paymentMethod; }
    void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}
