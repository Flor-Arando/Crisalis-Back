package com.example.crisalisbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crisalisbackend.model.Order;
import com.example.crisalisbackend.model.Product;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}