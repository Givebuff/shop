package com.kcj.management.shop.service;

import com.kcj.management.shop.model.menu.MenuCategory;
import com.kcj.management.shop.repository.MenuCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuCategoryService {
    @Autowired
    private MenuCategoryRepository menuCategoryRepository;

    public void saveMenuCategory(MenuCategory menuCategory){
        menuCategoryRepository.save(menuCategory);
    }

    public List<MenuCategory> findAll(){
        return menuCategoryRepository.findAll();
    }

    public MenuCategory findById(Long id){
        Optional<MenuCategory> optionalMenuCategory = menuCategoryRepository.findById(id);

        if(optionalMenuCategory.isPresent()){
            return optionalMenuCategory.get();
        } else {
            throw new RuntimeException("error : " + id + " menu category id not exist!!");
        }
    }

    public MenuCategory findByName(String name) {
        Optional<MenuCategory> optionalMenuCategory = menuCategoryRepository.findByNameAndUsedTrue(name);

        if(optionalMenuCategory.isPresent()) {
            return optionalMenuCategory.get();
        } else {
            throw new RuntimeException("error : " + name + " not exist value");
        }
    }

    public void unusedMenuCategory(Long id) {
        findById(id).setUsed(false);
    }
}
