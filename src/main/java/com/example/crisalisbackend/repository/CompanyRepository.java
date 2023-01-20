package com.example.crisalisbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crisalisbackend.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository <Company, Integer> {
    public Optional<Company> findByCompanyName(String companyName);
    /*public boolean existsByCompanyName(String companyName);*/
    public boolean existsByCuit(Integer cuit);
    
}
