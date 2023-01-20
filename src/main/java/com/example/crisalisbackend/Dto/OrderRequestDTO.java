package com.example.crisalisbackend.Dto;

import java.util.ArrayList;
import java.util.Map;

public class OrderRequestDTO {
    private int idOrder;
    private int idPerson;
    private int idCompany;
    private ArrayList<Map<String, Object>> products;
    private ArrayList<Integer> services;
    private int quantity;
    
    public OrderRequestDTO() {
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public int getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    }

    public ArrayList<Map<String, Object>> getProducts() {
        return products;
    }

    public ArrayList<Integer> getServices() {
        return services;
    }

    public void setProducts(ArrayList<Map<String, Object>> products) {
        this.products = products;
    }

    public void setServices(ArrayList<Integer> services) {
        this.services = services;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
