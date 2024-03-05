package com.kcj.management.shop.repository;

import com.kcj.management.shop.model.order.Department;
import com.kcj.management.shop.model.order.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LedgerRepository extends JpaRepository<Ledger, Long> {
    List<Ledger> findByDepartment(Department department);

}
