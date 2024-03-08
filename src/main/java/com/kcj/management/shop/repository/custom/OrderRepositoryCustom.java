package com.kcj.management.shop.repository.custom;

import com.kcj.management.shop.model.order.Order;

import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> todayOrderList();
}
