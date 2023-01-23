package com.example.crisalisbackend.model;

import java.util.Date;
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
    private Date creationDate;
    private Date lastModification;

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

    @OneToMany(mappedBy = "order")
    private Set<OrderService> orderServices;
/*
    @ManyToMany
    @JoinTable(
        name = "_order_service", 
        joinColumns = @JoinColumn(name = "id_order"),
        inverseJoinColumns = @JoinColumn(name = "id_service"))
    Set<Service> services;
*/
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

    /*public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }*/

    public Set<OrderService> getOrderServices() {
        return orderServices;
    }

    public void setOrderServices(Set<OrderService> orderServices) {
        this.orderServices = orderServices;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastModification() {
        return lastModification;
    }

    public void setLastModification(Date lastModification) {
        this.lastModification = lastModification;
    }
}
