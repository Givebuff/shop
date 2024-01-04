package com.kcj.management.shop.model;

import com.kcj.management.shop.model.order.Order;
import com.kcj.management.shop.model.order.PayType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
// 장부
public class Ledger {
    @Id @GeneratedValue
    private Long id;

    private String top;

    private String sub;

    private String subSub;

    private String name;

    @OneToMany
    private List<Order> orders = new ArrayList<>();

    public int getTotalPrice(){
        int total = 0;
        for(Order order: orders){
            total += order.getTotalPrice();
        }
        return total;
    }

    public int getLedgerPrice(){
        int total = 0;
        for(Order order: orders){
            if(order.getPayType() == PayType.LEDGER){
                total += order.getTotalPrice();
            }
        }
        return total;
    }
}
