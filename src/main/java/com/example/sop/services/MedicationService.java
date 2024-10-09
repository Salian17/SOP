package com.example.sop.services;

import com.example.sop.dtos.MedicationDto;

import java.util.Optional;

public interface MedicationService {
    Iterable<MedicationDto> getAll();
    Optional<MedicationDto> getById(Long id);
    MedicationDto registry(MedicationDto medication);
    MedicationDto update(MedicationDto medication);
    void delete(Long id);
}
