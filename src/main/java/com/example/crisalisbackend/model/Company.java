package com.example.crisalisbackend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String companyName;
    private Integer cuil;
    private Date startActivity;

    public Company() {
    }

    public Company(String companyName, Date startActivity, Integer cuil) {
        
        this.companyName = companyName;
        this.startActivity = startActivity;
        this.cuil = cuil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCuil() {
        return cuil;
    }

    public void setCuil(Integer cuil) {
        this.cuil = cuil;
    }

    public Date getStartActivity() {
        return startActivity;
    }

    public void setStartActivity(Date startActivity) {
        this.startActivity = startActivity;
    }  
    
}
