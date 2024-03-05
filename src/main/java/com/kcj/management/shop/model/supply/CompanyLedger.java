package com.kcj.management.shop.model.supply;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyLedger {
    @Id @GeneratedValue
    private Long id;

    private int price;

    @Temporal(TemporalType.DATE)
    private LocalDate purchaseDate;

    @ManyToOne
    private Company company;
}
