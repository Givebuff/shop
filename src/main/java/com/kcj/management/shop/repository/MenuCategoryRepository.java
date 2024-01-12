package com.kcj.management.shop.repository;

import com.kcj.management.shop.model.menu.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {
}
