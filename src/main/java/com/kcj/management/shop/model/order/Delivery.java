package com.kcj.management.shop.model.order;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("D")
public class Delivery extends Order{
    @ManyToOne
    private Address address;

    public void takeOrder(List<OrderItem> orderItems, Address address){
        getOrderItems().addAll(orderItems);
        this.address = address;
        getOrderDate().setTime(System.currentTimeMillis());
    }
}
