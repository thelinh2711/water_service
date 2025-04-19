package com.example.watersystem.service;

import com.example.watersystem.model.WaterServiceType;
import com.example.watersystem.repository.WaterServiceTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WaterServiceTypeService {

    private final WaterServiceTypeRepository repository;

    public WaterServiceTypeService(WaterServiceTypeRepository repository) {
        this.repository = repository;
    }

    public List<WaterServiceType> getAll() {
        return repository.findAll();
    }

    public Optional<WaterServiceType> getById(Long id) {
        return repository.findById(id);
    }

    public WaterServiceType save(WaterServiceType type) {
        return repository.save(type);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
