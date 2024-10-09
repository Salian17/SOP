package com.example.sop.services;

import com.example.sop.dtos.MedicationOrderDto;

import java.util.Optional;

public interface MedicationOrderService {
    Iterable<MedicationOrderDto> getAll();
    Optional<MedicationOrderDto> getById(Long id);
    MedicationOrderDto registry(MedicationOrderDto medicationOrder);
    MedicationOrderDto update(MedicationOrderDto medicationOrder);
    void delete(Long id);
}
