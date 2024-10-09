package com.example.sop.repositories;

import com.example.sop.models.MedicationOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationOrderRepository extends JpaRepository<MedicationOrder, Long> {
}
