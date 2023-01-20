package com.example.crisalisbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crisalisbackend.model.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer>{
    public Optional<Service> findByName(String name);
}
