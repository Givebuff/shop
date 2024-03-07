package com.kcj.management.shop.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtil {
    public static LocalDateTime todayStartDateTime() {
        return LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
    }

    public static LocalDateTime todayEndDateTime() {
        return LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
    }

    public static LocalDateTime getLocalDateTime(int year, int mon, int day, int hour, int min, int sec) {
        return LocalDateTime.now()
                .withYear(year).withMonth(mon).withDayOfMonth(day)
                .withHour(hour).withMinute(min).withSecond(sec);
    }

    public static LocalDate getLocalDate(int year, int mon, int day) {
        return LocalDate.now().withYear(year).withMonth(mon).withDayOfMonth(day);
    }
}
