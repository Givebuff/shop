package com.kcj.management.shop.util;

import com.kcj.management.shop.model.menu.Menu;
import com.kcj.management.shop.model.menu.MenuCategory;
import com.kcj.management.shop.model.menu.MenuOption;
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
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlValue;

    @Override
    public void run(String... args) throws Exception {
        if(ddlValue.equals("validate")) return;
        initMenuCategories();
        initMenus();
        initMenuOptions();
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

    private void initMenus() {
        MenuCategory 닭 = menuCategoryService.findByName("닭");
        MenuCategory 굴 = menuCategoryService.findByName("굴");
        MenuCategory 사이드 = menuCategoryService.findByName("사이드");

        menuService.saveMenu(Menu.builder()
                .menuCategory(닭)
                .name("안동찜닭")
                .price(23000)
                .content("단짠 간장맛")
                .build());

        menuService.saveMenu(Menu.builder()
                .menuCategory(닭)
                .name("닭조림")
                .price(25000)
                .content("매콤하고 국물을 조린 빨간 닭조림")
                .build());

        menuService.saveMenu(Menu.builder()
                .menuCategory(닭)
                .name("닭볶음탕")
                .price(28000)
                .content("매콤하고 국물있는 빨간 닭볶음탕")
                .build());

        menuService.saveMenu(Menu.builder()
                .menuCategory(닭)
                .name("굴찜닭")
                .price(32000)
                .content("굴이 듬뿍 들어간 빨간 찜닭")
                .build());

        menuService.saveMenu(Menu.builder()
                .menuCategory(굴)
                .name("굴국밥")
                .price(8000)
                .content("밥이 말아서 나오는 국밥")
                .build());

        menuService.saveMenu(Menu.builder()
                .menuCategory(굴)
                .name("굴탕")
                .price(9000)
                .content("굴이 더 들어가고 밥이 따로 나오는 국밥")
                .build());

        menuService.saveMenu(Menu.builder()
                .menuCategory(굴)
                .name("굴파전")
                .price(15000)
                .content("철판으로 오래 따뜻한 굴파전")
                .build());

        menuService.saveMenu(Menu.builder()
                .menuCategory(굴)
                .name("굴부추전")
                .price(15000)
                .content("철판으로 오래 따뜻한 굴부추전")
                .build());

        menuService.saveMenu(Menu.builder()
                .menuCategory(사이드)
                .name("공기밥")
                .price(1500)
                .content("공기밥")
                .build());

        menuService.saveMenu(Menu.builder()
                .menuCategory(사이드)
                .name("음료수")
                .price(1000)
                .content("음료수")
                .build());
    }

    private void initMenuOptions() {
        Menu 안동찜닭 = menuService.findByName("안동찜닭");
        Menu 닭조림 = menuService.findByName("닭조림");
        Menu 닭볶음탕 = menuService.findByName("닭볶음탕");
        Menu 굴찜닭 = menuService.findByName("굴찜닭");

        setMenuOption(안동찜닭);
        setMenuOption(닭볶음탕);
        setMenuOption(굴찜닭);

        menuOptionService.saveMenuOption(MenuOption.builder()
                .menu(닭조림)
                .price(0)
                .name("안맵게")
                .content("안맵게")
                .build());

        menuOptionService.saveMenuOption(MenuOption.builder()
                .menu(닭조림)
                .price(0)
                .name("매운맛")
                .content("매운맛")
                .build());

        menuOptionService.saveMenuOption(MenuOption.builder()
                .menu(닭조림)
                .price(0)
                .name("보통맛")
                .content("보통맛")
                .build());
    }

    private void setMenuOption(Menu menu){
        menuOptionService.saveMenuOption(MenuOption.builder()
                .menu(menu)
                .price(0)
                .name("안맵게")
                .content("안맵게")
                .build());

        menuOptionService.saveMenuOption(MenuOption.builder()
                .menu(menu)
                .price(0)
                .name("매운맛")
                .content("매운맛")
                .build());

        menuOptionService.saveMenuOption(MenuOption.builder()
                .menu(menu)
                .price(0)
                .name("보통맛")
                .content("보통맛")
                .build());

        menuOptionService.saveMenuOption(MenuOption.builder()
                .menu(menu)
                .price(2000)
                .name("당면많이")
                .content("당면많이")
                .build());
    }
}
