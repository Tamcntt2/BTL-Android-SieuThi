package com.example.sieuthi.customer;

import com.example.sieuthi.product.Product;

import java.util.List;

public class Customer {

    private String username;
    private String password;
    private List<Product> listProductInCart;

    public Customer(String username, String password, List<Product> listProductInCart) {
        this.username = username;
        this.password = password;
        this.listProductInCart = listProductInCart;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Product> getListProductInCart() {
        return listProductInCart;
    }

    public void setListProductInCart(List<Product> listProductInCart) {
        this.listProductInCart = listProductInCart;
    }
}
