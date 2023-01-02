package com.example.crisalisbackend.Dto;

import javax.validation.constraints.NotBlank;

public class dtoPerson {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private int dni;
    
    public dtoPerson(@NotBlank String firstName, @NotBlank String lastName, @NotBlank int dni) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
    }

    public dtoPerson() {
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
