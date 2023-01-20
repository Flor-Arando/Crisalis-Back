package com.example.crisalisbackend.Dto;

import javax.validation.constraints.NotBlank;

public class dtoProduct {
    @NotBlank
    private String name;
    @NotBlank
    private float unitPrice;

    
    public dtoProduct(@NotBlank String name, @NotBlank float unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;

    }

    public dtoProduct() {
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

}
