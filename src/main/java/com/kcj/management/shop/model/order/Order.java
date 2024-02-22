package com.kcj.management.shop.model.order;

import com.kcj.management.shop.model.Ledger;
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
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Ledger ledger;

    @ManyToOne
    private Address address;

    private int tableNum;

    private String reservationContent;

    private int people;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Enumerated(EnumType.STRING)
    private WorkStatus workStatus;

    @Enumerated(EnumType.STRING)
    private PayType payType;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime reservationDate;

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

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
    }

    public void removeOrderItem(OrderItem orderItem){
        orderItems.remove(orderItem);
    }

    public void completePayment(PayType payType){
        setPayType(payType);
        paymentDate = LocalDateTime.now();
    }
}
