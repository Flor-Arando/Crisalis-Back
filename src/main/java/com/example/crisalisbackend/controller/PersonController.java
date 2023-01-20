package com.example.crisalisbackend.controller;

//import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crisalisbackend.Dto.dtoPerson;
import com.example.crisalisbackend.Security.controller.Message;
import com.example.crisalisbackend.model.Person;
import com.example.crisalisbackend.service.PersonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("persona")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {
    
    @Autowired
    PersonService personService;
    
    @GetMapping("/list")
    /*public ResponseEntity<Optional<Person>> list(){
        Optional<Person> p = personService.getOne(1);
        return new ResponseEntity<>(p, HttpStatus.OK); 
    }*/

    public ResponseEntity<List<Person>> list(){
        List<Person> listPersons = personService.list();
        return new ResponseEntity<>(listPersons, HttpStatus.OK); 
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){ //deberia ir Person pero me daba error porque se confunde con ResponseEntity<Message>
       
        if(!personService.existsById(id)) {
            return new ResponseEntity<Message>(new Message("El id no existe"), HttpStatus.NOT_FOUND);
        }
           
        Person person = personService.getOne(id).get();
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping("/create")
     public ResponseEntity<?> create(@RequestBody dtoPerson dtoPerson){     

        /*public static boolean validarName(String name)
            return name.matches("^()")*/
            
        if(personService.existsByFirstName(dtoPerson.getFirstName())) {
            return new ResponseEntity<Message>(new Message("La persona ya existe"), HttpStatus.BAD_REQUEST);//cambiar por DNI
        }
        
        Person person = new Person(dtoPerson.getFirstName(), dtoPerson.getLastName(), dtoPerson.getDni());
        personService.save(person);
        
            return new ResponseEntity<Message>(new Message("Persona agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPerson dtoPerson){
        
        if(!personService.existsById(id)) {
            return new ResponseEntity<Message>(new Message("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
            
        //Compara nombre de personas
        if(personService.existsByFirstName(dtoPerson.getFirstName()) && personService.getByFirstName(dtoPerson.getFirstName()).get().getId() != id) {
            return new ResponseEntity<Message>(new Message("La persona ya existe"), HttpStatus.BAD_REQUEST);
        }
           
        //No puede estar vacio
        /*if(StringUtils.isBlank(dtoPerson.getFirstName())) {
            return new ResponseEntity<Message>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        } esta dependencia no va*/
                    
        Person person = personService.getOne(id).get();
        person.setFirstName(dtoPerson.getFirstName());
        person.setLastName((dtoPerson.getLastName()));
        person.setDni((dtoPerson.getDni()));
        
        personService.save(person);
            return new ResponseEntity <Message>(new Message("Persona actualizada"), HttpStatus.OK);
             
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!personService.existsById(id)) {
            return new ResponseEntity <Message>(new Message("no existe"), HttpStatus.NOT_FOUND);
        }
        personService.delete(id);
            return new ResponseEntity <Message>(new Message("Persona eliminada"), HttpStatus.OK);
    }

}
