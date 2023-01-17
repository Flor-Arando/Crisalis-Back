package com.example.crisalisbackend.controller;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.example.crisalisbackend.Dto.OrderDTO;
import com.example.crisalisbackend.Dto.OrderRequestDTO;
import com.example.crisalisbackend.Security.controller.Message;
import com.example.crisalisbackend.model.Order;
import com.example.crisalisbackend.model.OrderProduct;
import com.example.crisalisbackend.model.Product;
import com.example.crisalisbackend.model.Servicio;
import com.example.crisalisbackend.service.CompanyService;
import com.example.crisalisbackend.service.OrderProductService;
import com.example.crisalisbackend.service.OrderService;
import com.example.crisalisbackend.service.PersonService;
import com.example.crisalisbackend.service.ProductService;
import com.example.crisalisbackend.service.ServicioService;

@RestController
@RequestMapping("order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    PersonService personService;
    @Autowired
    CompanyService companyService;
    @Autowired
    ProductService productService;
    @Autowired
    ServicioService serviceService; // TODO: arreglar clase
    @Autowired
    OrderProductService orderProductService;

    @GetMapping("/list")
    public ResponseEntity<List<OrderDTO>> list() {
        List<OrderDTO> orders = new ArrayList<>();

        for (Order order : orderService.list()) {
            orders.add(mapOrderDTO(order));
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!orderService.existsById(id)) {
            return new ResponseEntity <Message>(new Message("No existe un pedido con el id solicitado."), HttpStatus.NOT_FOUND);
        }

        orderProductService.deleteByOrderId(id);
        orderService.delete(id);
        return new ResponseEntity <Message>(new Message("Pedido eliminado."), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        if (!orderService.existsById(id)) {
            return new ResponseEntity<Message>(new Message("No existe un pedido con el id solicitado."), HttpStatus.NOT_FOUND);
        }

        Order order = orderService.getOne(id).get();

        return new ResponseEntity<>(this.mapOrderDTO(order), HttpStatus.OK);
    }

    /*
    EJEMPLO DE REQUEST
    {
        "idPerson" : 1,
        "idCompany" : 1,
        "products" : [{ "id" : 1, "warranty" : 2}, { "id" : 2, "warranty" : 5}],
        "services" : [1, 2]
    }
    */
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody OrderRequestDTO data) {
        // TODO: faltan validaciones

        Order order = new Order();
        this.saveOrder(order, data);
        return new ResponseEntity<Message>(new Message("Orden agregada"), HttpStatus.CREATED);
    }

    /*
    EJEMPLO DE REQUEST
    {
        "idPerson" : 1,
        "products" : [{ "id" : 1, "warranty" : 20}],
        "services" : [1]
    }
    */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int idOrder, @RequestBody OrderRequestDTO data) {
        Order order = orderService.getOne(idOrder).get();
        this.saveOrder(order, data);
        return new ResponseEntity<Message>(new Message("Orden actualizada"), HttpStatus.OK);
    }

    private OrderDTO mapOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(order.getId());

        if (order.getCompany() != null) {
            orderDTO.setCompany(order.getCompany().getCompanyName());
        }
        
        orderDTO.setPerson(order.getPerson().getFullName());

        for (Servicio service : order.getServices()) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", service.getId());
            data.put("name", service.getName());
            data.put("support", service.isSupport());
            data.put("price", service.getUnitPrice());
            orderDTO.addService(data);
        }

        for (OrderProduct orderProduct : order.getOrderProducts()) {
            Map<String, Object> data = new HashMap<>();
            Product product = orderProduct.getProduct();
            data.put("id", product.getId());
            data.put("name", product.getName());
            data.put("unit_price", product.getUnitPrice());
            data.put("warranty", orderProduct.getWarranty());
            orderDTO.addProduct(data);
        }

        return orderDTO;
    }

    private void saveOrder(Order order, OrderRequestDTO data) {
        order.setPerson(personService.getOne(data.getIdPerson()).get()); // TODO: revisar lo opcional del service
        
        int idCompany = data.getIdCompany();
        if (idCompany > 0) {
            order.setCompany(companyService.getOne(idCompany).get()); // Si no llega IdCompany, queda en NULL
        } else {
            order.setCompany(null);
        }

        if (data.getServices().size() > 0) {
            // TODO: esto pasarlo al service y repository
            Set<Servicio> services = new HashSet<>();
            for (Integer idService : data.getServices()) {
                services.add(serviceService.getOne(idService).get());
            }

            order.setServices(services);
        }

        orderService.save(order);

        if (data.getProducts().size() > 0) {
            orderProductService.deleteByOrderId(order.getId());

            // TODO: esto pasarlo al service y repository
            for (Map<String, Object> productData : data.getProducts()) {
                OrderProduct orderProduct = new OrderProduct();
                Product product = productService.getOne((int) productData.get("id")).get();
                orderProduct.setOrder(order);
                orderProduct.setProduct(product);
                orderProduct.setWarranty((int) productData.get("warranty"));
                orderProductService.save(orderProduct);
            }
        }
    }
}
