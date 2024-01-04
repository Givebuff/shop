package com.kcj.management.shop.repository;

import com.kcj.management.shop.model.staff.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}
