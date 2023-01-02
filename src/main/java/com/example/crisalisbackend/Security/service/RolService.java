/*package com.example.crisalisbackend.Security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crisalisbackend.Security.entity.Rol;
import com.example.crisalisbackend.Security.enums.RolName;
import com.example.crisalisbackend.Security.repository.RolRepository;

@Service
@Transactional
public class RolService {
    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolName(RolName rolName){
        return rolRepository.findByRolName(rolName);
    }
    
    public void save(Rol rol){
        rolRepository.save(rol);
    }
}*/
