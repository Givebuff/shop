package com.kcj.management.shop.controller;

import com.kcj.management.shop.model.menu.MenuOption;
import com.kcj.management.shop.service.MenuOptionService;
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
public class MenuOptionController {
    @Autowired
    private MenuOptionService menuOptionService;
    @Autowired
    private MenuService menuService;

    @GetMapping("/menu/option/registry")
    public String registryMenuOptionPage(Model model){
        model.addAttribute("menus", menuService.findByUsedTrue());
        return "/menu/option/registry";
    }

    @PostMapping("/menu/option/registry")
    public String registryMenuOption(
            @RequestParam("menu") Long menu,
            @RequestParam("name") String name,
            @RequestParam("content") String content,
            @RequestParam("price") String price){

        menuOptionService.saveMenuOption(MenuOption.builder()
                .content(content)
                .name(name)
                .price(StringUtil.priceToInt(price))
                .menu(menuService.findById(menu))
                .build());

        return "redirect:/menu";
    }

    @GetMapping("/menu/option/manage")
    public String menuOptionManagePage(Model model) {
        model.addAttribute("menuOptions", menuOptionService.findByUsedTrue());
        return "/menu/option/manage";
    }

    @GetMapping("/menu/option/change/{id}")
    public String menuOptionChangePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("menuOption", menuOptionService.findById(id));
        model.addAttribute("menus", menuService.findByUsedTrue());
        return "/menu/option/change";
    }

    @PostMapping("/menu/option/change")
    public String menuOptionChange(
            @RequestParam("id") Long id,
            @RequestParam("menu") Long menuId,
            @RequestParam("name") String name,
            @RequestParam("content") String content,
            @RequestParam("price") String price
    ) {
        menuOptionService.unusedMenuOption(id);
        menuOptionService.saveMenuOption(MenuOption.builder()
                .menu(menuService.findById(menuId))
                .name(name)
                .content(content)
                .price(StringUtil.priceToInt(price))
                .build());
        return "redirect:/menu";
    }
}
