package com.example.mobdev_nhom7.models.responseObj;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DefaultItem {
    @SerializedName("result")
    @Expose
    private String res;
    @SerializedName("message")
    @Expose
    private String mes;
    public String getRes() {
        return res;
    }
    public void setRes(String res) {
        this.res = res;
    }
    public String getMes() {
        return mes;
    }
    public void setMes(String mes) {
        this.mes = mes;
    }
}
