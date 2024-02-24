package com.kcj.management.shop.controller;

import com.kcj.management.shop.model.menu.MenuOption;
import com.kcj.management.shop.service.MenuOptionService;
import com.kcj.management.shop.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MenuOptionController {
    @Autowired
    private MenuOptionService menuOptionService;
    @Autowired
    private MenuService menuService;

    @PostMapping("/menu/option/registry")
    public String saveMenuOption(
            @RequestParam("menuId") String menuId,
            @RequestParam("name") String name,
            @RequestParam("content") String content,
            @RequestParam("price") int price){
        MenuOption menuOption = new MenuOption();
        menuOption.setName(name);
        menuOption.setContent(content);
        menuOption.setPrice(price);
        menuOption.setMenu(menuService.findById(menuId));

        menuOptionService.saveMenuOption(menuOption);

        return "redirect:/menu";
    }
}
