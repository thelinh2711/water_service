package com.example.watersystem.model;

import com.example.watersystem.enums.InvoiceStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Liên kết với WaterUsage theo dạng 1-1
    @OneToOne
    @JoinColumn(name = "water_usage_id", nullable = false)
    @JsonProperty("waterUsage")
    private WaterUsage waterUsage;

    @JsonProperty("totalAmount")
    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @JsonProperty("status")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvoiceStatus status;

    @JsonProperty("createdAt")
    @Column(name = "created_at", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}
