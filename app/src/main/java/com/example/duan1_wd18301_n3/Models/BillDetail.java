package com.example.duan1_wd18301_n3.Models;

public class BillDetail {
    private int id;
    private int bill_id;
    private String product_name;
    private int quantity;
    private int price;

    public BillDetail() {
    }

    public BillDetail(int id, int bill_id, String product_name, int quantity, int price) {
        this.id = id;
        this.bill_id = bill_id;
        this.product_name = product_name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BillDetail{" +
                "id=" + id +
                ", bill_id=" + bill_id +
                ", product_name='" + product_name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
