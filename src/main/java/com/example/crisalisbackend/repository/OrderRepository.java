package com.example.crisalisbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crisalisbackend.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    public List<Order> findByOrderServices/*NotNull*/();
    
}
