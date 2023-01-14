package com.example.crisalisbackend.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.example.crisalisbackend.model.Order;
import com.example.crisalisbackend.model.Product;
import com.example.crisalisbackend.repository.PersonRepository;
import com.example.crisalisbackend.service.OrderService;
import com.example.crisalisbackend.service.PersonService;
//import com.example.crisalisbackend.model.Product;
import com.example.crisalisbackend.service.ProductService;
import com.example.crisalisbackend.service.ServicioService;
import com.google.gson.Gson;

@RestController
@RequestMapping("order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    
    @Autowired
    OrderService orderService;
    @Autowired
    PersonService personService;
    //CompanyService companyService;
    @Autowired
    ProductService productService;
    @Autowired
    ServicioService serviceService; // arreglar clase

    @GetMapping("/list")
    public ResponseEntity<List<Order>> list() {
        List<Order> orders = orderService.list();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!orderService.existsById(id)) {
            return new ResponseEntity <Message>(new Message("No existe un pedido con el id solicitado."), HttpStatus.NOT_FOUND);
        }

        orderService.delete(id);
        return new ResponseEntity <Message>(new Message("Pedido eliminado."), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        if (!orderService.existsById(id)) {
            return new ResponseEntity<Message>(new Message("No existe un pedido con el id solicitado."), HttpStatus.NOT_FOUND);
        }
           
        Order order = orderService.getOne(id).get();
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    /*
    {
        "id_person" : 1,
        "id_company" : 1,
        "products" : "[1, 2]",
        "services" : "[]"
    }
    */
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody /*dtoPerson dtoPerson*/ Map<String /*key*/, String /*valor*/> datos) {

       /*if(StringUtils.isBlank(dtoPerson.getFirstName())) {
           return new ResponseEntity<Message>(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
       }*/
           
       /*if(personService.existsByFirstName(dtoPerson.getFirstName())) {
           return new ResponseEntity<Message>(new Message("La persona ya existe"), HttpStatus.BAD_REQUEST);//cambiar por DNI
       }*/
       //
       //Product product = new Product();
       /*
       traer productos, servicios y usuario desde repositorio
    setear productos , servicios y usuario a la orden

       */
//System.out.println(datos.get("a"));
       //System.out.println(datos.get("products"));

       Gson gson = new Gson();
       
       int idPerson = Integer.parseInt(datos.get("id_person"));
       int idCompany = Integer.parseInt(datos.get("id_company"));
       int[] idProducts = gson.fromJson(datos.get("products"), int[].class);
       int[] idServices = gson.fromJson(datos.get("services"), int[].class);
       
       System.out.println("idPerson: " + idPerson);
       System.out.println("idCompany: " + idCompany);
       System.out.println("products: " + Arrays.toString(idProducts));
       System.out.println("services: " + Arrays.toString(idServices));

       Order order = new Order();
       //System.out.println(personService.getOne(idPerson).get());
       //personService.getOne(idPerson);
       order.setPerson(personService.getOne(idPerson).get()); // revisar lo opcional del service

       order.setIdCompany(idCompany);

       
        if (idProducts.length > 0) {
        //TODO: esto pasarlo al service y repository
            Set<Product> products = new HashSet<>();
            for (int id : idProducts) {
                Product product = productService.getOne(id).get();
                products.add(product);
            }

            order.setProducts(products);
        }

        //TODO: aca falta hacer algo similar para servicios

        orderService.save(order);

        return new ResponseEntity<Message>(new Message("Orden agregada"), HttpStatus.CREATED);
   }
}
