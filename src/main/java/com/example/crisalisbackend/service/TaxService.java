package com.example.crisalisbackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.crisalisbackend.model.Tax;
import com.example.crisalisbackend.repository.TaxRepository;

@org.springframework.stereotype.Service
@Transactional
public class TaxService {
    @Autowired
    TaxRepository taxRepository;

    public List<Tax> list() {
        return taxRepository.findAll();
    }

    public Optional<Tax> getByName(String name) { 
        return taxRepository.findByName(name); 
    }

    public Set<Tax> getByIds(ArrayList<Integer> ids) {
        return taxRepository.findByIdIn(ids);
    }
    
}

