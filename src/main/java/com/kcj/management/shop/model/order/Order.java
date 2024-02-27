package com.kcj.management.shop.model.order;

import com.kcj.management.shop.model.Department;
import com.kcj.management.shop.model.Ledger;
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
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Department department;

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

    private String htmlId;

    @PostPersist
    public void afterSave(){
        if(htmlId == null) {
            htmlId = getClass().getSimpleName().toLowerCase() + StringUtil.DELIMITER +  String.format("%06d", id);
        }
    }

    public void setOrderType(OrderType orderType){
        this.orderType = orderType;
        if(orderDate == null) orderDate = LocalDateTime.now();
    }

    public void setPayType(PayType payType){
        this.payType = payType;
        if(paymentDate == null) paymentDate = LocalDateTime.now();
    }

    public int getTotalPrice(){
        int total = 0;
        for(OrderItem orderItem: orderItems){
            total += orderItem.getTotalPrice();
        }
        return total;
    }

    public void addOrderItem(OrderItem orderItem){
        OrderItem findItem = orderItems.stream().filter(item -> item.getId().equals(orderItem.getId()))
                .findAny().orElse(null);

        if(findItem == null) orderItems.add(orderItem);
    }

    public void removeOrderItem(OrderItem orderItem){
        orderItems.stream().filter(item -> item.getId().equals(orderItem.getId()))
                .findAny().ifPresent(findItem -> orderItems.remove(orderItem));
    }

    @Override
    public String toString(){
        String result = StringUtil.toStringHeader(getClass().getSimpleName());

        for(OrderItem item: orderItems){
            result += item.toString();
        }

        result += "총 : " + getTotalPrice() + "원\n" + StringUtil.toStringFooter(getClass().getSimpleName());
        return result;
    }
}
