package com.example.crisalisbackend.Dto;

import javax.validation.constraints.NotBlank;

public class dtoProduct {
    @NotBlank
    private String name;
    @NotBlank
    private float unitPrice;
    @NotBlank
    private boolean warranty;
    
    public dtoProduct(@NotBlank String name, @NotBlank float unitPrice, @NotBlank boolean warranty) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.warranty = warranty;
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

    public boolean isWarranty() {
        return warranty;
    }

    public void setWarranty(boolean warranty) {
        this.warranty = warranty;
    }
}
