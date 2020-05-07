package com.example.restaurantmanager.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Menu {

    @SerializedName("menuID")
    @Expose
    private int menuID;
    @SerializedName("menuName")
    @Expose
    private String menuName;
    @SerializedName("menuIDParent")
    @Expose
    private int menuIDParent;

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuIDParent() {
        return menuIDParent;
    }

    public void setMenuIDParent(int menuIDParent) {
        this.menuIDParent = menuIDParent;
    }
}
