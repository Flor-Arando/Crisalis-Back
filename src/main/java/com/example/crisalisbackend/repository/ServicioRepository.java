package com.example.crisalisbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crisalisbackend.model.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Integer>{
    public Optional<Servicio> findByName(String name);
}
