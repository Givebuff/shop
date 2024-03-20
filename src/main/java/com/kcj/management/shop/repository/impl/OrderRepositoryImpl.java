package com.kcj.management.shop.repository.impl;

import com.kcj.management.shop.model.dto.order.OrderDTO;
import com.kcj.management.shop.model.dto.order.OrderSettle;
import com.kcj.management.shop.model.menu.QMenu;
import com.kcj.management.shop.model.menu.QMenuOption;
import com.kcj.management.shop.model.order.*;
import com.kcj.management.shop.repository.custom.OrderRepositoryCustom;
import com.kcj.management.shop.service.OrderService;
import com.kcj.management.shop.util.DateUtil;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Override
    public List<OrderDTO> kitchenOrderList() {
        JPAQuery<Order> query = new JPAQuery<>(queryManager);
        QOrder order = QOrder.order;
        QOrderItem orderItem = QOrderItem.orderItem;

        List<Order> orders = query
                .from(order)
                .join(orderItem)
                .on(order.id.eq(orderItem.order.id))
                .where(order.workStatus.eq(WorkStatus.COOK)
                        .and(order.reservationDate.coalesce(order.orderDate)
                                .between(DateUtil.todayStartDateTime(), DateUtil.todayEndDateTime())
                        )
                        .and(orderItem.complete.isFalse()))
                .fetch();

        return OrderDTO.orderDTOList(orders);
    }

    @Override
    public List<OrderSettle> settleOrders(int year) {
        JPAQuery<OrderItem> query = new JPAQuery<>(queryManager);
        QOrder order = QOrder.order;
        QOrderItem orderItem = QOrderItem.orderItem;
        QMenu menu = QMenu.menu;
        QMenuOption menuOption = QMenuOption.menuOption;
        NumberExpression<Integer> totalPrice = menu.price.multiply(menu.name.count());

        return query
                .select(Projections.bean(OrderSettle.class,
                        menu.name.as("menuName"),
                        menu.price.as("price"),
                        menu.name.count().as("count1"),
                        totalPrice.as("totalPrice")))
                .from(order)
                .join(orderItem).on(order.id.eq(orderItem.order.id))
                .join(menu).on(menu.id.eq(orderItem.menu.id))
                .where(order.payType.isNotNull()
                        .and(order.workStatus.eq(WorkStatus.COMPLETE))
                        .and(order.paymentDate.between(DateUtil.yearStartDateTime(year), DateUtil.yearEndDateTime(year))))
                .groupBy(menu.name, menu.price)
                .fetch();
    }

    @Override
    public boolean[] todayUsedHoleTables() {
        JPAQuery<Order> query = new JPAQuery<>(queryManager);
        QOrder order = QOrder.order;

        List<Order> orders = query.from(order)
                .where(order.reservationDate.coalesce(order.orderDate)
                        .between(DateUtil.todayStartDateTime(), DateUtil.todayEndDateTime())
                        .and(order.orderType.eq(OrderType.HOLE))
                        .and(order.complete.isFalse()))
                .orderBy(order.tableNum.asc())
                .fetch();

        boolean [] booleans = new boolean[OrderService.TABLE_COUNT];
        Arrays.fill(booleans, false);

        for(Order item: orders) {
            booleans[item.getTableNum() - 1] = true;
        }

        return booleans;
    }
}
