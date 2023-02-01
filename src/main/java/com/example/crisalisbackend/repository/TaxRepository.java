package com.example.crisalisbackend.repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crisalisbackend.model.Tax;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Integer>{
    public Optional<Tax> findByName(String name);
    //@Query("SELECT t FROM _tax as t WHERE id = ?1")
    //public ArrayList<Tax> findByIds(ArrayList<Integer> ids);
    
    Set<Tax> findByIdIn(ArrayList<Integer> ids);
        
    
}
