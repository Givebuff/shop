package com.kcj.management.shop.model.order;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kcj.management.shop.util.StringUtil;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Ledger {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Department department;

    private String name;

    @OneToMany(mappedBy = "ledger", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Order> orders = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime paymentDate;

    private String htmlId;

    @PostUpdate
    public void afterSave(){
        if(htmlId == null) {
            htmlId = getClass().getSimpleName().toLowerCase() + StringUtil.DELIMITER +  String.format("%06d", id);
        }
    }

    public int getTotalPrice(){
        int total = 0;
        for(Order order: orders){
            total += order.getTotalPrice();
        }
        return total;
    }
}
