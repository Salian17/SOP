package com.example.sop.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class MedicationDto extends RepresentationModel<MedicationDto> {
    private Long id;
    private String name;
    private String dosage;
    private String description;
    private double cost;
    private List<Long> medicationOrdersId;

    protected MedicationDto(){}

    public MedicationDto(Long id, String name, String dosage, String description, double cost, List<Long> medicationOrdersId) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.description = description;
        this.cost = cost;
        this.medicationOrdersId = medicationOrdersId;
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

    public List<Long> getMedicationOrdersId() {
        return medicationOrdersId;
    }

    public void setMedicationOrdersId(List<Long> medicationOrdersId) {
        this.medicationOrdersId = medicationOrdersId;
    }

    @Override
    public String toString() {
        return "MedicationDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dosage='" + dosage + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", medicationOrdersId=" + medicationOrdersId +
                '}';
    }
}
