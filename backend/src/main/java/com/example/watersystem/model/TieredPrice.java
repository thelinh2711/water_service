package com.example.watersystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TieredPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // Khóa ngoại tham chiếu đến bảng water_service_type
    @JoinColumn(name = "service_id", nullable = false)
    @JsonProperty("serviceType")
    private WaterServiceType serviceType;

    @JsonProperty("minValue")
    @Column(name = "min_value", nullable = false)
    private Integer minValue;

    @JsonProperty("maxValue")
    @Column(name = "max_value", nullable = false)
    private Integer maxValue;

    @JsonProperty("pricePerM3")
    @Column(name = "price_per_m3", nullable = false)
    private BigDecimal pricePerM3;
}
