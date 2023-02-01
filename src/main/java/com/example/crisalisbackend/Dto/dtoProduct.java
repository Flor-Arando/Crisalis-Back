package com.example.crisalisbackend.Dto;

import java.util.ArrayList;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.example.crisalisbackend.model.Tax;

public class dtoProduct {
    @NotBlank
    private String name;
    @NotBlank
    private float unitPrice;
    private ArrayList<Integer>taxes;
    

    public dtoProduct(@NotBlank String name, @NotBlank float unitPrice, ArrayList <Integer> taxes) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.taxes = taxes;
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

    public ArrayList<Integer> getTaxes() {
        return taxes;
    }

    public void setTaxes(ArrayList<Integer> taxes) {
        this.taxes = taxes;
    }

}
