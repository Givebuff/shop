package com.kcj.management.shop.controller.rest;

import com.kcj.management.shop.model.dto.RestApiRequest;
import com.kcj.management.shop.model.dto.order.OrderDTO;
import com.kcj.management.shop.model.dto.order.OrderSettle;
import com.kcj.management.shop.model.order.Order;
import com.kcj.management.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/rest/kitchen/order/list")
    public List<OrderDTO> getKitchenOrderList(){
        return orderService.kitchenOrderList();
    }

    @GetMapping("/rest/order/settle/{year}")
    public List<OrderSettle> getOrderSettleYear(@PathVariable("year") int year){
        return orderService.orderSettlesYear(year);
    }

    @PutMapping("/kitchen/cook/complete")
    public List<OrderDTO> putKitchenOrderComplete(@RequestParam("id") Long id) {
        orderService.orderCookComplete(id);
        return orderService.kitchenOrderList();
    }

    @GetMapping("/rest/order/hole/{tableNum}")
    public OrderDTO getOrderHoleTableNum(@PathVariable("tableNum") int tableNum){
        return null;
    }

    @GetMapping("/rest/order/hole/table/count")
    public int getOrderHoleTableCount() {
        return OrderService.TABLE_COUNT;
    }

    @GetMapping("/rest/order/used/hole/table")
    public boolean[] getTodayUsedHoleTable() {
        return orderService.todayUsedHoleTable();
    }

    @GetMapping("/rest/hole/table/order/{tableNum}")
    public RestApiRequest getHoleTableOrder(@PathVariable("tableNum") int tableNum) {
        return new RestApiRequest(orderService.getHoleTableOrder(tableNum));
    }
}
