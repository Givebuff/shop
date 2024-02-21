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
@DiscriminatorValue("D")
public class Delivery extends Order{
    @ManyToOne
    private Address address;

    public void takeOrder(List<OrderItem> orderItems, Address address){
        getOrderItems().addAll(orderItems);
        this.address = address;
        setPaymentDate(LocalDateTime.now());
    }
}
