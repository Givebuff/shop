package com.kcj.management.shop.model.supply;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @Builder.Default
    @JsonManagedReference
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    List<CompanyLedger> companyLedgers = new ArrayList<>();
}
