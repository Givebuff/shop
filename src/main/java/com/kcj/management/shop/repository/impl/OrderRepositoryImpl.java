package com.kcj.management.shop.repository.impl;

import com.kcj.management.shop.model.dto.order.OrderDTO;
import com.kcj.management.shop.model.order.*;
import com.kcj.management.shop.repository.custom.OrderRepositoryCustom;
import com.kcj.management.shop.util.DateUtil;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
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
        return OrderDTO.orderDTOList(todayOrderList());
    }

    @Override
    public List<OrderDTO> testDTO() {
        JPAQuery<OrderDTO> query = new JPAQuery<>(queryManager);
        QOrder order = QOrder.order;
        QDepartment department= QDepartment.department;
        QLedger ledger = QLedger.ledger;
        QAddress address = QAddress.address1;
        QOrderItem orderItem = QOrderItem.orderItem;

        return query.select(orderDTOFields(order))
                .from(order)
                .leftJoin(order.department, department)
                .leftJoin(order.ledger, ledger)
                .leftJoin(order.address, address)
                .join(order.orderItems, orderItem)
                .where(order.reservationDate.coalesce(
                        order.orderDate).between(DateUtil.todayStartDateTime(),DateUtil.todayEndDateTime()))
                .fetch();
    }

    private QBean<OrderDTO> orderDTOFields(QOrder order) {
        return Projections.fields(OrderDTO.class,
                order.id, order.tableNum, order.reservationContent,
                order.people, order.orderType, order.workStatus,
                order.orderDate, order.reservationDate, order.orderItems.as("orderItem"));
    }

    private QBean<OrderDTO> orderDTOBean(QOrder order) {
        return Projections.bean(OrderDTO.class,
                order.id, order.tableNum, order.reservationContent,
                order.people, order.orderType, order.workStatus,
                order.orderDate, order.reservationDate, order.orderItems.as("orderItem"));
    }

    private ConstructorExpression<OrderDTO> orderDTOConstructor(QOrder order) {
        return Projections.constructor(OrderDTO.class,
                order.id, order.tableNum, order.reservationContent,
                order.people, order.orderType, order.workStatus,
                order.orderDate, order.reservationDate, order.orderItems.as("orderItem"));
    }
}
