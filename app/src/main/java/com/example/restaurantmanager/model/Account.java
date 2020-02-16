package com.example.restaurantmanager.model;

import com.google.gson.annotations.SerializedName;

public class Account {
    @SerializedName("acName")
    private String acName;

    @SerializedName("acPassword")
    private String acPassword;

    @SerializedName("acGrant")
    private int acGrant;

    public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName;
    }

    public String getAcPassword() {
        return acPassword;
    }

    public void setAcPassword(String acPassword) {
        this.acPassword = acPassword;
    }

    public int getAcGrant() {
        return acGrant;
    }

    public void setAcGrant(int acGrant) {
        this.acGrant = acGrant;
    }
}
