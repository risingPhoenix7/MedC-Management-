package com.example.medcmanagementapp;

import java.util.UUID;

class Medicine {

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    private UUID itemID;
    private String itemName;
    private float price;
    private int defQuantity;

    Medicine(String itemName, float price, int defQuantity) {
        this.itemID = UUID.randomUUID();
        this.itemName = itemName;
        this.price = price;
        this.defQuantity = defQuantity;
    }

    String getItemID() {
        return itemID.toString();
    }

    String getItemName() {
        return itemName;
    }

    float getPrice() {
        return price;
    }

    String getStringPrice() {
        return Float.toString(price);
    }
    String getStringQuantity() {
        return Integer.toString(defQuantity);
    }

    void setDefQuantity(int defQuantity) {
        this.defQuantity = defQuantity;
    }

    int getDefQuantity() {
        return defQuantity;
    }
}
