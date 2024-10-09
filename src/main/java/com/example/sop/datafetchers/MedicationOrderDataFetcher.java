//package com.example.sop.datafetchers;
//
//import com.example.sop.dtos.MedicationOrderDto;
//import com.example.sop.models.Medication;
//import com.example.sop.models.MedicationOrder;
//import com.example.sop.models.Order;
//import com.example.sop.repositories.MedicationOrderRepository;
//import com.netflix.graphql.dgs.DgsComponent;
//import com.netflix.graphql.dgs.DgsMutation;
//import com.netflix.graphql.dgs.DgsQuery;
//import com.netflix.graphql.dgs.InputArgument;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@DgsComponent
//public class MedicationOrderDataFetcher {
//
//    private final ModelMapper modelMapper;
//    private final MedicationOrderRepository medicationOrderRepository;
//
//    @Autowired
//    public MedicationOrderDataFetcher(ModelMapper modelMapper, MedicationOrderRepository medicationOrderRepository) {
//        this.modelMapper = modelMapper;
//        this.medicationOrderRepository = medicationOrderRepository;
//    }
//
//    @DgsQuery
//    public MedicationOrderDto medicationOrderById(@InputArgument Long id) {
//        MedicationOrder medicationOrder = medicationOrderRepository.findById(id).orElse(null);
//        return medicationOrder != null ? modelMapper.map(medicationOrder, MedicationOrderDto.class) : null;
//    }
//
//    @DgsQuery
//    public List<MedicationOrderDto> medicationOrders() {
//        List<MedicationOrder> medicationOrders = medicationOrderRepository.findAll();
//        return medicationOrders.stream()
//                .map(medicationOrder -> modelMapper.map(medicationOrder, MedicationOrderDto.class))
//                .collect(Collectors.toList());
//    }
//
//    @DgsMutation
//    public MedicationOrderDto addMedicationOrder(@InputArgument MedicationOrderDto medicationOrderDto) {
//        MedicationOrder newMedicationOrder = modelMapper.map(medicationOrderDto, MedicationOrder.class);
//        newMedicationOrder = medicationOrderRepository.save(newMedicationOrder);
//        return modelMapper.map(newMedicationOrder, MedicationOrderDto.class);
//    }
//
//    @DgsMutation
//    public MedicationOrderDto updateMedicationOrder(@InputArgument Long id, @InputArgument MedicationOrderDto medicationOrderDto) {
//        MedicationOrder medicationOrder = medicationOrderRepository.findById(id).orElseThrow(RuntimeException::new);
//        medicationOrder.setOrder(modelMapper.map(medicationOrderDto.getOrder(), Order.class));
//        medicationOrder.setMedication(modelMapper.map(medicationOrderDto.getMedication(), Medication.class));
//        MedicationOrder updatedMedicationOrder = medicationOrderRepository.save(medicationOrder);
//        return modelMapper.map(updatedMedicationOrder, MedicationOrderDto.class);
//    }
//
//    @DgsMutation
//    public boolean deleteMedicationOrder(@InputArgument Long id) {
//        medicationOrderRepository.deleteById(id);
//        return true;
//    }
//}
//
