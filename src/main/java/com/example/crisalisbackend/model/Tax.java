package com.example.crisalisbackend.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "_tax")
public class Tax {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private float aliquot;

    @OneToMany(mappedBy = "tax")
    private Set<OrderProduct> orderProducts;

    @OneToMany(mappedBy = "tax")
    private Set<OrderService> orderServices;
    
    public Tax() {

    }
    
    public Tax(int id, String name, float aliquot) {
        this.id = id;
        this.name = name;
        this.aliquot = aliquot;
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

    public float getAliquot() {
        return aliquot;
    }

    public void setAliquot(float aliquot) {
        this.aliquot = aliquot;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public Set<OrderService> getOrderServices() {
        return orderServices;
    }

    public void setOrderServices(Set<OrderService> orderServices) {
        this.orderServices = orderServices;
    }

}
