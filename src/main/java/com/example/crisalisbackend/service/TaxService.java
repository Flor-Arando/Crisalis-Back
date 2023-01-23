package com.example.crisalisbackend.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.crisalisbackend.model.Tax;
import com.example.crisalisbackend.repository.TaxRepository;

@org.springframework.stereotype.Service
@Transactional
public class TaxService {
    @Autowired
    TaxRepository taxRepository;

    public Optional<Tax> getByName(String name) { 
        return taxRepository.findByName(name); 
    }
    
}

