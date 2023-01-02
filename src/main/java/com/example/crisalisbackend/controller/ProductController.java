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

import com.example.crisalisbackend.Dto.dtoProduct;
import com.example.crisalisbackend.Security.controller.Message;
import com.example.crisalisbackend.model.Product;
import com.example.crisalisbackend.service.ProductService;

@RestController
@RequestMapping("producto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    
    @Autowired
    ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<List<Product>> list() {
        List<Product>listProducts = productService.list();
            return new ResponseEntity<>(listProducts, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){ 
       
        if(!productService.existsById(id)) {
            return new ResponseEntity<Message>(new Message("El id no existe"), HttpStatus.NOT_FOUND);
        }
           
        Product product = productService.getOne(id).get();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/create")
     public ResponseEntity<?> create(@RequestBody dtoProduct dtoProduct){     

        /*if(StringUtils.isBlank(dtoPerson.getFirstName())) {
            return new ResponseEntity<Message>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }*/
            
        /*if(productService.existsByProductoId(dtoProduct.getId())) {
            return new ResponseEntity<Message>(new Message("El producto ya existe"), HttpStatus.BAD_REQUEST);//cambiar por DNI
        no hay ID en dtoProduct por eso no va esta condición
        }*/
        
        Product product = new Product(dtoProduct.getName(), dtoProduct.getUnitPrice(), dtoProduct.isWarranty());
        productService.save(product);
        
            return new ResponseEntity<Message>(new Message("Producto agregado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProduct dtoProduct){
        
        if(!productService.existsById(id)) {
            return new ResponseEntity<Message>(new Message("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
            
        
        /*if(productService.(dtoProduct.isWarranty())) {
            return new ResponseEntity<Message>(new Message("La empresa ya existe"), HttpStatus.BAD_REQUEST);
        } NO VA POR ID EN DTO IGUAL QUE EN CREATE HACER OTRA VALIDACION */
           
              
        Product product = productService.getOne(id).get();
        product.setName(dtoProduct.getName());
        product.setUnitPrice((dtoProduct.getUnitPrice()));
        product.setWarranty((dtoProduct.isWarranty()));
        
        productService.save(product);
            return new ResponseEntity <Message>(new Message("Producto actualizado"), HttpStatus.OK);
             
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!productService.existsById(id)) {
            return new ResponseEntity <Message>(new Message("no existe"), HttpStatus.NOT_FOUND);
        }
        productService.delete(id);
            return new ResponseEntity <Message>(new Message("Producto eliminado"), HttpStatus.OK);
    }
}
