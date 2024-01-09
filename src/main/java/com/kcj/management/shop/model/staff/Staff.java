package com.kcj.management.shop.model.staff;

import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private StaffRole staffRole;

    private String password;
}
