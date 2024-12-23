package com.example.sop.dtos;

import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.RepresentationModel;

public class MedicationOrderDto extends RepresentationModel<MedicationOrderDto> {
    private Long id;
    private Long orderId;
    private Long medicationId;

    protected MedicationOrderDto() {}

    public MedicationOrderDto(Long id, Long orderId, Long medicationId) {
        this.id = id;
        this.orderId = orderId;
        this.medicationId = medicationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Long medicationId) {
        this.medicationId = medicationId;
    }

    @Override
    public String toString() {
        return "MedicationOrderDto{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", medicationId=" + medicationId +
                '}';
    }
}
