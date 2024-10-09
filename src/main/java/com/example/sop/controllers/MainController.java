package com.example.sop.controllers;

import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class MainController {
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
}
