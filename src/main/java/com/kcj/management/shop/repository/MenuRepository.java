package com.kcj.management.shop.repository;

import com.kcj.management.shop.model.menu.Menu;
import com.kcj.management.shop.model.menu.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByMenuCategory(MenuCategory menuCategory);

    List<Menu> findByName(String name);

    List<Menu> findByNameLike(String name);
}