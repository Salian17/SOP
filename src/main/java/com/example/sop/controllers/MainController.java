package com.example.sop.controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class MainController {
    private final RabbitTemplate rabbitTemplate;
    static final String exchangeName = "testExchange";

    public MainController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/")
    public List<Link> index() {
        List<Link> links = new ArrayList<>();

        Link linkMedication = linkTo(methodOn(MedicationController.class).getAll()).withRel("medication");
//        Link linkUser = linkTo(methodOn(UserController.class).getAllUsers()).withRel("user");
        Link linkOrder = linkTo(methodOn(OrderController.class).getAll()).withRel("order");
        Link linkMedicationOrder = linkTo(methodOn(MedicationOrderController.class).getAll()).withRel("medicationOrder");

        links.add(linkMedication);
//        links.add(linkUser);
        links.add(linkOrder);
        links.add(linkMedicationOrder);


        return links;
    }

    @PostMapping("/message")
    public ResponseEntity<String> sendMessageToQ1(@RequestBody String message){
        rabbitTemplate.convertAndSend(exchangeName, "my.key",message);

        return ResponseEntity.ok().body("Send to Q1!");
    }
}
