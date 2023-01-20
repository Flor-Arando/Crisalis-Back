package com.example.crisalisbackend.Dto;

import javax.validation.constraints.NotBlank;

public class ServiceDTO {
    @NotBlank
    private String name;
    @NotBlank
    private float price;
    @NotBlank
    private float supportPrice;
    
    public ServiceDTO() {
    }
    
    public ServiceDTO(@NotBlank String name, @NotBlank float price, @NotBlank float supportPrice) {
        this.name = name;
        this.price = price;
        this.supportPrice = supportPrice;
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
}
