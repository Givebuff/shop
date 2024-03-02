package com.kcj.management.shop.controller;

import com.kcj.management.shop.model.menu.MenuCategory;
import com.kcj.management.shop.service.MenuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MenuCategoryController {
    @Autowired
    private MenuCategoryService menuCategoryService;

    @GetMapping("/menu/category/registry")
    public String menuCategoryRegistryPage(){
        return "menu/category/registry";
    }

    @PostMapping("/menu/category/registry")
    public String menuCategoryRegistry (
            @RequestParam("name") String name
    ){
        MenuCategory menuCategory = new MenuCategory();
        menuCategory.setName(name);
        menuCategoryService.saveMenuCategory(menuCategory);

        return "redirect:/menu/category/manage";
    }

    @GetMapping("/menu/category/manage")
    public String menuCategoryManagePage(Model model){
        model.addAttribute("menuCategories", menuCategoryService.findByUsedTrue());
        return "/menu/category/manage";
    }

    @GetMapping("/menu/category/change/{id}")
    public String menuCategoryChangePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("menuCategory", menuCategoryService.findById(id));
        return "/menu/category/change";
    }

    @PostMapping("/menu/category/change")
    public String menuCategoryChangePage(
            @RequestParam("id") Long id,
            @RequestParam("name") String name
    ) {
        MenuCategory menuCategory = menuCategoryService.findById(id);
        menuCategory.setName(name);
        menuCategoryService.saveMenuCategory(menuCategory);
        return "redirect:/menu/category/manage";
    }

    @PostMapping("/menu/category/delete/{id}")
    public String menuCategoryDelete(
            @PathVariable("id") Long id
    ) {
        menuCategoryService.unusedMenuCategory(id);
        return "redirect:/menu/category/manage";
    }
}
