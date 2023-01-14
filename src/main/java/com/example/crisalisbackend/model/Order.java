package com.example.crisalisbackend.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


// https://www.infoworld.com/article/3373652/java-persistence-with-jpa-and-hibernate-part-1-entities-and-relationships.html
// https://www.baeldung.com/learn-jpa-hibernate
// https://springframework.guru/requestbody-annotation/

@Entity
@Table(name="_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //(si están ambos completos es que fue una persona representando a la empresa, si solo está id_persona fue un particular)
    //private int idPerson;
    //@OneToOne(mappedBy="person")
    
    @ManyToOne
    //@JoinColumn(name = "id_person", referencedColumnName = "id")
    @JoinColumn(name="id_person")
    @JsonIgnoreProperties("orders")
    private Person person;


    @Column(nullable = true)
    private Integer idCompany;

    @ManyToMany
    @JoinTable(
        name = "_order_product", 
        joinColumns = @JoinColumn(name = "id_order"),
        inverseJoinColumns = @JoinColumn(name = "id_product"))
    Set<Product> products;

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




    /*public int getIdPerson() {
        return idPerson;
    }




    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }*/




    public Integer getIdCompany() {
        return idCompany;
    }




    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }




    public Set<Product> getProducts() {
        return products;
    }




    public void setProducts(Set<Product> products) {
        this.products = products;
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

    /*public Order(String name, float unitPrice, boolean warranty) {
        
        this.name = name;
        this.unitPrice = unitPrice;
        this.warranty = warranty;
    }*/

    
}
