package com.kcj.management.shop.util;

import com.kcj.management.shop.model.menu.Menu;
import com.kcj.management.shop.model.menu.MenuCategory;
import com.kcj.management.shop.model.staff.Staff;
import com.kcj.management.shop.model.staff.StaffRole;
import com.kcj.management.shop.repository.StaffRepository;
import com.kcj.management.shop.service.MenuCategoryService;
import com.kcj.management.shop.service.MenuOptionService;
import com.kcj.management.shop.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitLoader implements CommandLineRunner {
    @Autowired private MenuCategoryService menuCategoryService;
    @Autowired private MenuService menuService;
    @Autowired private MenuOptionService menuOptionService;
    @Override
    public void run(String... args) throws Exception {
    }

    private void initMenuCategories() {
        menuCategoryService.saveMenuCategory(
                MenuCategory.builder()
                        .name("닭")
                        .build());
        menuCategoryService.saveMenuCategory(
                MenuCategory.builder()
                        .name("굴")
                        .build());
        menuCategoryService.saveMenuCategory(
                MenuCategory.builder()
                        .name("사이드")
                        .build());
    }
}
