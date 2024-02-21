package com.kcj.management.shop.service;

import com.kcj.management.shop.model.menu.Menu;
import com.kcj.management.shop.model.menu.MenuCategory;
import com.kcj.management.shop.model.menu.MenuOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class MenuOptionServiceTest {
    @SpyBean
    private MenuService menuService;
    @SpyBean
    private MenuCategoryService menuCategoryService;
    @SpyBean
    private MenuOptionService menuOptionService;

    @BeforeEach
    void setValue(){
        menuCategoryService.saveMenuCategory(MenuCategory.builder().name("닭").build());
        menuCategoryService.saveMenuCategory(MenuCategory.builder().name("굴").build());

        MenuOption menuOption1 = MenuOption.builder()
                .name("안 맵게")
                .content("안맵게")
                .price(0)
                .build();

        MenuOption menuOption2 = MenuOption.builder()
                .name("맵게")
                .content("맵게")
                .price(0)
                .build();

        MenuOption menuOption3 = MenuOption.builder()
                .name("당면 많이")
                .content("당면 많이")
                .price(2000)
                .build();

        menuOptionService.saveMenuOption(menuOption1);
        menuOptionService.saveMenuOption(menuOption2);
        menuOptionService.saveMenuOption(menuOption3);

        List<MenuOption> menuOptions1 = new ArrayList<>();
        menuOptions1.add(menuOption1);
        menuOptions1.add(menuOption2);
        menuOptions1.add(menuOption3);

        menuService.saveMenu(
                Menu.builder()
                        .name("안동찜닭")
                        .menuCategory(menuCategoryService.findByName("닭"))
                        .content("단짠")
                        .menuOptions(menuOptions1)
                        .price(28000)
                        .build()
        );
        menuService.saveMenu(
                Menu.builder()
                        .name("닭볶음탕")
                        .menuCategory(menuCategoryService.findByName("닭"))
                        .content("국물있는 매콤함")
                        .price(30000)
                        .build()
        );
        menuService.saveMenu(
                Menu.builder()
                        .name("굴국밥")
                        .menuCategory(menuCategoryService.findByName("굴"))
                        .content("굴 국밥")
                        .price(8000)
                        .build()
        );
        menuService.saveMenu(
                Menu.builder()
                        .name("굴탕")
                        .menuCategory(menuCategoryService.findByName("굴"))
                        .content("밥 따로")
                        .price(9000)
                        .build()
        );
    }

    @Test
    void 검색(){
        for(MenuOption menuOption : menuService.findByName("안동찜닭").getMenuOptions()){
            System.out.println(menuOption.getName());
        }
        System.out.println("----------------");
        for(MenuOption menuOption : menuService.findByName("굴국밥").getMenuOptions()){
            System.out.println(menuOption.getName());
        }
    }

    @Test
    void 옵션_빼기(){
        Menu menu = menuService.findByName("안동찜닭");
        MenuOption menuOption = menu.getMenuOptions().get(0);

        System.out.println("size : " + menu.getMenuOptions().size());
        menu.getMenuOptions().remove(menuOption);

        System.out.println("size : " + menu.getMenuOptions().size());

        menuOptionService.findByMenuAndName(menu, menuOption.getName());
    }
}