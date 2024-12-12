package com.example.sop.dtos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class MedicationDto extends RepresentationModel<MedicationDto> {
    private Long id;
    private String name;
    private String dosage;
    private String description;
    private List<MedicationOrderDto> medicationOrders;

    protected MedicationDto(){}

    public MedicationDto(Long id, String name, String dosage, String description, List<MedicationOrderDto> medicationOrders) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.description = description;
        this.medicationOrders = medicationOrders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MedicationOrderDto> getMedicationOrders() {
        return medicationOrders;
    }

    public void setMedicationOrders(List<MedicationOrderDto> medicationOrders) {
        this.medicationOrders = medicationOrders;
    }

    @Override
    public String toString() {
        return "\nMedicationDto{" +
                "\n    id=" + id +
                ",\n    name='" + name + '\'' +
                ",\n    dosage='" + dosage + '\'' +
                ",\n    description='" + description + '\'' +
                ",\n    medicationOrders=" + medicationOrders +
                "\n }";
    }
}
