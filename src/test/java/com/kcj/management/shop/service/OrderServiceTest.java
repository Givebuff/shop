package com.kcj.management.shop.service;

import com.kcj.management.shop.model.Department;
import com.kcj.management.shop.model.menu.Menu;
import com.kcj.management.shop.model.menu.MenuCategory;
import com.kcj.management.shop.model.menu.MenuOption;
import com.kcj.management.shop.model.order.Order;
import com.kcj.management.shop.model.order.OrderItem;
import com.kcj.management.shop.model.order.OrderType;
import com.kcj.management.shop.model.order.WorkStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class OrderServiceTest {
    @SpyBean
    private MenuService menuService;
    @SpyBean
    private MenuCategoryService menuCategoryService;
    @SpyBean
    private MenuOptionService menuOptionService;
    @SpyBean
    private OrderService orderService;
    @SpyBean
    private DepartmentService departmentService;
    @SpyBean
    private OrderItemService orderItemService;

    @BeforeEach
    void saveOrder() {
        LocalDateTime reservationTime = LocalDateTime.now()
                .withYear(2025).withMonth(1).withDayOfMonth(10)
                .withHour(12).withMinute(30).withSecond(59);

        menuCategoryService.saveMenuCategory(MenuCategory.builder().name("닭").build());
        menuCategoryService.saveMenuCategory(MenuCategory.builder().name("굴").build());

        MenuOption menuOption1 = MenuOption.builder()
                .name("안 맵게")
                .content("안맵게")
                .price(0)
                .build();

        MenuOption menuOption2 = MenuOption.builder()
                .name("맵게")
                .content("맵게")
                .price(0)
                .build();

        MenuOption menuOption3 = MenuOption.builder()
                .name("당면 많이")
                .content("당면 많이")
                .price(2000)
                .build();

        menuOptionService.saveMenuOption(menuOption1);
        menuOptionService.saveMenuOption(menuOption2);
        menuOptionService.saveMenuOption(menuOption3);

        List<MenuOption> menuOptions1 = new ArrayList<>();
        menuOptions1.add(menuOption1);
        menuOptions1.add(menuOption2);
        menuOptions1.add(menuOption3);

        menuService.saveMenu(
                Menu.builder()
                        .name("안동찜닭")
                        .menuCategory(menuCategoryService.findByName("닭"))
                        .content("단짠")
                        .menuOptions(menuOptions1)
                        .price(28000)
                        .build()
        );
        menuService.saveMenu(
                Menu.builder()
                        .name("닭볶음탕")
                        .menuCategory(menuCategoryService.findByName("닭"))
                        .content("국물있는 매콤함")
                        .price(30000)
                        .build()
        );
        menuService.saveMenu(
                Menu.builder()
                        .name("굴국밥")
                        .menuCategory(menuCategoryService.findByName("굴"))
                        .content("굴 국밥")
                        .price(8000)
                        .build()
        );
        menuService.saveMenu(
                Menu.builder()
                        .name("굴탕")
                        .menuCategory(menuCategoryService.findByName("굴"))
                        .content("밥 따로")
                        .price(9000)
                        .build()
        );

        orderService.saveOrder(Order.builder().tableNum(3)
                .orderType(OrderType.RESERVATION)
                .workStatus(WorkStatus.RESERVATION)
                .reservationContent("코보")
                .people(4)
                .reservationDate(reservationTime)
                .build());
        departmentService.saveDepartment(
                Department.builder()
                        .section("환경과")
                        .dept("환경부")
                        .build()
        );
        departmentService.saveDepartment(
                Department.builder()
                        .section("환경과")
                        .dept("환경개선부")
                        .build()
        );
        departmentService.saveDepartment(
                Department.builder()
                        .section("시설과")
                        .dept("시설부")
                        .build()
        );
        departmentService.saveDepartment(
                Department.builder()
                        .section("시설과")
                        .dept("부품지원부")
                        .build()
        );
        departmentService.saveDepartment(
                Department.builder()
                        .section("시설과")
                        .dept("시설환경부")
                        .build()
        );
        departmentService.saveDepartment(
                Department.builder()
                        .section("민원과")
                        .dept("민원부")
                        .build()
        );

        Menu menu1 = menuService.findByName("안동찜닭");
        List<MenuOption> menu1Options = new ArrayList<>();
        menu1Options.add(menuOptionService.findByMenuAndName(menu1, "안맵게"));
        menu1Options.add(menuOptionService.findByMenuAndName(menu1, "당면 많이"));

        orderItemService.saveOrderItem(OrderItem.builder()
                .menu(menu1)
                .menuOptions(menu1Options)
                .count(2).build());

    }
}