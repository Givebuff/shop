package com.kcj.management.shop.model.order;

public enum WorkStatus {
    RESERVATION, COOK, COMPLETE;

    public static String getKorean(WorkStatus workStatus) {
        String result = "";

        switch (workStatus) {
            case RESERVATION -> result = "예약";
            case COOK -> result = "조리중";
            case COMPLETE -> result = "조리완료";
        }

        return result;
    }
}
