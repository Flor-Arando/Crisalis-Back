package com.example.crisalisbackend.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crisalisbackend.model.OrderProduct;
import com.example.crisalisbackend.repository.OrderProductRepository;


@Service
@Transactional
public class OrderProductService {
    @Autowired
    OrderProductRepository orderProductRepository;

    public void save(OrderProduct orderProduct) {
        orderProductRepository.save(orderProduct);
    }

    public void delete(int id) {
        orderProductRepository.deleteById(id);
    }

    public void deleteByOrderId(int id) {
        orderProductRepository.deleteByOrderId(id);
    }
}
