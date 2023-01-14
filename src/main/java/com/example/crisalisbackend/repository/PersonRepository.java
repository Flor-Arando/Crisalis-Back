package com.example.crisalisbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crisalisbackend.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    public Optional<Person> findByFirstName(String firstName);
    public boolean existsByFirstName(String firstName);
}
