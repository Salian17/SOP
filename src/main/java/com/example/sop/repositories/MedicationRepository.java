package com.example.sop.repositories;

import com.example.sop.models.Medication;
import com.example.sop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long>{
    List<Medication> findByName(String name);
}
