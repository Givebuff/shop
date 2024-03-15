package com.kcj.management.shop.controller.rest;

import com.kcj.management.shop.model.menu.MenuOption;
import com.kcj.management.shop.service.MenuOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuOptionRestController {
    @Autowired private MenuOptionService menuOptionService;

    @DeleteMapping("/menu/option/delete")
    public MenuOption menuOptionDelete(@RequestParam("id") Long id) {
        menuOptionService.unusedMenuOption(id);
        return menuOptionService.findById(id);
    }
}
