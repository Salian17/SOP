package com.example.sop.controllers;

import com.example.sop.dtos.MedicationOrderDto;
import com.example.sop.dtos.OrderDto;
import com.example.sop.services.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final RabbitTemplate rabbitTemplate;
    static final String exchangeName = "testExchange";
    private final OrderService orderService;
    private ObjectMapper objectMapper;

    @Autowired
    public OrderController(RabbitTemplate rabbitTemplate, OrderService orderService, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.orderService = orderService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{id}")
    public EntityModel<OrderDto> getById(@PathVariable Long id) {
        OrderDto orderDto = orderService.getById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        EntityModel<OrderDto> orderModel = EntityModel.of(orderDto);

        rabbitTemplate.convertAndSend(exchangeName, "my.key",orderDto.toString());

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
    public List<Long> getMedicationOrders(@PathVariable Long id) {
        return orderService.getById(id)
                .orElseThrow(()-> new RuntimeException("Order not found"))
                .getOrderId();
    }

    @PostMapping("/registryOrder")
    public EntityModel<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        OrderDto createdOrder = orderService.registry(orderDto);
//        sendOrder(orderDto);
        return EntityModel.of(createdOrder, linkTo(methodOn(OrderController.class).getById(createdOrder.getId()))
                .withSelfRel());
    }

    public void sendOrder(OrderDto orderDto) {
        try {
            String message = objectMapper.writeValueAsString(orderDto);
            rabbitTemplate.convertAndSend("firstQueue", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        return ResponseEntity.noContent().build();
    }
}
