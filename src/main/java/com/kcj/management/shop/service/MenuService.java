package com.kcj.management.shop.service;

import com.kcj.management.shop.exception.IdNotFoundException;
import com.kcj.management.shop.model.menu.Menu;
import com.kcj.management.shop.model.menu.MenuCategory;
import com.kcj.management.shop.repository.MenuRepository;
import com.kcj.management.shop.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private MenuCategoryService menuCategoryService;

    public void saveMenu(Menu menu){
        menuRepository.save(menu);
    }

    public Menu findById(String id){
        Optional<Menu> optionalMenu = menuRepository.findById(id);

        if(optionalMenu.isPresent()) {
            return optionalMenu.get();
        } else {
            throw new IdNotFoundException(getClass().getName());
        }
    }

    public List<Menu> findByMenuCategory(MenuCategory menuCategory){
        return menuRepository.findByMenuCategoryAndUsedTrue(menuCategory);
    }

    public Menu findByName(String name){
        return menuRepository.findByNameAndUsedTrue(name);
    }

    public List<Menu> findByNameLike(String name, int delimiter) {
        return menuRepository.findByNameLikeAndUsedTrue(StringUtil.delimiterString(name, delimiter));
    }
    public List<Menu> findByNameLike(String name) {
        return menuRepository.findByNameLikeAndUsedTrue(StringUtil.delimiterString(name, 0));
    }

    public void unusedMenu(String id){
        findById(id).setUsed(false);
    }
}
