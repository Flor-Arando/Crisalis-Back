package com.example.crisalisbackend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crisalisbackend.model.Order;
import com.example.crisalisbackend.repository.OrderRepository;


@Service
@Transactional
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public List<Order> list() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOne(int id) {
        return orderRepository.findById(id);
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public void delete(int id) {
        orderRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return orderRepository.existsById(id);
    }
}
