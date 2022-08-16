package com.nghien.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nghien.entities.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
}
