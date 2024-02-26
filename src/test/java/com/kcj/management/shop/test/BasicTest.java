package com.kcj.management.shop.test;

import com.kcj.management.shop.model.menu.Menu;
import com.kcj.management.shop.util.StringUtil;
import org.junit.jupiter.api.Test;

public class BasicTest {

    @Test
    void test12(){
        String a = "aaa-000100";

        System.out.println(StringUtil.suffixAndRemoveZero(a));
    }
}
