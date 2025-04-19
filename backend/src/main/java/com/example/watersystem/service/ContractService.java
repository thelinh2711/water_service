package com.example.watersystem.service;

import com.example.watersystem.model.Apartment;
import com.example.watersystem.model.Contract;
import com.example.watersystem.model.Customer;
import com.example.watersystem.model.WaterServiceType;
import com.example.watersystem.repository.ApartmentRepository;
import com.example.watersystem.repository.ContractRepository;
import com.example.watersystem.repository.CustomerRepository;
import com.example.watersystem.repository.WaterServiceTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    private final ContractRepository contractRepository;
    private final CustomerRepository customerRepository;
    private final ApartmentRepository apartmentRepository;
    private final WaterServiceTypeRepository waterServiceTypeRepository;

    public ContractService(ContractRepository contractRepository,
                           CustomerRepository customerRepository,
                           ApartmentRepository apartmentRepository,
                           WaterServiceTypeRepository waterServiceTypeRepository) {
        this.contractRepository = contractRepository;
        this.customerRepository = customerRepository;
        this.apartmentRepository = apartmentRepository;
        this.waterServiceTypeRepository = waterServiceTypeRepository;
    }

    public List<Contract> getAll() {
        return contractRepository.findAll();
    }

    public Optional<Contract> getById(Long id) {
        return contractRepository.findById(id);
    }

    public Contract save(Contract contract) {
        Customer customer = customerRepository.findById(contract.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Apartment apartment = apartmentRepository.findById(contract.getApartment().getId())
                .orElseThrow(() -> new RuntimeException("Apartment not found"));
        WaterServiceType serviceType = waterServiceTypeRepository.findById(contract.getServiceType().getId())
                .orElseThrow(() -> new RuntimeException("Service Type not found"));

        contract.setCustomer(customer);
        contract.setApartment(apartment);
        contract.setServiceType(serviceType);

        return contractRepository.save(contract);
    }

    public void delete(Long id) {
        contractRepository.deleteById(id);
    }
}