package com.example.crisalisbackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private float unitPrice;
    private boolean warranty;

    public Product() {
    }

    public Product(String name, float unitPrice, boolean warranty) {
        
        this.name = name;
        this.unitPrice = unitPrice;
        this.warranty = warranty;
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

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public boolean isWarranty() {
        return warranty;
    }

    public void setWarranty(boolean warranty) {
        this.warranty = warranty;
    }

  
}
