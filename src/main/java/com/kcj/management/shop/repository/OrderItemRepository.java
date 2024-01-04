package com.kcj.management.shop.repository;

import com.kcj.management.shop.model.menu.Menu;
import com.kcj.management.shop.model.order.Order;
import com.kcj.management.shop.model.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);

    List<OrderItem> findByMenu(Menu menu);
}
