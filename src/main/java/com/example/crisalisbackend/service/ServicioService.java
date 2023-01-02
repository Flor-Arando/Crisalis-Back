package com.example.crisalisbackend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crisalisbackend.model.Servicio;
import com.example.crisalisbackend.repository.ServicioRepository;

@Service
@Transactional
public class ServicioService {
    @Autowired
    ServicioRepository servicioRepository;

    public List<Servicio> list(){
        return servicioRepository.findAll();
    }

    public Optional<Servicio> getOne(int id){
        return servicioRepository.findById(id);
    }

    public Optional<Servicio> getByName(String name){ 
        return servicioRepository.findByName(name); 
    }

    public void save(Servicio servicio){
        servicioRepository.save(servicio);
    }

    public void delete(int id){
        servicioRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return servicioRepository.existsById(id);
    }
}
