package com.example.crisalisbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crisalisbackend.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    public Optional<Product> findByName(String name);
   // public boolean existsByProductoId(Integer id);
    /*public boolean existsByCompanyName(String companyName);*/
    
}
