package com.nghien.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.nghien.entities.Order;

import java.util.List;

public interface OrderService {

    public Order create(JsonNode orderData);

    public Order findById(Long id);

    List<Order> findByUsername(String username);
}
