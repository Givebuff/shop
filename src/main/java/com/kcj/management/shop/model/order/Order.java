package com.kcj.management.shop.model.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
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
    private Timestamp orderDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp paymentDate;

    @OneToMany(mappedBy = "order")
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
        getPaymentDate().setTime(System.currentTimeMillis());
    }
}
