package com.example.sop.repositories;

import com.example.sop.enums.OrderStatusEnum;
import com.example.sop.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
//    List<Order> findByOrderStatusEnum(OrderStatusEnum orderStatusEnum);
}
