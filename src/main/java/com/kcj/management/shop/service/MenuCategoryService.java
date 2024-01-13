package com.kcj.management.shop.service;

import com.kcj.management.shop.model.menu.MenuCategory;
import com.kcj.management.shop.repository.MenuCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuCategoryService {
    @Autowired
    private MenuCategoryRepository menuCategoryRepository;

    public void menuCategorySave(MenuCategory menuCategory){
        menuCategoryRepository.save(menuCategory);
    }

    public List<MenuCategory> findAll(){
        return menuCategoryRepository.findAll();
    }

    public MenuCategory findById(Long id){
        return menuCategoryRepository.findById(id).orElse(null);
    }
}
