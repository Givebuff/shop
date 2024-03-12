package com.kcj.management.shop.repository.impl;

import com.kcj.management.shop.model.dto.order.OrderDTO;
import com.kcj.management.shop.model.order.*;
import com.kcj.management.shop.repository.custom.OrderRepositoryCustom;
import com.kcj.management.shop.util.DateUtil;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

public class OrderRepositoryImpl implements OrderRepositoryCustom {
    private final EntityManager queryManager;

    public OrderRepositoryImpl(EntityManager queryManager) {
        this.queryManager = queryManager;
    }

    @Override
    public List<Order> todayOrderList() {
        JPAQuery<Order> query = new JPAQuery<>(queryManager);
        QOrder order = QOrder.order;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(order.workStatus.ne(WorkStatus.COMPLETE))
                .and(order.payType.isNotNull())
                .or(order.payType.isNull());

        return query.from(order)
                .where(order.reservationDate.coalesce(order.orderDate)
                        .between(DateUtil.todayStartDateTime(), DateUtil.todayEndDateTime())
                        .and(builder)
                )
                .fetch();
    }

    @Override
    public List<OrderDTO> todayOrderTodayDTOList(){
        return OrderDTO.orderDTOList(todayOrderList());
    }
}
