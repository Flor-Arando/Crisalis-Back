package com.example.crisalisbackend.Dto;

import javax.validation.constraints.NotBlank;

public class dtoServicio {
    @NotBlank
    private String name;
    @NotBlank
    private float unitPrice;
    @NotBlank
    private boolean support;
    
    public dtoServicio() {
    }
    
    public dtoServicio(@NotBlank String name, @NotBlank float unitPrice, @NotBlank boolean support) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.support = support;
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

    public boolean isSupport() {
        return support;
    }

    public void setSupport(boolean support) {
        this.support = support;
    }
    
    
}
