package com.example.watersystem.service;

import com.example.watersystem.model.Apartment;
import com.example.watersystem.model.Customer;
import com.example.watersystem.repository.ApartmentRepository;
import com.example.watersystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApartmentService {
    @Autowired
    private CustomerRepository customerRepository;
    private final ApartmentRepository repository;

    public ApartmentService(ApartmentRepository repository) {
        this.repository = repository;
    }

    public List<Apartment> getAll() {
        return repository.findAll();
    }

    public Optional<Apartment> getById(Long id) {
        return repository.findById(id);
    }

    public List<Apartment> getByCustomerId(Long customerId) {
        return repository.findByCustomerId(customerId);
    }

    public Apartment save(Apartment apartment) {
        Long customerId = apartment.getCustomer().getId();
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        apartment.setCustomer(customer); // gán customer đã được quản lý

        return repository.save(apartment);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
