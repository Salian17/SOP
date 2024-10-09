package com.example.sop.services.servicesImpl;

import com.example.sop.dtos.OrderDto;
import com.example.sop.models.Order;
import com.example.sop.repositories.OrderRepository;
import com.example.sop.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    final private OrderRepository orderRepository;
    final private ModelMapper modelMapper;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Iterable<OrderDto> getAll() {
        return orderRepository.findAll().stream()
                .map(s-> modelMapper.map(s, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDto> getById(Long id) {
        return Optional.ofNullable(modelMapper.map(orderRepository.findById(id), OrderDto.class));
    }

    @Override
    public OrderDto registry(OrderDto order) {
        return modelMapper.map(orderRepository.saveAndFlush(modelMapper
                .map(order, Order.class)), OrderDto.class);
    }

    @Override
    public OrderDto update(OrderDto order) {
        return modelMapper.map(orderRepository.saveAndFlush(modelMapper
                .map(order, Order.class)), OrderDto.class);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
