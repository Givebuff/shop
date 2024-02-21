package com.kcj.management.shop.service;

import com.kcj.management.shop.model.menu.Menu;
import com.kcj.management.shop.model.menu.MenuCategory;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class MenuServiceTest {
    @SpyBean
    private MenuService menuService;
    @SpyBean
    private MenuCategoryService menuCategoryService;

    @BeforeEach
    void setValue(){
        menuCategoryService.saveMenuCategory(MenuCategory.builder().name("닭").build());
        menuCategoryService.saveMenuCategory(MenuCategory.builder().name("굴").build());
        menuService.saveMenu(
                Menu.builder()
                        .name("안동찜닭")
                        .menuCategory(menuCategoryService.findByName("닭"))
                        .content("단짠")
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
    void 카테고리_검색(){
        for(Menu menu : menuService.findByMenuCategory(menuCategoryService.findByName("닭"))){
            System.out.println(menu.getName());
        }
    }

    @Test
    void 이름_검색(){
        Assertions.assertEquals(menuService.findByName("안동찜닭").getPrice(), 28000);
    }

    @Test
    void 이름_like_검색(){
        for(Menu menu : menuService.findByNameLike("굴")){
            System.out.println(menu.getName());
            System.out.println(menu.getMenuCategory().getId());
        }
    }

    @Test
    void 메뉴_변경(){
        Menu preMenu = menuService.findByName("굴탕");
        System.out.println(preMenu.getId());

        Menu menu = Menu.builder()
                .name(preMenu.getName())
                .content(preMenu.getContent())
                .menuCategory(preMenu.getMenuCategory())
                .price(10000)
                .build();

        menuService.saveMenu(menu);
        System.out.println(menu.getId());

        menuService.unusedMenu(preMenu.getId());
        Assertions.assertEquals(menuService.findByName("굴탕").getPrice(), 10000);
    }
}