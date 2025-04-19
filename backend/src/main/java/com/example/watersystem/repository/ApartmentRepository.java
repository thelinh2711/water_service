package com.example.watersystem.repository;

import com.example.watersystem.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    List<Apartment> findByCustomerId(Long customerId);
}
