package com.kcj.management.shop.model.order;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Department {
    @Id @GeneratedValue
    private Long id;
    private String section;
    private String dept;
    @Builder.Default
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Ledger> ledgers = new ArrayList<>();

    public int getTotalPrice() {
        return ledgers.stream().mapToInt(Ledger::getTotalPrice).sum();
    }
}
