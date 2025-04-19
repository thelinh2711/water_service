package com.example.watersystem.model;

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
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Liên kết với Invoice
    @OneToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    @JsonProperty("invoice")
    private Invoice invoice;

    @JsonProperty("paidDate")
    @Column(name = "paid_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime paidDate;

    @JsonProperty("amount")
    @Column(nullable = false)
    private BigDecimal amount;
}
