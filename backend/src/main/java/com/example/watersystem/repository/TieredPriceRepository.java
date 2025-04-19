package com.example.watersystem.repository;

import com.example.watersystem.model.TieredPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TieredPriceRepository extends JpaRepository<TieredPrice, Long> {
    List<TieredPrice> findByServiceTypeId(Long serviceTypeId);
}
