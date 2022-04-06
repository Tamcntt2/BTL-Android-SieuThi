package com.example.sieuthi.product;

import java.sql.Time;
import java.time.LocalDate;

public class Product {

    private String id;
    private int imgPhoto;
    private int price;
    private String name;
    private int quantity;

    public Product(String id, int imgPhoto, int price, String name, int quantity) {
        this.id = id;
        this.imgPhoto = imgPhoto;
        this.price = price;
        this.name = name;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getImgPhoto() {
        return imgPhoto;
    }

    public void setImgPhoto(int imgPhoto) {
        this.imgPhoto = imgPhoto;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }
}
