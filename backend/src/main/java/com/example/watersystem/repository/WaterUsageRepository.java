package com.example.watersystem.repository;

import com.example.watersystem.model.WaterUsage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WaterUsageRepository extends JpaRepository<WaterUsage, Long> {
    List<WaterUsage> findByContractId(Long contractId);
    List<WaterUsage> findByMonth(String month);
}
