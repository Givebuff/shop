package com.kcj.management.shop.model.order;

public enum OrderType {
    RESERVATION, HOLE, DELIVERY, TAKEOUT;

    public static String getKorean(OrderType orderType) {
        String result = "";

        switch (orderType) {
            case RESERVATION -> result = "예약";
            case HOLE -> result = "홀";
            case DELIVERY -> result = "배달";
            case TAKEOUT -> result = "포장";
        }

        return result;
    }
}