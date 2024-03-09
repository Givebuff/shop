package com.kcj.management.shop.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
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
        List<Order> orders = orderService.findAll();
        System.out.println("This is flag");
        System.out.println(orders.size());
        System.out.println("////////////////////////////////////////////////");
        System.out.println(orders);

        return orders;
    }

    @GetMapping("/rest/order/today/dto")
    public List<OrderDTO> getOrderTodayDTOList(){return orderService.orderTodayDTOS();}
}
