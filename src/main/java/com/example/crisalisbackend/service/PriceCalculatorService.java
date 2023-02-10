package com.example.crisalisbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import com.example.crisalisbackend.model.Order;
import com.example.crisalisbackend.model.Product;
import com.example.crisalisbackend.model.OrderService;
import com.example.crisalisbackend.model.Person;
import com.example.crisalisbackend.model.Tax;
import com.example.crisalisbackend.repository.OrderRepository;

@Service
public class PriceCalculatorService {
    @Autowired
    OrderRepository orderRepository;

    private static float PRODUCT_DISCOUNT_ACTIVE_SERVICE = 10;
    private static float PRODUCT_WARRANTY_INCREASE = 2;
    private static float MAXIMUM_PRODUCT_DISCOUNT_VALUE = 2500;

    public double calculateProductTotalPrice (Product product, int quantity, int warranty, Person person) {
        float taxValue = 0;

        for (Tax tax : product.getTaxes()) {
            taxValue += (tax.getAliquot() / 100) * product.getUnitPrice();    
        } 
        
        boolean isActive = false;
        ArrayList<Optional<Order>> orders = orderRepository.getByPerson(person);
        
        for (Optional<Order> order : orders) {
            Set<OrderService> orderServices = order.get().getOrderServices();

            if (orderServices != null) {
                for (OrderService orderService : orderServices) {
                    if (orderService.isActive()) {
                        isActive = true;
                        break;
                    }
                }
            }
        }
        
        float productPrice = product.getUnitPrice() + taxValue;

        if (isActive) {
            float discount = productPrice * (PRODUCT_DISCOUNT_ACTIVE_SERVICE / 100);
            productPrice -= Math.min(discount, MAXIMUM_PRODUCT_DISCOUNT_VALUE);
        }

        float warrantyValue = (productPrice * (PRODUCT_WARRANTY_INCREASE / 100)) * warranty;

        float productFinalPrice = productPrice + warrantyValue;

        return productFinalPrice * quantity;        
    }   
}
