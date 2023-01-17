package com.example.crisalisbackend.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="id_person")
    @JsonIgnoreProperties("orders")
    private Person person;

    @ManyToOne
    @JoinColumn(name="id_company")
    @JsonIgnoreProperties("orders")
    private Company company;

    @OneToMany(mappedBy = "order")
    private Set<OrderProduct> orderProducts;

    @ManyToMany
    @JoinTable(
        name = "_order_service", 
        joinColumns = @JoinColumn(name = "id_order"),
        inverseJoinColumns = @JoinColumn(name = "id_service"))
    Set<Servicio> services;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public Set<Servicio> getServices() {
        return services;
    }

    public void setServices(Set<Servicio> services) {
        this.services = services;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
