package com.example.duan1_wd18301_n3.Models;

public class ProductStatistic {
    private String productName;
    private int quantity;
    private double totalSales;

    public ProductStatistic(String productName, int quantity, double totalSales) {
        this.productName = productName;
        this.quantity = quantity;
        this.totalSales = totalSales;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }
}
