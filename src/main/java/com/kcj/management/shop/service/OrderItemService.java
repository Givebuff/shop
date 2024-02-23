package com.kcj.management.shop.service;

import com.kcj.management.shop.exception.IdNotFoundException;
import com.kcj.management.shop.model.order.Order;
import com.kcj.management.shop.model.order.OrderItem;
import com.kcj.management.shop.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    public void saveOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    public OrderItem findById(Long id) {
        Optional<OrderItem> optionalOrderItem = orderItemRepository.findById(id);

        if(optionalOrderItem.isPresent()){
            return optionalOrderItem.get();
        } else {
            throw new IdNotFoundException(getClass().getName());
        }
    }
}
