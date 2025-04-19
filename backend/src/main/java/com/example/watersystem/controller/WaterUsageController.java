package com.example.watersystem.controller;

import com.example.watersystem.model.WaterUsage;
import com.example.watersystem.service.WaterUsageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/water-usages")
@RequiredArgsConstructor
public class WaterUsageController {

    private final WaterUsageService service;

    @GetMapping
    public List<WaterUsage> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public WaterUsage getOne(@PathVariable Long id) {
        return service.getById(id).orElse(null);
    }

    @GetMapping("/by-contract/{contractId}")
    public List<WaterUsage> getByContract(@PathVariable Long contractId) {
        return service.getByContractId(contractId);
    }

    @PostMapping
    public WaterUsage create(@RequestBody WaterUsage usage) {
        return service.save(usage);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
