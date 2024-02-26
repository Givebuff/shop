package com.kcj.management.shop.model;

import com.kcj.management.shop.model.order.Order;
import com.kcj.management.shop.model.order.PayType;
import com.kcj.management.shop.util.StringUtil;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ledger {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Department department;

    private String name;

    @OneToMany
    private List<Order> orders = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime paymentDate;

    private String htmlId;

    @PostPersist
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
