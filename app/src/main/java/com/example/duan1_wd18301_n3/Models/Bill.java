package com.example.duan1_wd18301_n3.Models;

public class Bill {
    private int id;
    private int user_id;
    private int total_price;
    private String description;
    private String date_created;

    public Bill() {
    }

    public Bill(int id, int user_id, int total_price, String description, String date_created) {
        this.id = id;
        this.user_id = user_id;
        this.total_price = total_price;
        this.description = description;
        this.date_created = date_created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", total_price=" + total_price +
                ", description='" + description + '\'' +
                ", date_created='" + date_created + '\'' +
                '}';
    }
}
