package com.kcj.management.shop.repository;

import com.kcj.management.shop.model.menu.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {
    Optional<MenuCategory> findByNameAndUsedTrue(String name);
    List<MenuCategory> findByUsedTrue();
}
