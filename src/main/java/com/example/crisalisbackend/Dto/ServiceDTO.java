package com.example.crisalisbackend.Dto;

import java.util.ArrayList;

import javax.validation.constraints.NotBlank;

public class ServiceDTO {
    @NotBlank
    private String name;
    @NotBlank
    private float price;
    @NotBlank
    private float supportPrice;
    private ArrayList<Integer>taxes;
    
    public ServiceDTO() {
    }
    
    public ServiceDTO(@NotBlank String name, @NotBlank float price, @NotBlank float supportPrice, ArrayList <Integer> taxes) {
        this.name = name;
        this.price = price;
        this.supportPrice = supportPrice;
        this.taxes = taxes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getSupportPrice() {
        return supportPrice;
    }

    public void setSupportPrice(float supportPrice) {
        this.supportPrice = supportPrice;
    }

    public ArrayList<Integer> getTaxes() {
        return taxes;
    }

    public void setTaxes(ArrayList<Integer> taxes) {
        this.taxes = taxes;
    }
}
