package com.kcj.management.shop.repository;

import com.kcj.management.shop.model.order.Order;
import com.kcj.management.shop.model.order.OrderType;
import com.kcj.management.shop.model.order.PayType;
import com.kcj.management.shop.model.order.WorkStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByPayType(PayType payType);
    List<Order> findByWorkStatus(WorkStatus workStatus);
    List<Order> findByOrderType(OrderType orderType);
    List<Order> findByOrderDateBetween(LocalDateTime before, LocalDateTime after);
    List<Order> findByPaymentDateBetween(LocalDateTime before, LocalDateTime after);
    List<Order> findByPayTypeAndWorkStatus(PayType payType, WorkStatus workStatus);
}
