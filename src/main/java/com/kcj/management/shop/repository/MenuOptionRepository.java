package com.kcj.management.shop.repository;

import com.kcj.management.shop.model.menu.Menu;
import com.kcj.management.shop.model.menu.MenuOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuOptionRepository extends JpaRepository<MenuOption, Long> {
    List<MenuOption> findByMenuAndUsedTrue(Menu menu);

    Optional<MenuOption> findByMenuAndNameAndUsedTrue(Menu menu, String name);
}