package com.kcj.management.shop.test;

import com.kcj.management.shop.model.menu.Menu;
import org.junit.jupiter.api.Test;

public class BasicTest {

    @Test
    void test12(){
        tt(new Menu());
    }

    void tt(Object o) {
        System.out.println(o.getClass().getName());
    }
}
