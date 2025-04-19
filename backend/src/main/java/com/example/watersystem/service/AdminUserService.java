package com.example.watersystem.service;

import com.example.watersystem.dto.LoginResponse;
import com.example.watersystem.model.AdminUser;
import com.example.watersystem.repository.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final AdminUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(String username, String rawPassword) {
        AdminUser user = repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Sai mật khẩu");
        }

        return new LoginResponse("fake-token", user.getFullName());
    }

    public List<AdminUser> getAll() {
        return repository.findAll();
    }

    public Optional<AdminUser> getById(Long id) {
        return repository.findById(id);
    }

    public Optional<AdminUser> getByUsername(String username) {
        return repository.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    public AdminUser save(AdminUser adminUser) {
        adminUser.setPassword(passwordEncoder.encode(adminUser.getPassword()));
        return repository.save(adminUser);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
