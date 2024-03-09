package com.kcj.management.shop.repository.impl;

import com.kcj.management.shop.model.dto.order.OrderDTO;
import com.kcj.management.shop.model.order.Order;
import com.kcj.management.shop.model.order.QOrder;
import com.kcj.management.shop.repository.custom.OrderRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;

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

        return query
                .from(qOrder)
                .fetch();
    }

    @Override
    public List<OrderDTO> todayOrderTodayDTOList(){
        JPAQuery<OrderDTO> query = new JPAQuery<>(queryManager);
        QOrder qOrder = QOrder.order;


        return query.select(Projections.fields(OrderDTO.class
                , qOrder.id, qOrder.tableNum, qOrder.orderItems))
                .from(qOrder)
                .fetch();
    }
}
