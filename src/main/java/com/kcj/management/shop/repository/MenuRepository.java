package com.kcj.management.shop.repository;

import com.kcj.management.shop.model.menu.Menu;
import com.kcj.management.shop.model.menu.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByMenuCategoryAndUsedTrue(MenuCategory menuCategory);
    Menu findByNameAndUsedTrue(String name);
    List<Menu> findByNameLikeAndUsedTrue(String name);
    @Query("select m from Menu m join m.menuCategory c where m.used order by c.name, m.name")
    List<Menu> findByUsedTrueOrderByMenuCategoryAndName();
    List<Menu> findByUsedTrue();
}