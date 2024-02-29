package com.kcj.management.shop.repository;

import com.kcj.management.shop.model.Department;
import com.kcj.management.shop.model.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LedgerRepository extends JpaRepository<Ledger, Long> {
    List<Ledger> findByDepartment(Department department);

}
