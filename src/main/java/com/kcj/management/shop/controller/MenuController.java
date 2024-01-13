package com.kcj.management.shop.controller;

import com.kcj.management.shop.model.File;
import com.kcj.management.shop.model.menu.Menu;
import com.kcj.management.shop.model.menu.MenuCategory;
import com.kcj.management.shop.service.FileService;
import com.kcj.management.shop.service.MenuCategoryService;
import com.kcj.management.shop.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuCategoryService menuCategoryService;
    @Autowired
    private FileService fileService;

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
            @RequestParam Long category,
            @RequestParam("file") List<MultipartFile> files,
            @RequestParam String name,
            @RequestParam String price,
            @RequestParam String content
            ){
        MenuCategory menuCategory = menuCategoryService.findById(category);
        File file = fileService.fileSave(files.get(0));
        Menu menu = new Menu();
        menu.setName(name);
        menu.setPrice(Integer.parseInt(price.replaceAll(",", "")));
        menu.setContent(content);
        menu.setMenuCategory(menuCategory);
        menuService.menuSave(menu);
        menu.setFile(file);
        file.setMenu(menu);

        return "redirect:/menu/registry";
    }
}
