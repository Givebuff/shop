package com.kcj.management.shop.model.dto.order;

import com.kcj.management.shop.model.order.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private int tableNum;
    private String reservationContent;
    private int people;
    private OrderType orderType;
    private WorkStatus workStatus;
    private LocalDateTime orderDate;
    private LocalDateTime reservationDate;
    private List<OrderItem> orderItems;
}
