package com.example.crisalisbackend.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "_tax")
public class Tax {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private float aliquot;

    @ManyToMany(mappedBy = "taxes")
    Set<Product> products;

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

}
