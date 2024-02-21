package com.kcj.management.shop.model.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HoleTable {
    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private Long number;
}
