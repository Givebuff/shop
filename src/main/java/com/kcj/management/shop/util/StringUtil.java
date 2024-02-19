package com.kcj.management.shop.util;

public class StringUtil {
    public static int priceToInt(String price){
        return Integer.parseInt(price.replaceAll(",", ""));
    }

    public static String delimiterString(String name, int delimiter) {
        switch (delimiter) {
            case 1 :
                name = name + "%";
                break;
            case 10 :
                name = "%" + name;
                break;
            default:
                name = "%" + name + "%";
                break;
        }
        return name;
    }
}
