package com.kcj.management.shop.controller.rest;

import com.kcj.management.shop.model.dto.order.OrderDTO;
import com.kcj.management.shop.model.order.Order;
import com.kcj.management.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderRestController {
    @Autowired private OrderService orderService;

    @GetMapping("/rest/order/today")
    public List<Order> getTodayOrderList() {
        return orderService.todayOrderList();
    }

    @GetMapping("/rest/order/today/dto")
    public List<OrderDTO> getOrderTodayDTOList(){return orderService.orderTodayDTOS();}
}
