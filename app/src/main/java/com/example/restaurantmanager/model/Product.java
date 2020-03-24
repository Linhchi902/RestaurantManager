package com.example.restaurantmanager.model;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("prCode")
    private int prID;
    @SerializedName("prName")
    private String prName;

    @SerializedName("prPrice")
    private int prPrice;

    @SerializedName("prAmount")
    private int prAmount;

    @SerializedName("prUnit")
    private String prUnit;

    @SerializedName("prTime")
    private int prTime;

    @SerializedName("prImage")
    private String prImage;

    @SerializedName("menuID")
    private String menuID;

    public int getPrID() {
        return prID;
    }

    public void setPrID(int prID) {
        this.prID = prID;
    }

    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    public int getPrPrice() {
        return prPrice;
    }

    public void setPrPrice(int prPrice) {
        this.prPrice = prPrice;
    }

    public int getPrAmount() {
        return prAmount;
    }

    public void setPrAmount(int prAmount) {
        this.prAmount = prAmount;
    }

    public int getPrTime() {
        return prTime;
    }

    public void setPrTime(int prTime) {
        this.prTime = prTime;
    }

    public String getPrUnit() {
        return prUnit;
    }

    public void setPrUnit(String prUnit) {
        this.prUnit = prUnit;
    }

    public String getPrImage() {
        return prImage;
    }

    public void setPrImage(String prImage) {
        this.prImage = prImage;
    }

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }
}
