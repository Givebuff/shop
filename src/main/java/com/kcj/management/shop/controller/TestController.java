package com.kcj.management.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @GetMapping("/test/hole")
    public String testHole(){
        return "hole/hole";
    }

    @GetMapping("/test/kitchen")
    public String testKitchen(){
        return "kitchen/kitchen";
    }

    @GetMapping("/test/test")
    public String testGet(Model model) {
        List<String> mainCategories = Arrays.asList("카테고리 1", "카테고리 2", "카테고리 3");
        model.addAttribute("mainCategories", mainCategories);

        // 중분류와 소분류 데이터를 생성하여 모델에 추가
        Map<String, Map<String, List<String>>> subcategoryMap = new HashMap<>();
        subcategoryMap.put("카테고리 1", createSubcategoriesMap());
        subcategoryMap.put("카테고리 2", createSubcategoriesMap2());
        subcategoryMap.put("카테고리 3", createSubcategoriesMap3());
        model.addAttribute("subcategoryMap", subcategoryMap);
        return "test/test";
    }

    @GetMapping("/test/map")
    public String testKakaoMap(Model model) {
        return "test/kakaoMap";
    }

    @PostMapping("/test/test")
    public String testPost(@RequestParam Map<String, String> formData){
        formData.forEach((key, value) -> {
            System.out.println("key : " + key + "   value : " + value);
        });

        return "redirect:test/test";
    }

    // 간단한 중분류와 소분류 데이터 생성 메서드
    private Map<String, List<String>> createSubcategoriesMap() {
        Map<String, List<String>> subcategoriesMap = new HashMap<>();
        subcategoriesMap.put("서브카테고리 1", Arrays.asList("소분류 1-1", "소분류 1-2"));
        return subcategoriesMap;
    }

    // 간단한 중분류와 소분류 데이터 생성 메서드
    private Map<String, List<String>> createSubcategoriesMap2() {
        Map<String, List<String>> subcategoriesMap = new HashMap<>();
        subcategoriesMap.put("서브카테고리 2", Arrays.asList("소분류 2-1", "소분류 2-2"));
        return subcategoriesMap;
    }

    // 간단한 중분류와 소분류 데이터 생성 메서드
    private Map<String, List<String>> createSubcategoriesMap3() {
        Map<String, List<String>> subcategoriesMap = new HashMap<>();
        subcategoriesMap.put("서브카테고리 3", Arrays.asList("소분류 3-1", "소분류 3-2"));
        return subcategoriesMap;
    }
}
