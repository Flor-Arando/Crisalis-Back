package com.example.crisalisbackend.Dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

public class dtoCompany {
    @NotBlank
    private String companyName;
    @NotBlank
    private Integer cuil;
    @NotBlank
    private Date startActivity;
    
    public dtoCompany(@NotBlank String companyName, @NotBlank Integer cuil, @NotBlank Date startActivity) {
        this.companyName = companyName;
        this.cuil = cuil;
        this.startActivity = startActivity;
    }

    public dtoCompany() {
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
