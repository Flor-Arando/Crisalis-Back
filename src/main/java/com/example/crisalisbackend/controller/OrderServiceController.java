package com.example.crisalisbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.crisalisbackend.model.Order;
import com.example.crisalisbackend.model.OrderService;
import com.example.crisalisbackend.model.Person;
import com.example.crisalisbackend.repository.ServiceStateRepository;
import com.example.crisalisbackend.service.PersonService;

@Controller
@RequestMapping(path = "/order-service")
public class OrderServiceController {
    //update - list

    @Autowired
    private ServiceStateRepository serviceStateRepository;

    @Autowired
    private PersonService personService;
    

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/list")
    public @ResponseBody Iterable<OrderService> getStates() {
        return this.serviceStateRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/active/{id}")
    public @ResponseBody boolean isActive(@PathVariable("id") int id) {

        boolean isActive = false; 
        Person person = personService.getOne(id).get();

        
        for (Order order : person.getOrders()) {
            for (OrderService orderService : order.getOrderServices()) {

                if (orderService.isActive()) {
                    isActive = true;
                    break;
                }
            }
        }
        
        return isActive;
    }

   /* @CrossOrigin(origins = "*")
    @PutMapping("/update/{id}")
    ResponseEntity<Boolean> convertState(@RequestBody OrderService orderService, @PathVariable boolean active) {
        
    serviceStateRepository.selectActive(active)
                .map(orderService -> {
                    orderService.setActive(newOrderService.getActive());
                    serviceStateRepository.save(orderService);

                    return new ResponseEntity<String>("", HttpStatus.OK);
                })
                .orElseGet(() -> {
                    return new ResponseEntity<String>("Error en actualizar el estado", HttpStatus.INTERNAL_SERVER_ERROR);
                });

        return new ResponseEntity<Boolean>("", HttpStatus.OK);
    } */

}
