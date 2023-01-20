package com.example.crisalisbackend.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.relational.core.mapping.Embedded.Nullable;

@Entity
@Table(name = "_service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private float price;
    @Nullable
    private float supportPrice; // TODO: que se pueda poner null

    @ManyToMany(mappedBy = "services")
    Set<Order> orders;    

    public Service() {
    }

    public Service(String name, float price, float supportPrice) {
        this.name = name;
        this.price = price;
        this.supportPrice = supportPrice;
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
