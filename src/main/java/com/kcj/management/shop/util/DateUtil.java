package com.kcj.management.shop.util;

import java.time.LocalDateTime;

public class DateUtil {
    public static LocalDateTime intToLocalDateTime(int y, int M, int d, int h, int m, int s){
        return LocalDateTime.of(y, M, d, h, m, s);
    }

    public static LocalDateTime intToLocalDateTime(int y, int M, int d, int h, int m){
        return intToLocalDateTime(y,M,d,h,m,59);
    }

    public static LocalDateTime intToLocalDateTime(int y, int M, int d, int h){
        return intToLocalDateTime(y,M,d,h,59);
    }

    public static LocalDateTime intToLocalDateTime(int y, int M, int d){
        return intToLocalDateTime(y,M,d,23);
    }
}
