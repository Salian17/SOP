package com.example.sop.services.servicesImpl;

import com.example.sop.dtos.MedicationOrderDto;
import com.example.sop.models.MedicationOrder;
import com.example.sop.repositories.MedicationOrderRepository;
import com.example.sop.services.MedicationOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicationOrderServiceImpl implements MedicationOrderService {
    final private MedicationOrderRepository medicationOrderRepository;
    final private ModelMapper modelMapper;

    public MedicationOrderServiceImpl(MedicationOrderRepository medicationOrderRepository, ModelMapper modelMapper) {
        this.medicationOrderRepository = medicationOrderRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public Iterable<MedicationOrderDto> getAll() {
        return medicationOrderRepository.findAll().stream()
                .map((s) -> modelMapper.map(s, MedicationOrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MedicationOrderDto> getById(Long id) {
        return Optional.ofNullable(modelMapper.map(medicationOrderRepository.findById(id), MedicationOrderDto.class));
    }

    @Override
    public MedicationOrderDto registry(MedicationOrderDto medicationOrder) {
        return modelMapper.map(medicationOrderRepository.save(modelMapper
                .map(medicationOrder, MedicationOrder.class)), MedicationOrderDto.class);
    }

    @Override
    public MedicationOrderDto update(MedicationOrderDto medicationOrder) {
        return modelMapper.map(medicationOrderRepository.saveAndFlush(modelMapper
                .map(medicationOrder, MedicationOrder.class)), MedicationOrderDto.class);
    }

    @Override
    public void delete(Long id) {
        medicationOrderRepository.deleteById(id);
    }
}
