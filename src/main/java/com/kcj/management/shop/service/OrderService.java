package com.kcj.management.shop.service;

import com.kcj.management.shop.exception.IdNotFoundException;
import com.kcj.management.shop.model.order.Order;
import com.kcj.management.shop.model.order.OrderItem;
import com.kcj.management.shop.model.order.OrderType;
import com.kcj.management.shop.model.order.WorkStatus;
import com.kcj.management.shop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void saveOrder(Order order){
        orderRepository.save(order);
    }

    public Order findById(Long id){
        Optional<Order> optionalOrder = orderRepository.findById(id);

        if(optionalOrder.isPresent()){
            return optionalOrder.get();
        } else {
            throw new IdNotFoundException(getClass().getName());
        }
    }

    public void addOrderItem(Long orderId, OrderItem orderItem) {
        findById(orderId).addOrderItem(orderItem);
    }

    public void removeOrderItem(Long orderId, OrderItem orderItem) {
        findById(orderId).removeOrderItem(orderItem);
    }

    public List<Order> getNoPaymentOrderList() {
        return null;
    };
}
