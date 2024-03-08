package com.kcj.management.shop.repository.impl;

import com.kcj.management.shop.model.order.Order;
import com.kcj.management.shop.model.order.QOrder;
import com.kcj.management.shop.repository.custom.OrderRepositoryCustom;
import com.kcj.management.shop.util.DateUtil;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepositoryCustom {
    private final EntityManager queryManager;

    public OrderRepositoryImpl(EntityManager queryManager) {
        this.queryManager = queryManager;
    }

    @Override
    public List<Order> todayOrderList() {
        JPAQuery<Order> query = new JPAQuery<>(queryManager);
        QOrder qOrder = QOrder.order;

        return query.from(qOrder)
                .where(qOrder.reservationDate.coalesce(qOrder.orderDate)
                        .between(DateUtil.todayStartDateTime(), DateUtil.todayEndDateTime()))
                .fetch();
    }
}
