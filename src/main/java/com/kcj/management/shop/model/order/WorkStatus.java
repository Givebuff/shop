package com.kcj.management.shop.model.order;

public enum WorkStatus {
    RESERVATION, ORDER, COOK, COMPLETE, LEDGER, PAYMENT;

    public static String getKorean(WorkStatus workStatus) {
        String result = "";

        switch (workStatus) {
            case RESERVATION -> result = "예약";
            case ORDER -> result = "주문";
            case COOK -> result = "조리중";
            case COMPLETE -> result = "조리완료";
            case LEDGER -> result = "장부";
            case PAYMENT -> result = "결제완료";
        }

        return result;
    }
}
