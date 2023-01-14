package com.example.crisalisbackend.service;

import java.util.List;
import java.util.Optional;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crisalisbackend.model.Order;
import com.example.crisalisbackend.model.Product;
import com.example.crisalisbackend.repository.OrderRepository;
import com.example.crisalisbackend.repository.ProductRepository;


@Service
@Transactional
public class OrderService {
    
    @Autowired
    OrderRepository orderRepository;

    public List<Order> list() {
        return orderRepository.findAll();
    }

    
    public Optional<Order> getOne(int id){
        return orderRepository.findById(id);
    }
/*
    public Optional<Product> getByName(String name){ 
        return productRepository.findByName(name); //repository
    }*/

    public void save(Order order){
        orderRepository.save(order);
    }

    public void delete(int id) {
        orderRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return orderRepository.existsById(id);
    }
    
    /*public boolean existsByProductoId(Integer id){
        return productRepository.existsByProductoId(id);
    } prueba para validacion*/
}
