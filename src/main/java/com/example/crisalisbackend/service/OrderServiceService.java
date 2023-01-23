package com.example.crisalisbackend.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crisalisbackend.model.OrderService;
import com.example.crisalisbackend.repository.OrderServiceRepository;


@Service
@Transactional
public class OrderServiceService {
    @Autowired
    OrderServiceRepository orderServiceRepository;

    public void save(OrderService orderService) {
        orderServiceRepository.save(orderService);
    }

    public void delete(int id) {
        orderServiceRepository.deleteById(id);
    }

    public void deleteByOrderId(int id) {
        orderServiceRepository.deleteByOrderId(id);
    }
}
