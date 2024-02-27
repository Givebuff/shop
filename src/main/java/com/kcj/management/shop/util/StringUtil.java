package com.kcj.management.shop.util;

public class StringUtil {
    public static final String DELIMITER = "-";

    public static int priceToInt(String price){
        return Integer.parseInt(price.replaceAll(",", ""));
    }

    /**
     * 1  : name% <br>
     * 10 : %name <br>
     * 11 : %name%
     * @param name
     * @param delimiter
     * @return
     */
    public static String likeDelimiterString(String name, int delimiter) {
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

    public static String getPrefix(String value) {
        return value.substring(0, value.indexOf(DELIMITER));
    }

    public static String getSuffix(String value) {
        return value.substring(value.indexOf(DELIMITER) + 1);
    }

    public static String suffixAndRemoveZero(String value) {
        return getSuffix(value).replaceFirst("^0+(?!$)", "");
    }

    public static String toStringHeader(String o) {
        return "---------------------" + o + " Start----------------------\n";
    }

    public static String toStringFooter(String o) {
        return "---------------------" + o + " End----------------------\n";
    }

}
