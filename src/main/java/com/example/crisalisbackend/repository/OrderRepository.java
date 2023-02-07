package com.example.crisalisbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crisalisbackend.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    public Optional<Order> findByIdPerson(int idPerson);
}
