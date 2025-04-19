package com.example.watersystem.service;

import com.example.watersystem.model.Contract;
import com.example.watersystem.model.WaterUsage;
import com.example.watersystem.repository.ContractRepository;
import com.example.watersystem.repository.WaterUsageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WaterUsageService {

    private final WaterUsageRepository repository;
    private final ContractRepository contractRepository;

    public List<WaterUsage> getAll() {
        return repository.findAll();
    }

    public Optional<WaterUsage> getById(Long id) {
        return repository.findById(id);
    }

    public List<WaterUsage> getByContractId(Long contractId) {
        return repository.findByContractId(contractId);
    }

    public WaterUsage save(WaterUsage usage) {
        Long contractId = usage.getContract().getId();
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
        usage.setContract(contract);
        return repository.save(usage);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
