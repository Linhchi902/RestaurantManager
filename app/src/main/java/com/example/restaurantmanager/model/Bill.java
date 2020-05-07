package com.example.restaurantmanager.model;

import com.google.gson.annotations.SerializedName;

public class Bill {
    @SerializedName("bID")
    private String billID;

    @SerializedName("billDateCreate")
    private String dateCreate;

    @SerializedName("ctId")
    private int customerID;

    @SerializedName("empCode")
    private int empCode;

    public Bill(String dateCreate, int customerID, int empCode) {
        this.dateCreate = dateCreate;
        this.customerID = customerID;
        this.empCode = empCode;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getEmpCode() {
        return empCode;
    }

    public void setEmpCode(int empCode) {
        this.empCode = empCode;
    }
}
