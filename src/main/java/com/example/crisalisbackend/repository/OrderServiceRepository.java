package com.example.crisalisbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crisalisbackend.model.OrderService;

public interface OrderServiceRepository extends JpaRepository<OrderService, Integer> {
    Integer deleteByOrderId(int idService);
}
