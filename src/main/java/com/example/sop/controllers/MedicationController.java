package com.example.sop.controllers;

import com.example.sop.dtos.MedicationDto;
import com.example.sop.dtos.MedicationOrderDto;
import com.example.sop.services.MedicationService;
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
@RequestMapping("/medication")
public class MedicationController {
    final private MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping
    Iterable<EntityModel<MedicationDto>> getAll() {
        return StreamSupport.stream(medicationService.getAll().spliterator(), false)
                .map(medicationDto -> {
                    EntityModel<MedicationDto> medicationModel = EntityModel.of(medicationDto);
                    medicationModel.add(linkTo(methodOn(MedicationController.class).getById(medicationDto.getId()))
                            .withSelfRel());
                    medicationModel.add(linkTo(methodOn(MedicationController.class).getById(medicationDto.getId()))
                            .withSelfRel());
                    return medicationModel;
                }).collect(Collectors.toList());
    }

    @GetMapping("/{id}/medicationOrder")
    public List<MedicationOrderDto> getMedicationOrders(@PathVariable Long id) {
        return medicationService.getById(id)
                .orElseThrow(() -> new RuntimeException("Medication not found"))
                .getMedicationOrders();
    }

    @PostMapping("/registryMedication")
    public EntityModel<MedicationDto> registry(@RequestBody MedicationDto medication){
        MedicationDto medicationDto = medicationService.registry(medication);
        return EntityModel.of(medicationDto, linkTo(methodOn(MedicationController.class).getById(medicationDto.getId()))
                .withSelfRel());
    }
    @GetMapping("/{id}")
    EntityModel<MedicationDto> getById(@PathVariable Long id){
        MedicationDto medicationDto = medicationService.getById(id)
                .orElseThrow(() -> new RuntimeException("Medication not found"));

        EntityModel<MedicationDto> medicationModel = EntityModel.of(medicationDto);

        medicationModel.add(linkTo(methodOn(MedicationController.class).getById(id)).withSelfRel());
        medicationModel.add(linkTo(methodOn(MedicationController.class).update(id ,medicationDto)).withRel("update"));
        medicationModel.add(linkTo(methodOn(MedicationController.class).getMedicationOrders(id)).withRel("medicationOrders"));
        medicationModel.add(linkTo(methodOn(MedicationController.class).delete(id)).withRel("delete"));

        return medicationModel;
    }
    @PutMapping("/update/{id}")
    public EntityModel<MedicationDto> update(@PathVariable Long id,@RequestBody MedicationDto medicationDto){
        medicationDto.setId(id);
        MedicationDto updatedMedication = medicationService.update(medicationDto);
        return EntityModel.of(updatedMedication, linkTo(methodOn(MedicationController.class)
                .getById(updatedMedication.getId())).withSelfRel());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MedicationDto> delete(@PathVariable Long id){
        medicationService.delete(id);

        MedicationDto updateMedication = medicationService.getById(id)
                .map(medication -> {
                    medication.add(linkTo(methodOn(MedicationController.class).getById(id))
                            .withSelfRel());
                    return medication;
                }).orElseThrow(() -> new IllegalArgumentException("Medication not found"));

        return ResponseEntity.ok(updateMedication);
    }
}
