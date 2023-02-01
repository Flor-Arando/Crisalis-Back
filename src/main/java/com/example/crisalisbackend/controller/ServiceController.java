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

import com.example.crisalisbackend.Dto.ServiceDTO;
import com.example.crisalisbackend.Security.controller.Message;
import com.example.crisalisbackend.model.Service;
import com.example.crisalisbackend.service.ServiceService;
import com.example.crisalisbackend.service.TaxService;

@RestController
@RequestMapping("servicio")
@CrossOrigin(origins = "http://localhost:4200")
public class ServiceController {
    @Autowired
    ServiceService serviceService;
    @Autowired
    TaxService taxService;

    @GetMapping("/list")
    public ResponseEntity<List<Service>> list() {
        List<Service>listServices = serviceService.list();
            return new ResponseEntity<>(listServices, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){ 
       
        if(!serviceService.existsById(id)) {
            return new ResponseEntity<Message>(new Message("El ID no existe"), HttpStatus.NOT_FOUND);
        }
           
        Service service = serviceService.getOne(id).get();
        return new ResponseEntity<>(service, HttpStatus.OK);
    }

    @PostMapping("/create") //AGREGAR VALIDACIONES
     public ResponseEntity<?> create(@RequestBody ServiceDTO serviceDTO){     
        
        Service service = new Service(serviceDTO.getName(), serviceDTO.getPrice(), serviceDTO.getSupportPrice(), taxService.getByIds(serviceDTO.getTaxes()));
        serviceService.save(service); //ordenar como en DTO 
        
        return new ResponseEntity<Message>(new Message("Servicio agregado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}") //AGREGAR VALIDACIONES
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ServiceDTO serviceDTO){
        
        if(!serviceService.existsById(id)) {
            return new ResponseEntity<Message>(new Message("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
              
        Service service = serviceService.getOne(id).get();
        service.setName(serviceDTO.getName());
        service.setPrice((serviceDTO.getPrice()));
        service.setSupportPrice(serviceDTO.getSupportPrice());
        service.setTaxes(taxService.getByIds(serviceDTO.getTaxes())); // TODO: validar los ids de tax
        serviceService.save(service);
        
        return new ResponseEntity <Message>(new Message("Servicio actualizado"), HttpStatus.OK);            
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!serviceService.existsById(id)) {
            return new ResponseEntity <Message>(new Message("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        serviceService.delete(id);
            return new ResponseEntity <Message>(new Message("Servicio eliminado"), HttpStatus.OK);
    }
}
