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

import com.example.crisalisbackend.Dto.dtoCompany;
import com.example.crisalisbackend.Security.controller.Message;
import com.example.crisalisbackend.model.Company;
import com.example.crisalisbackend.model.Tax;
import com.example.crisalisbackend.service.CompanyService;
import com.example.crisalisbackend.service.TaxService;

@RestController
@RequestMapping("tax")
@CrossOrigin(origins = "http://localhost:4200")
public class TaxController {

    @Autowired
    TaxService taxService;

    @GetMapping("/list")
    public ResponseEntity<List<Tax>> list() {
        List<Tax> taxes = taxService.list();
        return new ResponseEntity<>(taxes, HttpStatus.OK); 
    }

/*    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){ 

        if(!companyService.existsById(id)) {
            return new ResponseEntity<Message>(new Message("El id no existe"), HttpStatus.NOT_FOUND);
        }
           
        Company company = companyService.getOne(id).get();
        return new ResponseEntity<>(company, HttpStatus.OK);
    }*/
}
