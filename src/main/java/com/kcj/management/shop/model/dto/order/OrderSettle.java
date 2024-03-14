package com.kcj.management.shop.model.dto.order;

import com.kcj.management.shop.model.order.OrderType;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderSettle {
    private String menuName;
    private String menuOptionName;
    private int price;
    private int totalPrice;
    private OrderType orderType;
    private Long count1;
    private Long count2;
    private Long count3;
}
