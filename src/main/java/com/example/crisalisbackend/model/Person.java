package com.example.crisalisbackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private int dni;

    public Person() {
    }

    public Person(String firstName, String lastName, int dni) {
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public int getDni() {
        return dni;
    }


    public void setDni(int dni) {
        this.dni = dni;
    }
   
}
