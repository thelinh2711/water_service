package com.example.watersystem.service;

import com.example.watersystem.dto.LoginResponse;
import com.example.watersystem.model.Customer;
import com.example.watersystem.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(String username, String rawPassword) {
        Customer customer = repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại"));

        if (!passwordEncoder.matches(rawPassword, customer.getPassword())) {
            throw new RuntimeException("Sai mật khẩu");
        }

        return new LoginResponse("fake-token", customer.getFullName());
    }

    public List<Customer> getAll() {
        return repository.findAll();
    }

    public Optional<Customer> getById(Long id) {
        return repository.findById(id);
    }

    public Optional<Customer> getByUsername(String username) {
        return repository.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    public Customer save(Customer customer) {
        if (customer.getPassword() != null && !customer.getPassword().isBlank()) {
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        }
        return repository.save(customer);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}