package com.kcj.management.shop.service;

import com.kcj.management.shop.exception.IdNotFoundException;
import com.kcj.management.shop.model.order.OrderItem;
import com.kcj.management.shop.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional
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

    @Transactional
    public void orderItemCookComplete(Long id) {
        OrderItem orderItem = findById(id);
        orderItem.setComplete(true);
        orderItem.setCompleteDateTime(LocalDateTime.now());
    }
}
