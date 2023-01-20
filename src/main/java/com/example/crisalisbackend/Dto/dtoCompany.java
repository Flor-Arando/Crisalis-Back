package com.example.crisalisbackend.Dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

public class dtoCompany {
    @NotBlank
    private String companyName;
    @NotBlank
    private Integer cuit;
    @NotBlank
    private Date activityStart;
    
    public dtoCompany(@NotBlank String companyName, @NotBlank Integer cuit, @NotBlank Date activityStart) {
        this.companyName = companyName;
        this.cuit = cuit;
        this.activityStart = activityStart;
    }

    public dtoCompany() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCuit() {
        return cuit;
    }

    public void setCuit(Integer cuit) {
        this.cuit = cuit;
    }

    public Date getActivityStart() {
        return activityStart;
    }

    public void setActivityStart(Date activityStart) {
        this.activityStart = activityStart;
    }
    
}
