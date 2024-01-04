package com.kcj.management.shop.repository;

import com.kcj.management.shop.model.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LedgerRepository extends JpaRepository<Ledger, Long> {
    List<Ledger> findByCategory1Like(String category1);

    List<Ledger> findByCategory1LikeAndCategory2Like(String category1, String category2);

    List<Ledger> findByName(String name);

    List<Ledger> findByCategory1LikeAndName(String category1, String name);

    List<Ledger> findByCategory1LikeAndCategory2LikeAndName(String category1, String category2, String name);

    List<Ledger> findByNameAndPerson(String name, String person);
}
