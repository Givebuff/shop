package com.kcj.management.shop.controller;

import com.kcj.management.shop.model.menu.Menu;
import com.kcj.management.shop.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/menu")
    public String menuPage(){
        return "menu";
    }
    @GetMapping("/menu/registry")
    public String menuRegistryPage(){
        return "menu_registry";
    }

    @PostMapping("/menu/registry")
    public String menuRegistry(Menu menu){

        return "redirect:/menu/registry";
    }
}
