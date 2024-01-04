package com.kcj.management.shop.model.staff;

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
public class Staff {
    @Id @GeneratedValue
    private Long id;

    private String name;

    private StaffRole memberRole;
}
