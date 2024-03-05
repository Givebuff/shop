package com.kcj.management.shop.repository;

import com.kcj.management.shop.model.menu.Menu;
import com.kcj.management.shop.model.menu.MenuOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MenuOptionRepository extends JpaRepository<MenuOption, Long> {
    List<MenuOption> findByMenuAndUsedTrue(Menu menu);

    Optional<MenuOption> findByMenuAndNameAndUsedTrue(Menu menu, String name);

    @Query("select o from MenuOption o join o.menu m where o.used order by m.name, o.name")
    List<MenuOption> findByUsedTrueOrderByMenuAndName();
}