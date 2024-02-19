package com.kcj.management.shop.service;

import com.kcj.management.shop.model.menu.Menu;
import com.kcj.management.shop.model.menu.MenuOption;
import com.kcj.management.shop.repository.MenuOptionRepository;
import com.kcj.management.shop.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuOptionService {
    @Autowired
    private MenuOptionRepository menuOptionRepository;
    @Autowired
    private MenuService menuService;

    public void saveMenuOption(MenuOption menuOption){
        menuOptionRepository.save(menuOption);
    }

    public MenuOption findById(Long id) {
        Optional<MenuOption> optionalMenuOption = menuOptionRepository.findById(id);

        if(optionalMenuOption.isPresent()){
            return optionalMenuOption.get();
        } else {
            throw new RuntimeException("error : " + id + " menuoption id not exist!!");
        }
    }

    public void unusedMenuOption(Long id){
        findById(id).setUsed(false);
    }

    public List<MenuOption> getUsedMenuOption(Long id){
        Menu menu = menuService.findById(id);
        List<MenuOption> menuOptions = new ArrayList<>();

        for(MenuOption menuOption: menu.getMenuOptions()){
            if(menuOption.isUsed()){
                menuOptions.add(menuOption);
            }
        }

        return menuOptions;
    }
}
