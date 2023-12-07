package com.example.duan1_wd18301_n3.Models;

import java.io.Serializable;

public class BillStatistic implements Serializable {
    private int idBill;
    private String userName;
    private float totalBill;
    private String description;
    private String datetime;

    public BillStatistic() {
    }

    public BillStatistic(int idBill, String userName, float totalBill, String description, String datetime) {
        this.idBill = idBill;
        this.userName = userName;
        this.totalBill = totalBill;
        this.description = description;
        this.datetime = datetime;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public float getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(float totalBill) {
        this.totalBill = totalBill;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
