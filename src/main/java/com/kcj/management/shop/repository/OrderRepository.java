package com.kcj.management.shop.repository;

import com.kcj.management.shop.model.order.Order;
import com.kcj.management.shop.model.order.PayType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByPayType(PayType payType);

    List<Order> findByPaymentDateBetween(Timestamp startTime, Timestamp endTime);
}
