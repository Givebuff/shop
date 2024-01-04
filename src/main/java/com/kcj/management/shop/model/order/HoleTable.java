package com.kcj.management.shop.model.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class HoleTable {
    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private Long number;
}
