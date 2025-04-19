package com.example.watersystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonProperty("customer")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "apartment_id", nullable = false)
    @JsonProperty("apartment")
    private Apartment apartment;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    @JsonProperty("serviceType")
    private WaterServiceType serviceType;

    @JsonProperty("signedDate")
    @Column(name = "signed_date", nullable = false)
    private LocalDate signedDate;
}
