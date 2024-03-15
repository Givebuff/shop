package com.kcj.management.shop.service;

import com.kcj.management.shop.exception.IdNotFoundException;
import com.kcj.management.shop.exception.NotExistException;
import com.kcj.management.shop.model.menu.Menu;
import com.kcj.management.shop.model.menu.MenuOption;
import com.kcj.management.shop.repository.MenuOptionRepository;
import com.kcj.management.shop.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuOptionService {
    @Autowired
    private MenuOptionRepository menuOptionRepository;

    @Transactional
    public void saveMenuOption(MenuOption menuOption){
        menuOptionRepository.save(menuOption);
    }

    public MenuOption findById(Long id) {
        Optional<MenuOption> optionalMenuOption = menuOptionRepository.findById(id);

        if(optionalMenuOption.isPresent()){
            return optionalMenuOption.get();
        } else {
            throw new IdNotFoundException(getClass().getName());
        }
    }

    public MenuOption findByMenuAndName(Menu menu, String name){
        Optional<MenuOption> optionalMenuOption = menuOptionRepository.findByMenuAndNameAndUsedTrue(menu, name);

        if(optionalMenuOption.isPresent()){
            return optionalMenuOption.get();
        } else {
            throw new NotExistException(getClass().getName());
        }

    }

    public List<MenuOption> findByUsedTrue() {
        return menuOptionRepository.findByUsedTrueOrderByMenuAndName();
    }

    @Transactional
    public void unusedMenuOption(Long id){
        findById(id).setUsed(false);
    }
}
