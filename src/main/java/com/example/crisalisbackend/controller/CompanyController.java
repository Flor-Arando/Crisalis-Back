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
import com.example.crisalisbackend.service.CompanyService;

@RestController
@RequestMapping("empresa")
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyController {
    
    @Autowired
    CompanyService companyService;

    @GetMapping("/list")
    public ResponseEntity<List<Company>> list(){
        List<Company> listCompanies = companyService.list();
            return new ResponseEntity<>(listCompanies, HttpStatus.OK); 
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){ 

        if(!companyService.existsById(id)) {
            return new ResponseEntity<Message>(new Message("El id no existe"), HttpStatus.NOT_FOUND);
        }
           
        Company company = companyService.getOne(id).get();
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @PostMapping("/create")
     public ResponseEntity<?> create(@RequestBody dtoCompany dtoCompany){     
        
        // TODO: unificar las validaciones con las de update segun correspondan
        if(companyService.existsByCuit(dtoCompany.getCuit())) {
            return new ResponseEntity<Message>(new Message("El cuil de la empresa ya existe"), HttpStatus.BAD_REQUEST);//cambiar por DNI
        }
        
        Company company = new Company(dtoCompany.getCompanyName(), dtoCompany.getActivityStart(), dtoCompany.getCuit());
        companyService.save(company);
        
            return new ResponseEntity<Message>(new Message("Empresa agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoCompany dtoCompany){
        
        if (!companyService.existsById(id)) {
            return new ResponseEntity<Message>(new Message("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
            
        
        if (companyService.existsByCuit(dtoCompany.getCuit()) && (id < 1)) {
            return new ResponseEntity<Message>(new Message("La empresa ya existe"), HttpStatus.BAD_REQUEST);
        } 
           
              
        Company company = companyService.getOne(id).get();
        company.setCompanyName(dtoCompany.getCompanyName());
        // TODO: se está guardando la fecha del dia anterior a la que se selecciona. El request esta bien, pero en get del dto no
        company.setActivityStart((dtoCompany.getActivityStart()));
        company.setCuit((dtoCompany.getCuit()));
        
        companyService.save(company);
            return new ResponseEntity <Message>(new Message("Empresa actualizada"), HttpStatus.OK);
             
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!companyService.existsById(id)) {
            return new ResponseEntity <Message>(new Message("no existe"), HttpStatus.NOT_FOUND);
        }
        companyService.delete(id);
            return new ResponseEntity <Message>(new Message("Empresa eliminada"), HttpStatus.OK);
    }

}
