package com.example.crisalisbackend.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.example.crisalisbackend.model.Company;
import com.example.crisalisbackend.model.Order;
import com.example.crisalisbackend.model.OrderProduct;
import com.example.crisalisbackend.model.OrderService;
import com.example.crisalisbackend.model.Person;
import com.example.crisalisbackend.model.Product;
import com.example.crisalisbackend.model.Service;
import com.example.crisalisbackend.service.CompanyService;
import com.example.crisalisbackend.service.OrderProductService;
import com.example.crisalisbackend.service.OrderServiceService;
import com.example.crisalisbackend.service.PersonService;
import com.example.crisalisbackend.service.PriceCalculatorService;
import com.example.crisalisbackend.service.ProductService;
import com.example.crisalisbackend.service.ServiceService;
import com.example.crisalisbackend.service.TaxService;

@RestController
@RequestMapping("order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    @Autowired
    com.example.crisalisbackend.service.OrderService orderService;
    @Autowired
    PersonService personService;
    @Autowired
    CompanyService companyService;
    @Autowired
    ProductService productService;
    @Autowired
    ServiceService serviceService;
    @Autowired
    OrderProductService orderProductService;
    @Autowired
    OrderServiceService orderServiceService;
    @Autowired
    TaxService taxService;
    @Autowired
    PriceCalculatorService priceCalculatorService;

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
            return new ResponseEntity<Message>(new Message("No existe un pedido con el id solicitado."),
                    HttpStatus.NOT_FOUND);
        }

        orderProductService.deleteByOrderId(id);
        orderServiceService.deleteByOrderId(id);
        orderService.delete(id);
        return new ResponseEntity<Message>(new Message("Pedido eliminado."), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        if (!orderService.existsById(id)) {
            return new ResponseEntity<Message>(new Message("No existe un pedido con el id solicitado."),
                    HttpStatus.NOT_FOUND);
        }

        Order order = orderService.getOne(id).get();

        return new ResponseEntity<>(this.mapOrderDTO(order), HttpStatus.OK);
    }

    /*
     * EJEMPLO DE REQUEST
     * {
     * "idPerson" : 1,
     * "idCompany" : 1,
     * "products" : [{ "id" : 1, "warranty" : 20, "quantity" : 1, "tax" : "IVA"}],
     * "services" : [{"id" : 1}]
     * }
     */
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody OrderRequestDTO data) {
        Order order = new Order();

        try {
            this.saveOrder(order, data);
        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Message>(new Message("Orden agregada"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int idOrder, @RequestBody OrderRequestDTO data) {

        try {
            Optional<Order> order = orderService.getOne(idOrder);

            if (order.isEmpty()) {
                throw new Exception("No se encontr?? el pedido solicitado");
            }

            this.saveOrder(order.get(), data);
        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Message>(new Message("Orden actualizada"), HttpStatus.OK);
    }

    private OrderDTO mapOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(order.getId());

        if (order.getCompany() != null) {
            orderDTO.setCompany(order.getCompany().getCompanyName());
            orderDTO.setIdCompany(order.getCompany().getId());
        }

        orderDTO.setPerson(order.getPerson().getFullName());
        orderDTO.setIdPerson(order.getPerson().getId());
        orderDTO.setCreationDate(order.getCreationDate()); //TODO: setear fecha de modificacion?
        
        for (OrderService orderService : order.getOrderServices()) {
            Service service = orderService.getService();

            Map<String, Object> data = new HashMap<>();
            data.put("id", service.getId());
            data.put("name", service.getName());
            data.put("price", service.getPrice());
            data.put("support_price", service.getSupportPrice());
            data.put("active", orderService.isActive());
            data.put("orderServiceId", orderService.getId());
            data.put("taxes", service.getTaxes());
            
            orderDTO.addService(data);
        }

        for (OrderProduct orderProduct : order.getOrderProducts()) {
            Product product = orderProduct.getProduct();

            Map<String, Object> data = new HashMap<>();
            data.put("id", product.getId());
            data.put("name", product.getName());
            data.put("unit_price", product.getUnitPrice());
            data.put("warranty", orderProduct.getWarranty());
            data.put("quantity", orderProduct.getQuantity());
            data.put("total_price", orderProduct.getTotalPrice());
            data.put("taxes", product.getTaxes());
            
            orderDTO.addProduct(data);
        }

        return orderDTO;
    }

    private void saveOrder(Order order, OrderRequestDTO data) throws Exception {
        if (order.getId() > 0) {
            order.setLastModification(new Date());
        } else {
            order.setCreationDate(new Date());
        }
        
        Optional<Person> person = personService.getOne((int) data.getIdPerson());
        if (person.isEmpty()) {
            throw new Exception("No se encontr?? la persona solicitada");
        }
        order.setPerson(person.get());
        
        int idCompany = data.getIdCompany();
        if (idCompany > 0) {
            Optional<Company> company = companyService.getOne(data.getIdCompany());
            if (company.isEmpty()) {
                throw new Exception("No se encontr?? la empresa solicitada");
            }

            order.setCompany(company.get());
        } else {
            order.setCompany(null); 
        }
        
        for (Map<String, Object> serviceData : data.getServices()) {
            Optional<Service> service = serviceService.getOne(Integer.parseInt((String) serviceData.get("id")));

            if (service.isEmpty()) {
                throw new Exception("No se encontr?? el servicio con id " + serviceData.get("id"));
            }
        }
        
        for (Map<String, Object> productData : data.getProducts()) {
            Optional<Product> product = productService.getOne(Integer.parseInt((String) productData.get("id")));
            
            if (product.isEmpty()) {
                throw new Exception("No se encontr?? el producto con id " + productData.get("id"));
            }
        }
        
        orderService.save(order);
        
        orderServiceService.deleteByOrderId(order.getId());
        if (data.getServices().size() > 0) {
            for (Map<String, Object> serviceData : data.getServices()) {
                Service service = serviceService.getOne(Integer.parseInt((String) serviceData.get("id"))).get();

                OrderService orderService = new OrderService();
                orderService.setOrder(order);
                orderService.setService(service);
                orderService.setTotalPrice(999); 
                orderServiceService.save(orderService);
            }
        }
        
        orderProductService.deleteByOrderId(order.getId());
        if (data.getProducts().size() > 0) {
            for (Map<String, Object> productData : data.getProducts()) {
                Product product = productService.getOne(Integer.parseInt((String) productData.get("id"))).get();
                int warranty = (productData.get("warranty") != null) ? (int) productData.get("warranty") : 0;

                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setOrder(order);
                orderProduct.setProduct(product);
                orderProduct.setWarranty(warranty);
                orderProduct.setQuantity((int) productData.get("quantity"));
                orderProduct.setTotalPrice(priceCalculatorService.calculateProductTotalPrice(product, orderProduct.getQuantity(), orderProduct.getWarranty(), order.getPerson()));

                orderProductService.save(orderProduct);
            }
        }
    }

    @GetMapping("/with-services")
    public ResponseEntity<List<OrderDTO>> withServices() {
        List<OrderDTO> orders = new ArrayList<>();

        for (Order order : orderService.listWithServices()) {
            orders.add(mapOrderDTO(order));
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
