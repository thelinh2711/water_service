package com.example.watersystem.service;

import com.example.watersystem.model.Invoice;
import com.example.watersystem.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository repository;

    public List<Invoice> getAll() {
        return repository.findAll();
    }

    public Optional<Invoice> getById(Long id) {
        return repository.findById(id);
    }

    public Invoice save(Invoice invoice) {
        return repository.save(invoice);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
