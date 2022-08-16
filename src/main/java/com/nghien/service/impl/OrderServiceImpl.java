package com.nghien.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nghien.entities.Order;
import com.nghien.entities.OrderDetail;
import com.nghien.repository.OrderDetailRepository;
import com.nghien.repository.OrderRepository;
import com.nghien.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Order create(JsonNode orderData) {
        Order order = objectMapper.convertValue(orderData, Order.class);
        orderRepository.save(order);
        TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {
        };
        List<OrderDetail> orderDetails = objectMapper.convertValue(orderData.get("orderDetails"), type).stream()
                .peek(d -> d.setOrder(order)).collect(Collectors.toList());
        orderDetailRepository.saveAll(orderDetails);
        return order;
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public List<Order> findByUsername(String username) {
        return orderRepository.findByUsername(username);
    }
}
