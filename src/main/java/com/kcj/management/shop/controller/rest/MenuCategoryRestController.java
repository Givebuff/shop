package com.kcj.management.shop.controller.rest;

import com.kcj.management.shop.model.menu.MenuCategory;
import com.kcj.management.shop.service.MenuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuCategoryRestController {
    @Autowired private MenuCategoryService menuCategoryService;

    @DeleteMapping("/menu/category/delete")
    @Transactional
    public MenuCategory menuCategoryDelete(
            @RequestParam("id") Long id
    ) {
        menuCategoryService.unusedMenuCategory(id);
        return menuCategoryService.findById(id);
    }
}
