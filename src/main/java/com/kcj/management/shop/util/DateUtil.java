package com.kcj.management.shop.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

public class DateUtil {
    public static LocalDateTime todayStartDateTime() {
        return LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
    }

    public static LocalDateTime todayEndDateTime() {
        return LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
    }

    public static LocalDateTime yearStartDateTime(int year){
        return LocalDateTime.now().withYear(year).withMonth(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
    }

    public static LocalDateTime yearEndDateTime(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, Calendar.DECEMBER, 1);
        ;
        return LocalDateTime.now().withYear(year).withMonth(12).withDayOfMonth(calendar.getActualMaximum(Calendar.DAY_OF_MONTH)).withHour(0).withMinute(0).withSecond(0);
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
