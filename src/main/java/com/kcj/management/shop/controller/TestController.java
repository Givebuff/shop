package com.kcj.management.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class TestController {

    @GetMapping("/test/hole")
    public String testHole(){
        return "/hole/hole";
    }

    @GetMapping("/test/kitchen")
    public String testKitchen(){
        return "/kitchen/kitchen";
    }

    @GetMapping("/test/test")
    public String testGet() {
        return "/test/test";
    }

    @PostMapping("/test/test")
    public String testPost(@RequestParam Map<String, String> formData){
        formData.forEach((key, value) -> {
            System.out.println("key : " + key + "   value : " + value);
        });

        return "redirect:/test/test";
    }
}
