package com.kcj.management.shop.service;

import com.kcj.management.shop.repository.MenuCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuCategoryService {
    @Autowired
    private MenuCategoryRepository menuCategoryRepository;
}
