package com.kcj.management.shop.model.staff;

import org.springframework.beans.factory.annotation.Value;

public enum StaffRole {
    ADMIN, CENTER, HOLE, KITCHEN, COUNTER,GUEST;

    @Value("${shop.security.role.prefix}")
    private static String prefix;

    public static String getRole(StaffRole role){
        return prefix + role.toString();
    }
}
