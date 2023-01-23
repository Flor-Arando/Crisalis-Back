package com.example.crisalisbackend.Dto;

import java.util.ArrayList;
import java.util.Map;

public class OrderDTO {
    int id;
    String company;
    String person;
    ArrayList<Map<String, Object>> services = new ArrayList<>();
    ArrayList<Map<String, Object>> products = new ArrayList<>();

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public void setCompany(String name) {
        this.company = name;
    }

    public String getCompany() {
        return company;
    }

    public void setPerson(String name) {
        this.person = name;
    }

    public String getPerson() {
        return person;
    }

    public void addService(Map<String, Object> service) {
        this.services.add(service);
    }

    public ArrayList<Map<String, Object>> getServices() {
        return services;
    }

    public void addProduct(Map<String, Object> product) {
        this.products.add(product);
    }

    public ArrayList<Map<String, Object>> getProducts() {
        return products;
    }

    /*public void addService(Map<String, Object> service) {
        this.services.add(service);
    }

    public ArrayList<Map<String, Object>> getServices() {
        return services;
    }*/
}
