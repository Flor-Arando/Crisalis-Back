package com.example.crisalisbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crisalisbackend.model.OrderService;

public interface OrderServiceRepository extends JpaRepository<OrderService, Integer> {
    public Integer deleteByOrderId(int idService);
    public boolean existsById(Integer id);
}
