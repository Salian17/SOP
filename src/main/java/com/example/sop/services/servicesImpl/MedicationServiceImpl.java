package com.example.sop.services.servicesImpl;

import com.example.sop.dtos.MedicationDto;
import com.example.sop.models.Medication;
import com.example.sop.repositories.MedicationRepository;
import com.example.sop.services.MedicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicationServiceImpl implements MedicationService {
    final private MedicationRepository medicationRepository;
    final private ModelMapper modelMapper;

    @Autowired
    public MedicationServiceImpl(MedicationRepository medicationRepository, ModelMapper modelMapper) {
        this.medicationRepository = medicationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Iterable<MedicationDto> getAll() {
        return medicationRepository.findAll().stream()
                .map(s->modelMapper.map(s, MedicationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MedicationDto> getById(Long id) {
        return Optional.ofNullable(modelMapper.map(medicationRepository.findById(id), MedicationDto.class));
    }

    @Override
    public MedicationDto registry(MedicationDto medicationDto) {
        return modelMapper.map(medicationRepository.saveAndFlush(modelMapper
                .map(medicationDto, Medication.class)), MedicationDto.class);
    }

    @Override
    public MedicationDto update(MedicationDto medication) {
        return modelMapper.map(medicationRepository.saveAndFlush(modelMapper
                .map(medication, Medication.class)), MedicationDto.class);
    }

    @Override
    public void delete(Long id) {
        medicationRepository.deleteById(id);
    }
}
