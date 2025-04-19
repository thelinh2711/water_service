package com.example.watersystem.service;

import com.example.watersystem.model.TieredPrice;
import com.example.watersystem.model.WaterServiceType;
import com.example.watersystem.repository.TieredPriceRepository;
import com.example.watersystem.repository.WaterServiceTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TieredPriceService {

    private final TieredPriceRepository priceRepository;
    private final WaterServiceTypeRepository serviceTypeRepository;

    public TieredPriceService(TieredPriceRepository priceRepository, WaterServiceTypeRepository serviceTypeRepository) {
        this.priceRepository = priceRepository;
        this.serviceTypeRepository = serviceTypeRepository;
    }

    public List<TieredPrice> getAll() {
        return priceRepository.findAll();
    }

    public Optional<TieredPrice> getById(Long id) {
        return priceRepository.findById(id);
    }

    public List<TieredPrice> getByServiceType(Long serviceId) {
        return priceRepository.findByServiceTypeId(serviceId);
    }

    public TieredPrice save(TieredPrice tieredPrice) {
        Long serviceId = tieredPrice.getServiceType().getId();
        WaterServiceType serviceType = serviceTypeRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service type not found"));
        tieredPrice.setServiceType(serviceType);

        return priceRepository.save(tieredPrice);
    }

    public void delete(Long id) {
        priceRepository.deleteById(id);
    }
}
