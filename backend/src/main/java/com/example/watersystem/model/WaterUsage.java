package com.example.watersystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WaterUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tham chiếu đến bảng hợp đồng
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = false)
    @JsonProperty("contract")
    private Contract contract;

    // YYYY-MM format, kiểu String
    @JsonProperty("month")
    @Column(nullable = false, length = 7)
    private String month;

    @JsonProperty("previousIndex")
    @Column(name = "previous_index", nullable = false)
    private Integer previousIndex;

    @JsonProperty("currentIndex")
    @Column(name = "current_index", nullable = false)
    private Integer currentIndex;
}
