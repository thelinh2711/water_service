package com.example.watersystem.controller;

import com.example.watersystem.model.TieredPrice;
import com.example.watersystem.service.TieredPriceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tiered-prices")
@CrossOrigin(origins = "*")
public class TieredPriceController {

    private final TieredPriceService service;

    public TieredPriceController(TieredPriceService service) {
        this.service = service;
    }

    @GetMapping
    public List<TieredPrice> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public TieredPrice getOne(@PathVariable Long id) {
        return service.getById(id).orElse(null);
    }

    @GetMapping("/by-service/{serviceId}")
    public List<TieredPrice> getByService(@PathVariable Long serviceId) {
        return service.getByServiceType(serviceId);
    }

    @PostMapping
    public TieredPrice create(@RequestBody TieredPrice price) {
        return service.save(price);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
