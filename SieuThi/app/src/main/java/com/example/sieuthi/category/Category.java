package com.example.sieuthi.category;

import com.example.sieuthi.product.Product;

import java.util.List;

public class Category {

    private String id;
    private int imgPhoto;
    private String name;
    private List<Product> listProduct;

    public Category(String id, int imgPhoto, String name, List<Product> listProduct) {

        this.id = id;
        this.imgPhoto = imgPhoto;
        this.name = name;
        this.listProduct = listProduct;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImgPhoto() {
        return imgPhoto;
    }

    public void setImgPhoto(int imgPhoto) {
        this.imgPhoto = imgPhoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }
}
