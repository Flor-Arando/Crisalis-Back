package com.example.crisalisbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crisalisbackend.model.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {
    Integer deleteByOrderId(int idOrder);
}
