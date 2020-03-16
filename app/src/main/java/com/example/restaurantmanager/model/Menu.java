package com.example.restaurantmanager.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Menu {

    @SerializedName("menuID")
    @Expose
    private String menuID;
    @SerializedName("menuName")
    @Expose
    private String menuName;
    @SerializedName("menuIDParent")
    @Expose
    private String menuIDParent;

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuIDParent() {
        return menuIDParent;
    }

    public void setMenuIDParent(String menuIDParent) {
        this.menuIDParent = menuIDParent;
    }

}
