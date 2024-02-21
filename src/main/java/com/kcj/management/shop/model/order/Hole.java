package com.kcj.management.shop.model.order;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("H")
public class Hole extends Order{
    @ManyToOne
    private HoleTable holeTable;

    public void takeOrder(List<OrderItem> orderItems, HoleTable holeTable){
        getOrderItems().addAll(orderItems);
        this.holeTable = holeTable;
        setPaymentDate(LocalDateTime.now());
    }
}
