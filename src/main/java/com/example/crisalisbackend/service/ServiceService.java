package com.example.crisalisbackend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import com.example.crisalisbackend.model.Service ;
import com.example.crisalisbackend.repository.ServiceRepository;

@org.springframework.stereotype.Service
@Transactional
public class ServiceService {
    @Autowired
    ServiceRepository serviceRepository;

    public List<Service> list() {
        return serviceRepository.findAll();
    }

    public Optional<Service> getOne(int id) {
        return serviceRepository.findById(id);
    }

    public Optional<Service> getByName(String name) { 
        return serviceRepository.findByName(name); 
    }

    public void save(Service service) {
        serviceRepository.save(service);
    }

    public void delete(int id) {
        serviceRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return serviceRepository.existsById(id);
    }
}
