package com.kcj.management.shop.controller.rest;

import com.kcj.management.shop.model.menu.Menu;
import com.kcj.management.shop.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuRestController {
    @Autowired private MenuService menuService;

    @DeleteMapping("/menu/delete")
    @Transactional
    public Menu menuDelete(
            @RequestParam("id") Long id
    ) {
        menuService.unusedMenu(id);
        return menuService.findById(id);
    }
}
