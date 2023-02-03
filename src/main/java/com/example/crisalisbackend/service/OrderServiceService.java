package com.example.crisalisbackend.service;

import java.util.List;
import java.util.Optional;

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

    public List<OrderService> list() {
        return orderServiceRepository.findAll();
    }

    public void save(OrderService orderService) {
        orderServiceRepository.save(orderService);
    }

    public void delete(int id) {
        orderServiceRepository.deleteById(id);
    }

    public void deleteByOrderId(int id) {
        orderServiceRepository.deleteByOrderId(id);
    }

    public boolean existsById(Integer id){
        return orderServiceRepository.existsById(id);
    }

    public Optional<OrderService> getOne(int id) {
        return orderServiceRepository.findById(id);
    }
}
