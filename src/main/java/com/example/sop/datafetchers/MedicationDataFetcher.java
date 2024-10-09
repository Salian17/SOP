package com.example.sop.datafetchers;

import com.example.sop.dtos.MedicationDto;
import com.example.sop.models.Medication;
import com.example.sop.repositories.MedicationRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
public class MedicationDataFetcher {
    private ModelMapper modelMapper;
    private MedicationRepository medicationRepository;

    @Autowired
    public MedicationDataFetcher(ModelMapper modelMapper, MedicationRepository medicationRepository) {
        this.modelMapper = modelMapper;
        this.medicationRepository = medicationRepository;
    }

    @DgsQuery
    public MedicationDto medicationById(@InputArgument Long id) {
        Medication medication = medicationRepository.findById(id).orElse(null);
        return medication != null ? modelMapper.map(medication, MedicationDto.class) : null;
    }

    @DgsQuery
    public List<MedicationDto> medications(@InputArgument String nameFilter) {
        List<Medication> queryResult;
        if (nameFilter == null) {
            queryResult = medicationRepository.findAll();
        } else {
            queryResult = medicationRepository.findByName(nameFilter);
        }
        return queryResult.stream().map(medication -> modelMapper.map(medication, MedicationDto.class)).collect(Collectors.toList());
    }

    @DgsMutation
    public MedicationDto addMedication(@InputArgument MedicationDto medication) {
        Medication newMedication = modelMapper.map(medication, Medication.class);
        newMedication = medicationRepository.save(newMedication);
        return modelMapper.map(newMedication, MedicationDto.class);
    }

    @DgsMutation
    public MedicationDto updateMedication(@InputArgument Long id, @InputArgument(name="medication") MedicationDto medicationDto) {
        Medication medication = medicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Medication not found"));

        medication.setName(medicationDto.getName());
        medication.setDosage(medicationDto.getDosage());
        medication.setDescription(medicationDto.getDescription());

        Medication updatedMedication = medicationRepository.save(medication);

        return modelMapper.map(updatedMedication, MedicationDto.class);
    }


    @DgsMutation
    public boolean deleteMedication(@InputArgument Long id) {
        medicationRepository.deleteById(id);
        return true;
    }
}

