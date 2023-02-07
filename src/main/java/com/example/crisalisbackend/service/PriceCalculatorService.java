package com.example.crisalisbackend.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.crisalisbackend.model.Order;
import com.example.crisalisbackend.model.Product;
import com.example.crisalisbackend.model.OrderService;

import com.example.crisalisbackend.model.Tax;
import com.example.crisalisbackend.repository.OrderRepository;

public class PriceCalculatorService {

    @Autowired
    OrderRepository orderRepository;

    private static float PRODUCT_DISCOUNT_ACTIVE_SERVICE = 10;
    private static float PRODUCT_WARRANTY_INCREASE = 2;
    private static float MAXIMUM_PRODUCT_DISCOUNT_VALUE = 2500;


    public double calculateProductTotalPrice (Product product, int quantity, int warranty, int idPerson) {
        
        float taxValue = 0;

        for (Tax tax : product.getTaxes()) {
            taxValue += (tax.getAliquot() / 100) * product.getUnitPrice();    
        } 

        Order order = orderRepository.findByIdPerson(idPerson).get();
        boolean isActive = false;

        for (OrderService orderService : order.getOrderServices()) {

            if (orderService.isActive()) {
                isActive = true;
                break;
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
