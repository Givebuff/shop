package com.kcj.management.shop.model.order;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public abstract class Order {
    @Id @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private PayType payType;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime paymentDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public int getTotalPrice(){
        int total = 0;
        for(OrderItem orderItem: orderItems){
            total += orderItem.getTotalPrice();
        }
        return total;
    }

    public void addOrder(List<OrderItem> orderItems){
        getOrderItems().addAll(orderItems);
    }

    public void removeOrderItem(OrderItem orderItem){
        getOrderItems().remove(orderItem);
    }

    public void completePayment(PayType payType){
        setPayType(payType);
        paymentDate = LocalDateTime.now();
    }
}
