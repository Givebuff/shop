package com.kcj.management.shop.controller;

import com.kcj.management.shop.model.menu.Menu;
import com.kcj.management.shop.model.menu.MenuCategory;
import com.kcj.management.shop.service.MenuCategoryService;
import com.kcj.management.shop.service.MenuService;
import com.kcj.management.shop.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuCategoryService menuCategoryService;

    @GetMapping("/menu")
    public String menuPage(){
        return "menu";
    }
    @GetMapping("/menu/registry")
    public String menuRegistryPage(Model model){
        model.addAttribute("categories", menuCategoryService.findAll());

        return "menu_registry";
    }

    @PostMapping("/menu/registry")
    public String menuRegistry(
            @RequestParam Long categoryId,
            @RequestParam String name,
            @RequestParam String price,
            @RequestParam String content
            ){
        menuService.saveMenu(
                Menu.builder()
                        .name(name)
                        .price(StringUtil.priceToInt(price))
                        .content(content)
                        .menuCategory(menuCategoryService.findById(categoryId))
                        .build());

        return "redirect:/menu";
    }

    @GetMapping("/menu/change/{id}")
    public String changeMenuPage(@PathVariable("id") String id, Model model){
        model.addAttribute("menu", menuService.findById(id));
        return "/menu/change";
    }

    @PostMapping("/menu/change")
    public String changeMenu(
            @RequestParam String id,
            @RequestParam Long categoryId,
            @RequestParam String name,
            @RequestParam String price,
            @RequestParam String content){

        Menu preMenu = menuService.findById(id);
        Menu menu = Menu.builder()
                .name(name)
                .menuCategory(menuCategoryService.findById(categoryId))
                .price(StringUtil.priceToInt(price))
                .content(content)
                .menuOptions(preMenu.getMenuOptions())
                .build();

        menuService.unusedMenu(preMenu.getId());
        menuService.saveMenu(menu);

        return "redirect:/menu";
    }
}
