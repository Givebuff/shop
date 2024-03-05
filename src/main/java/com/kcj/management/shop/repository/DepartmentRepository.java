package com.kcj.management.shop.repository;

import com.kcj.management.shop.model.order.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("select distinct m.section from Department m")
    List<String> findDistinctSection();

    List<Department> findBySection(String section);

    Optional<Department> findBySectionAndDept(String section, String dept);
}
