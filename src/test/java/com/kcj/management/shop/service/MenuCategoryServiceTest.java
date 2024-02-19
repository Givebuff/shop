package com.kcj.management.shop.service;

import com.kcj.management.shop.model.menu.MenuCategory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class MenuCategoryServiceTest {
    @SpyBean
    private MenuCategoryService menuCategoryService;

    @BeforeEach
    void setValue(){
        menuCategoryService.saveMenuCategory(MenuCategory.builder().name("닭").build());
        menuCategoryService.saveMenuCategory(MenuCategory.builder().name("국밥").build());
        menuCategoryService.saveMenuCategory(MenuCategory.builder().name("굴").build());
        menuCategoryService.saveMenuCategory(MenuCategory.builder().name("전").build());
        menuCategoryService.saveMenuCategory(MenuCategory.builder().name("사이드").build());
    }

    @Test
    void 메뉴_카테고리_읽기(){
        for(MenuCategory menuCategory : menuCategoryService.findAll()){
            System.out.println(menuCategory.getId());
            System.out.println(menuCategory.getName());
            System.out.println(menuCategory.isUsed());
            System.out.println("------------------------------------");
        }

        System.out.println(menuCategoryService.findByName("닭").getId());
    }

    @Test
    void 메뉴_카테고리_변경(){
        MenuCategory menuCategory = menuCategoryService.findByName("굴");
        menuCategoryService.unusedMenuCategory(menuCategory.getId());
        System.out.println(menuCategoryService.findByName("굴").isUsed());
    }
}