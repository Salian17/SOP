package com.example.sop.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.hateoas.RepresentationModel;

public class MedicationOrderDto extends RepresentationModel<MedicationOrderDto> {
    private Long id;
    private OrderDto order;
    private MedicationDto medication;

    protected MedicationOrderDto() {}

    public MedicationOrderDto(Long id, OrderDto order, MedicationDto medication) {
        this.id = id;
        this.order = order;
        this.medication = medication;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderDto getOrder() {
        return order;
    }

    public void setOrder(OrderDto order) {
        this.order = order;
    }

    public MedicationDto getMedication() {
        return medication;
    }

    public void setMedication(MedicationDto medication) {
        this.medication = medication;
    }

    @Override
    public String toString() {
        return "\nMedicationOrderDto{" +
                "\n   id=" + id +
                ",\n    order=" + order +
                ",\n    medication=" + medication +
                "\n}";
    }
}
