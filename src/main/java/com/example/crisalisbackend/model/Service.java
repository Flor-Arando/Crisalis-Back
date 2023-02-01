package com.example.crisalisbackend.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
//import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.relational.core.mapping.Embedded.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    /*
     * @ManyToMany(mappedBy = "services")
     * Set<Order> orders;
     */
    @ManyToMany
    @JoinTable(
        name = "_service_tax", 
        joinColumns = @JoinColumn(name = "id_service"),
        inverseJoinColumns = @JoinColumn(name = "id_tax"))
    Set<Tax> taxes;

    @JsonIgnore
    @OneToMany(mappedBy = "service")
    private Set<OrderService> orderServices;

    public Service() {
    }

    public Service(String name, float price, float supportPrice,Set<Tax> taxes) {
        this.name = name;
        this.price = price;
        this.supportPrice = supportPrice;
        this.taxes = taxes;
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

    public Set<OrderService> getOrderServices() {
        return orderServices;
    }

    public void setServices(Set<OrderService> orderServices) {
        this.orderServices = orderServices;
    }

    public Set<Tax> getTaxes() {
        return taxes;
    }

    public void setTaxes(Set<Tax> taxes) {
        this.taxes = taxes;
    }

}
