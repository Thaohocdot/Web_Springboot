package com.nghien.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.nghien.entities.Order;
import com.nghien.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
    @Autowired
    private OrderService orderService;

    @PostMapping()
    public Order create(@RequestBody JsonNode orderData){
        return orderService.create(orderData);

    }
}
