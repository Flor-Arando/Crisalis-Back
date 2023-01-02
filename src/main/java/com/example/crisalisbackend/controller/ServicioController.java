package com.example.crisalisbackend.controller;

import java.util.List;

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

import com.example.crisalisbackend.Dto.dtoServicio;
import com.example.crisalisbackend.Security.controller.Message;
import com.example.crisalisbackend.model.Servicio;
import com.example.crisalisbackend.service.ServicioService;

@RestController
@RequestMapping("servicio")
@CrossOrigin(origins = "http://localhost:4200")
public class ServicioController {
    @Autowired
    ServicioService servicioService;

    @GetMapping("/list")
    public ResponseEntity<List<Servicio>> list() {
        List<Servicio>listServicios = servicioService.list();
            return new ResponseEntity<>(listServicios, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){ 
       
        if(!servicioService.existsById(id)) {
            return new ResponseEntity<Message>(new Message("El ID no existe"), HttpStatus.NOT_FOUND);
        }
           
        Servicio servicio = servicioService.getOne(id).get();
            return new ResponseEntity<>(servicio, HttpStatus.OK);
    }

    @PostMapping("/create") //AGREGAR VALIDACIONES
     public ResponseEntity<?> create(@RequestBody dtoServicio dtoServicio){     
        
        Servicio servicio = new Servicio(dtoServicio.getName(), dtoServicio.getUnitPrice(), dtoServicio.isSupport());
        servicioService.save(servicio); //ordenar como en DTO 
        
            return new ResponseEntity<Message>(new Message("Servicio agregado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}") //AGREGAR VALIDACIONES
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoServicio dtoServicio){
        
        if(!servicioService.existsById(id)) {
            return new ResponseEntity<Message>(new Message("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
              
        Servicio servicio = servicioService.getOne(id).get();
        servicio.setName(dtoServicio.getName());
        servicio.setUnitPrice((dtoServicio.getUnitPrice()));
        servicio.setSupport((dtoServicio.isSupport()));
        
        servicioService.save(servicio);
            return new ResponseEntity <Message>(new Message("Servicio actualizado"), HttpStatus.OK);            
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!servicioService.existsById(id)) {
            return new ResponseEntity <Message>(new Message("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        servicioService.delete(id);
            return new ResponseEntity <Message>(new Message("Servicio eliminado"), HttpStatus.OK);
    }

}
