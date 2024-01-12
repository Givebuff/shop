package com.kcj.management.shop.controller;

import com.kcj.management.shop.service.MenuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MenuCategoryController {
    @Autowired
    private MenuCategoryService menuCategoryService;

    @GetMapping("/menu/category/registry")
    public String menuCategoryRegistryPage(){
        return "menu_category_registry";
    }

    @PostMapping("/menu/category/registry")
    public String menuCategoryRegistry(){
        return "redirect:/menu/category/registry";
    }
}
