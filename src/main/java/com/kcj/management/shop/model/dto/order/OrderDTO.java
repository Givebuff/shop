package com.kcj.management.shop.model.dto.order;

import com.kcj.management.shop.model.order.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private String orderTypeKr;
    private WorkStatus workStatus;
    private String workStatusKr;
    private LocalDateTime orderDate;
    private LocalDateTime reservationDate;
    private List<OrderItem> orderItems = new ArrayList<>();

    public OrderDTO(Order order) {
        id = order.getId();
        tableNum = order.getTableNum();
        reservationContent = order.getReservationContent();
        people = order.getPeople();
        orderType = order.getOrderType();
        orderTypeKr = OrderType.getKorean(orderType);
        workStatus = order.getWorkStatus();
        workStatusKr = WorkStatus.getKorean(workStatus);
        orderDate = order.getOrderDate();
        reservationDate = order.getReservationDate();
        orderItems = order.getOrderItems();
    }

    public static List<OrderDTO> orderDTOList(List<Order> orders) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order: orders) {
            orderDTOList.add(new OrderDTO(order));
        }
        return orderDTOList;
    }
}
