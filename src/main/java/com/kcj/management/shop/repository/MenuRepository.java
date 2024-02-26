package com.kcj.management.shop.repository;

import com.kcj.management.shop.model.menu.Menu;
import com.kcj.management.shop.model.menu.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByMenuCategoryAndUsedTrue(MenuCategory menuCategory);

    Menu findByNameAndUsedTrue(String name);

    List<Menu> findByNameLikeAndUsedTrue(String name);
}