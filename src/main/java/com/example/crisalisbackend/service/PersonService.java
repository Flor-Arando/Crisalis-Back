package com.example.crisalisbackend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crisalisbackend.model.Person;
import com.example.crisalisbackend.repository.PersonRepository;

@Service
@Transactional
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> list(){
        return personRepository.findAll();
    }
    
    public Optional<Person> getOne(int id){
        return personRepository.findById(id);
    }

    public Optional<Person> getByFirstName(String firstName){
        return personRepository.findByFirstName(firstName);
    }
    
    public void save(Person person){ //variable llamada person de tipo Person que traera un objeto a guardar
        personRepository.save(person);
    }

    public void delete(int id){
        personRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return personRepository.existsById(id);
    }
    
    public boolean existsByFirstName(String firstName){
        return personRepository.existsByFirstName(firstName);
    }
    
}
