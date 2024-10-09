package com.example.sop.controllers;

import com.example.sop.dtos.MedicationOrderDto;
import com.example.sop.dtos.OrderDto;
import com.example.sop.services.OrderService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public EntityModel<OrderDto> getById(@PathVariable Long id) {
        OrderDto orderDto = orderService.getById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        EntityModel<OrderDto> orderModel = EntityModel.of(orderDto);

        orderModel.add(linkTo(methodOn(OrderController.class).getById(id)).withSelfRel());

        return orderModel;
    }

    @GetMapping
    public Iterable<EntityModel<OrderDto>> getAll() {
        return StreamSupport.stream(orderService.getAll().spliterator(), false)
                .map(orderDto -> {
                    EntityModel<OrderDto> orderModel = EntityModel.of(orderDto);
                    orderModel.add(linkTo(methodOn(OrderController.class).getById(orderDto.getId())).withSelfRel());
                    orderModel.add(linkTo(methodOn(OrderController.class).getMedicationOrders(orderDto.getId()))
                            .withRel("medicationOrder"));
                    return orderModel;
                }).collect(Collectors.toList());
    }

    @GetMapping("/{id}/medicationOrder")
    public List<MedicationOrderDto> getMedicationOrders(@PathVariable Long id) {
        return orderService.getById(id)
                .orElseThrow(()-> new RuntimeException("Order not found"))
                .getOrderItems();
    }

    @PostMapping("/registryOrder")
    public EntityModel<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        OrderDto createdOrder = orderService.registry(orderDto);
        return EntityModel.of(createdOrder, linkTo(methodOn(OrderController.class).getById(createdOrder.getId()))
                .withSelfRel());
    }

    @PutMapping("/update/{id}")
    public EntityModel<OrderDto> updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        orderDto.setId(id);
        OrderDto updatedOrder = orderService.update(orderDto);
        return EntityModel.of(updatedOrder, linkTo(methodOn(OrderController.class)
                .getById(updatedOrder.getId())).withSelfRel());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OrderDto> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);

        OrderDto updateOrder = orderService.getById(id)
                .map(orderDto -> {
                    orderDto.add(linkTo(methodOn(OrderController.class).getById(id))
                            .withSelfRel());
                    return orderDto;
                }).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        return ResponseEntity.ok(updateOrder);
    }
}
