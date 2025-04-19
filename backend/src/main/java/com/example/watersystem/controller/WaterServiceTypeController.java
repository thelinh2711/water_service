package com.example.watersystem.controller;

import com.example.watersystem.model.WaterServiceType;
import com.example.watersystem.service.WaterServiceTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/water-service-types")
@CrossOrigin("*")
public class WaterServiceTypeController {

    private final WaterServiceTypeService service;

    public WaterServiceTypeController(WaterServiceTypeService service) {
        this.service = service;
    }

    @GetMapping
    public List<WaterServiceType> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public WaterServiceType getById(@PathVariable Long id) {
        return service.getById(id).orElse(null);
    }

    @PostMapping
    public WaterServiceType create(@RequestBody WaterServiceType type) {
        return service.save(type);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
