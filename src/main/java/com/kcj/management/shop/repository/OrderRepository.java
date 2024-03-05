package com.kcj.management.shop.repository;

import com.kcj.management.shop.model.order.Department;
import com.kcj.management.shop.model.order.Ledger;
import com.kcj.management.shop.model.order.*;
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
    List<Order> findByDepartment(Department department);
    List<Order> findByDepartmentAndLedger(Department department, Ledger ledger);
    List<Order> findByDepartmentAndLedgerIsNotNull(Department department);
    List<Order> findByAddress(Address address);
}
