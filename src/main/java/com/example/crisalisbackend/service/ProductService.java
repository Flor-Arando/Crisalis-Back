package com.example.crisalisbackend.service;

import java.util.List;
import java.util.Optional;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crisalisbackend.model.Product;
import com.example.crisalisbackend.repository.ProductRepository;


@Service
@Transactional
public class ProductService {
    
    @Autowired
    ProductRepository productRepository;

    public List<Product> list(){
        return productRepository.findAll();
    }

    public Optional<Product> getOne(int id){
        return productRepository.findById(id);
    }

    public Optional<Product> getByName(String name){ 
        return productRepository.findByName(name); //repository
    }

    public void save(Product product){
        productRepository.save(product);
    }

    public void delete(int id){
        productRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return productRepository.existsById(id);
    }
    
    /*public boolean existsByProductoId(Integer id){
        return productRepository.existsByProductoId(id);
    } prueba para validacion*/
}
