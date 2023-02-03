package com.example.crisalisbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crisalisbackend.Security.controller.Message;
import com.example.crisalisbackend.model.OrderService;
import com.example.crisalisbackend.service.OrderServiceService;

@RestController
@RequestMapping("order-service-state")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderServiceStateController {

    @Autowired
    OrderServiceService orderServiceService;

    @GetMapping("/list")
    public ResponseEntity<List<OrderService>> list() {
        List<OrderService> orderServices = orderServiceService.list();
        return new ResponseEntity<>(orderServices, HttpStatus.OK); 
    }

    @PatchMapping("/change-active/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) { 

        if (!orderServiceService.existsById(id)) {
            return new ResponseEntity<Message>(new Message("El id no existe"), HttpStatus.NOT_FOUND);
        }
           
        OrderService orderService = orderServiceService.getOne(id).get();
        orderService.setActive(!orderService.isActive());
        orderServiceService.save(orderService);

        return new ResponseEntity<>(orderService, HttpStatus.NO_CONTENT);
    }
}
