package com.example.crisalisbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crisalisbackend.model.Tax;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Integer>{
    public Optional<Tax> findByName(String name);
}
