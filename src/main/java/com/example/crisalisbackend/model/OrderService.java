package com.example.crisalisbackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="_order_service")
public class OrderService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "id_order", nullable = false)
    private Order order;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_service", nullable = false)
    private Service service;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_tax", nullable = true)
    private Tax tax;

    private double totalPrice;
//TODO: Agregar supportPrice para este pedido servicio
    private boolean active;

    public OrderService() {

    }

    public OrderService(int id, Order order, Service service, double totalPrice) {
        this.id = id;
        this.order = order;
        this.service = service;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
