package com.kcj.management.shop.repository;

import com.kcj.management.shop.model.order.HoleTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HoleTableRepository extends JpaRepository<HoleTable, Long> {
    @Query("SELECT COALESCE(MAX(h.number),0)+1 FROM HoleTable h")
    int getMaxTable();
}
