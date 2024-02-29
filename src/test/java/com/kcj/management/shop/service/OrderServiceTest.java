package com.kcj.management.shop.service;

import com.kcj.management.shop.model.Department;
import com.kcj.management.shop.model.menu.Menu;
import com.kcj.management.shop.model.menu.MenuCategory;
import com.kcj.management.shop.model.menu.MenuOption;
import com.kcj.management.shop.model.order.*;
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
    @SpyBean private MenuService menuService;
    @SpyBean private MenuCategoryService menuCategoryService;
    @SpyBean private MenuOptionService menuOptionService;
    @SpyBean private OrderService orderService;
    @SpyBean private DepartmentService departmentService;
    @SpyBean private OrderItemService orderItemService;
    @SpyBean private AddressService addressService;
    @SpyBean private LedgerService ledgerService;

    @BeforeEach
    void saveOrder() {
        LocalDateTime reservationTime = LocalDateTime.now()
                .withYear(2025).withMonth(1).withDayOfMonth(10)
                .withHour(12).withMinute(30).withSecond(59);

        menuCategoryService.saveMenuCategory(MenuCategory.builder().name("닭").build());
        menuCategoryService.saveMenuCategory(MenuCategory.builder().name("굴").build());
        menuCategoryService.saveMenuCategory(MenuCategory.builder().name("사이드").build());

        Menu menu = Menu.builder()
                .name("안동찜닭")
                .menuCategory(menuCategoryService.findByName("닭"))
                .content("단짠")
                .price(28000)
                .build();

        menuService.saveMenu(menu);
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
        menuService.saveMenu(
                Menu.builder()
                        .name("굴파전")
                        .menuCategory(menuCategoryService.findByName("굴"))
                        .price(20000)
                        .build()
        );
        menuService.saveMenu(
                Menu.builder()
                        .name("공기밥")
                        .menuCategory(menuCategoryService.findByName("사이드"))
                        .price(1500)
                        .build()
        );

        MenuOption menuOption1 = MenuOption.builder()
                .name("안맵게")
                .content("안맵게")
                .menu(menu)
                .price(0)
                .build();

        MenuOption menuOption2 = MenuOption.builder()
                .name("맵게")
                .content("맵게")
                .menu(menu)
                .price(0)
                .build();

        MenuOption menuOption3 = MenuOption.builder()
                .name("당면 많이")
                .content("당면 많이")
                .menu(menu)
                .price(2000)
                .build();

        menuOptionService.saveMenuOption(menuOption1);
        menuOptionService.saveMenuOption(menuOption2);
        menuOptionService.saveMenuOption(menuOption3);

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
    }

    @Test
    void 홀테스트(){
        Menu 찜닭 = menuService.findByName("안동찜닭");
        List<MenuOption> menu1Options = new ArrayList<>();
        menu1Options.add(menuOptionService.findByMenuAndName(찜닭, "안맵게"));
        menu1Options.add(menuOptionService.findByMenuAndName(찜닭, "당면 많이"));

        Menu 굴국밥 = menuService.findByName("굴국밥");

        OrderItem holeItem = OrderItem.builder()
                .menu(찜닭)
                .menuOptions(menu1Options)
                .count(2).build();

        OrderItem holeItem2 = OrderItem.builder()
                .menu(굴국밥)
                .count(8)
                .build();

        orderItemService.saveOrderItem(holeItem);
        orderItemService.saveOrderItem(holeItem2);

        // 홀 주문 시작
        Order order = Order.builder()
                .orderType(OrderType.HOLE)
                .orderDate(LocalDateTime.now())
                .workStatus(WorkStatus.COOK)
                .tableNum(4)
                .build();

        order.getOrderItems().add(holeItem);
        order.getOrderItems().add(holeItem2);

        orderService.saveOrder(order);
        // 홀 주문 종료

        // 홀 조리 완료
        order.setWorkStatus(WorkStatus.COMPLETE);
        orderService.orderCookComplete(order.getId());
        // 홀 조리 완료

        // 홀 계산 시작
        order.setPayType(PayType.CARD);
        order.setPaymentDate(LocalDateTime.now());
        // 홀 계산 끝
    }

    @Test
    void 배달테스트(){
        Menu 찜닭 = menuService.findByName("안동찜닭");
        List<MenuOption> menu1Options = new ArrayList<>();
        menu1Options.add(menuOptionService.findByMenuAndName(찜닭, "안맵게"));

        Menu 공기밥 = menuService.findByName("공기밥");

        OrderItem deliveryItem = OrderItem.builder()
                .menu(찜닭)
                .menuOptions(menu1Options)
                .count(1).build();

        OrderItem deliveryItem2 = OrderItem.builder()
                .menu(공기밥)
                .count(4).build();

        orderItemService.saveOrderItem(deliveryItem);
        orderItemService.saveOrderItem(deliveryItem2);

        //배달 주문 시작
        Address address = new Address();
        address.setAddress("청도 아트빌");
        address.setDetail("201호");
        address.setPhoneNumber("01032240276");
        addressService.saveAddress(address);

        Order deliveryOrder = Order.builder()
                .orderType(OrderType.DELIVERY)
                .orderDate(LocalDateTime.now())
                .workStatus(WorkStatus.COOK)
                .address(address)
                .build();
        deliveryOrder.getOrderItems().add(deliveryItem);
        deliveryOrder.getOrderItems().add(deliveryItem2);

        orderService.saveOrder(deliveryOrder);
        // 배달 주문 종료

        // 배달 조리 완료
        Order cookedOrder = orderService.findById(deliveryOrder.getId());
        cookedOrder.setWorkStatus(WorkStatus.COMPLETE);
        orderService.orderCookComplete(cookedOrder.getId());
        // 배달 조리 완료

        // 배달 결제 시작
        Order deliveryCompleteOrder = orderService.findById(deliveryOrder.getId());
        deliveryCompleteOrder.setPayType(PayType.MONEY);
        deliveryCompleteOrder.setPaymentDate(LocalDateTime.now());
        // 배달 결제 완료
    }

    @Test
    void 예약테스트(){
        Menu 굴국밥 = menuService.findByName("굴국밥");
        Menu 굴파전 = menuService.findByName("굴파전");

        OrderItem reservationItem = OrderItem.builder()
                .menu(굴파전)
                .count(2).build();

        OrderItem reservationItem2 = OrderItem.builder()
                .menu(굴국밥)
                .count(4).build();
        orderItemService.saveOrderItem(reservationItem);
        orderItemService.saveOrderItem(reservationItem2);

        // 예약 시작
        Department department = departmentService.findBySectionAndDepartment("환경과", "환경부");
    }

    @Test
    void 포장테스트(){
        Menu 찜닭 = menuService.findByName("안동찜닭");
        List<MenuOption> menu1Options = new ArrayList<>();
        menu1Options.add(menuOptionService.findByMenuAndName(찜닭, "안맵게"));
        menu1Options.add(menuOptionService.findByMenuAndName(찜닭, "당면 많이"));

        Menu 굴국밥 = menuService.findByName("굴국밥");

        Menu 공기밥 = menuService.findByName("공기밥");
        Menu 굴파전 = menuService.findByName("굴파전");

    }
}