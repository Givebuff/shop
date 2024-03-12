package com.kcj.management.shop.controller;


import com.kcj.management.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    @Autowired private OrderService orderService;
    @Value("${shop.calc.table.count}") private Integer tableCount;

    @GetMapping("/hole/hole")
    public String holeManagePage(Model model) {
        model.addAttribute("tableCount", tableCount);
        return "/hole/hole";
    }

    @GetMapping("/kitchen/kitchen")
    public String kitchenManagePage(Model model) {
        return "/kitchen/kitchen";
    }
}
