package com.example.crisalisbackend.Security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crisalisbackend.Security.entity.Usuario;

public interface UsersRepository extends JpaRepository<Usuario, Integer>{
    
    Optional<Usuario> findByUserName(String userName);
    
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    
}
