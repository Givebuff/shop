package com.kcj.management.shop.service;

import com.kcj.management.shop.model.menu.Menu;
import com.kcj.management.shop.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    public Menu menuSave(Menu menu){
        return menuRepository.save(menu);
    }
}
