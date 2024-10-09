package com.example.sop.services;

import com.example.sop.dtos.OrderDto;

import java.util.Optional;

public interface OrderService {
    Iterable<OrderDto> getAll();
    Optional<OrderDto> getById(Long id);
    OrderDto registry(OrderDto order);
    OrderDto update(OrderDto order);
    void delete(Long id);
}
