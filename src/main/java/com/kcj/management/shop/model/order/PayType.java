package com.kcj.management.shop.model.order;

public enum PayType {
    CARD, MONEY, LEDGER;

    public static String getKorean(PayType payType) {
        String result = "";

        switch (payType) {
            case CARD -> result = "카드";
            case MONEY -> result = "현금";
            case LEDGER -> result = "장부";
        }

        return result;
    }
}
