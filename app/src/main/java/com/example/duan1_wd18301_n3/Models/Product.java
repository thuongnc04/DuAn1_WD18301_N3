package com.example.duan1_wd18301_n3.Models;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String categoryName;
    private String name;
    private int quantity;
    private int price;
    private byte[] image;


    public Product() {
        super();
    }

    public Product(String name, int quantity) {
        super();
        this.name = name;
        this.quantity = quantity;
    }

    public Product(String name, String categoryName, int quantity, int price, byte[] image) {
        super();
        this.name = name;
        this.categoryName = categoryName;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }

    public Product(int id, String categoryName, String name, int quantity, int price, byte[] image) {
        this.id = id;
        this.categoryName = categoryName;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


}
