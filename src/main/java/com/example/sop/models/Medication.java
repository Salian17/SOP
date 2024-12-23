package com.example.sop.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Medication {
    private Long id;
    private String name;
    private String dosage;
    private String description;
    private double cost;
    private List<MedicationOrder> medicationOrders;

    protected Medication() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @OneToMany(mappedBy = "medication")
    public List<MedicationOrder> getMedicationOrders() {
        return medicationOrders;
    }

    public void setMedicationOrders(List<MedicationOrder> medicationOrders) {
        this.medicationOrders = medicationOrders;
    }
}
