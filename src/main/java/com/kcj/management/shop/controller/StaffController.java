package com.kcj.management.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaffController {

    @GetMapping("")
    public String initPage(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "/login";
    }
}
