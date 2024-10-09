package com.example.sop.controllers;

import com.example.sop.dtos.MedicationOrderDto;
import com.example.sop.services.MedicationOrderService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/medicationOrder")
public class MedicationOrderController {
    private final MedicationOrderService medicationOrderService;

    public MedicationOrderController(MedicationOrderService medicationOrderService) {
        this.medicationOrderService = medicationOrderService;
    }

    @GetMapping("/{id}")
    public EntityModel<MedicationOrderDto> getById(@PathVariable Long id) {
        MedicationOrderDto medicationOrderDto = medicationOrderService.getById(id)
                .orElseThrow(()-> new RuntimeException("MedicationOrder not found"));

        EntityModel<MedicationOrderDto> medicationOrderModel = EntityModel.of(medicationOrderDto);

        medicationOrderModel.add(linkTo(methodOn(MedicationOrderController.class).getById(id)).withSelfRel());

        return medicationOrderModel;
    }

    @GetMapping
    public Iterable<EntityModel<MedicationOrderDto>> getAll() {
        return StreamSupport.stream(medicationOrderService.getAll().spliterator(), false)
                .map(medicationOrderDto -> {
                    EntityModel<MedicationOrderDto> medicationOrderModel = EntityModel.of(medicationOrderDto);
                    medicationOrderModel.add(linkTo(methodOn(MedicationOrderController.class)
                            .getById(medicationOrderDto.getId())).withSelfRel());
                    return medicationOrderModel;
                }).collect(Collectors.toList());
    }

    @PostMapping("/registryMedicationOrder")
    public EntityModel<MedicationOrderDto> createMedicationOrder(@RequestBody MedicationOrderDto medicationOrderDto) {
        MedicationOrderDto createdMedicationOrder = medicationOrderService.registry(medicationOrderDto);
        return EntityModel.of(createdMedicationOrder, linkTo(methodOn(MedicationOrderController.class).getById(createdMedicationOrder.getId())).withSelfRel());
    }

    @PutMapping("/update/{id}")
    public EntityModel<MedicationOrderDto> updateMedicationOrder(@PathVariable Long id, @RequestBody MedicationOrderDto medicationOrderDto) {
        medicationOrderDto.setId(id);
        MedicationOrderDto updatedMedicationOrder = medicationOrderService.update(medicationOrderDto);
        return EntityModel.of(updatedMedicationOrder, linkTo(methodOn(MedicationOrderController.class)
                .getById(updatedMedicationOrder.getId())).withSelfRel());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MedicationOrderDto> deleteMedicationOrder(@PathVariable Long id) {
        medicationOrderService.delete(id);

        MedicationOrderDto updateMedicationOrder = medicationOrderService.getById(id)
                .map(medicationOrder -> {
                    medicationOrder.add(linkTo(methodOn(MedicationOrderController.class))
                            .withSelfRel());
                    return medicationOrder;
                }).orElseThrow(() -> new IllegalArgumentException("MedicationOrder not found"));

        return ResponseEntity.ok(updateMedicationOrder);
    }
}
