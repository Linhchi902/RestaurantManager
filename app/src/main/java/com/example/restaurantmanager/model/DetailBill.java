package com.example.restaurantmanager.model;

import com.google.gson.annotations.SerializedName;

public class DetailBill {
    @SerializedName("bdId")
    private String bdId;

    @SerializedName("bID")
    private String bID;

    @SerializedName("prCode")
    private int prCode;

    @SerializedName("disID")
    private String disID;

    @SerializedName("bdPrice")
    private String bdPrice;

    @SerializedName("bdAmount")
    private int bdAmount;

    public DetailBill(int prCode, String disID, String bdPrice, int bdAmount) {
        this.prCode = prCode;
        this.disID = disID;
        this.bdPrice = bdPrice;
        this.bdAmount = bdAmount;
    }

    public String getBdId() {
        return bdId;
    }

    public void setBdId(String bdId) {
        this.bdId = bdId;
    }

    public String getbID() {
        return bID;
    }

    public void setbID(String bID) {
        this.bID = bID;
    }

    public int getPrCode() {
        return prCode;
    }

    public void setPrCode(int prCode) {
        this.prCode = prCode;
    }

    public String getDisID() {
        return disID;
    }

    public void setDisID(String disID) {
        this.disID = disID;
    }

    public String getBdPrice() {
        return bdPrice;
    }

    public void setBdPrice(String bdPrice) {
        this.bdPrice = bdPrice;
    }

    public int getBdAmount() {
        return bdAmount;
    }

    public void setBdAmount(int bdAmount) {
        this.bdAmount = bdAmount;
    }
}
