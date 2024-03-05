package com.kcj.management.shop.repository;

import com.kcj.management.shop.model.supply.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
