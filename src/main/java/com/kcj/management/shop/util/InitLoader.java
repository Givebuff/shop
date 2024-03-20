package com.kcj.management.shop.util;

import com.kcj.management.shop.model.menu.Menu;
import com.kcj.management.shop.model.menu.MenuCategory;
import com.kcj.management.shop.model.menu.MenuOption;
import com.kcj.management.shop.model.order.*;
import com.kcj.management.shop.model.staff.Staff;
import com.kcj.management.shop.model.staff.StaffRole;
import com.kcj.management.shop.repository.StaffRepository;
import com.kcj.management.shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class InitLoader implements CommandLineRunner {
    @Autowired private MenuCategoryService menuCategoryService;
    @Autowired private MenuService menuService;
    @Autowired private MenuOptionService menuOptionService;
    @Autowired private DepartmentService departmentService;
    @Autowired private OrderService orderService;
    @Autowired private OrderItemService orderItemService;
    @Autowired private LedgerService ledgerService;
    @Autowired private AddressService addressService;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlValue;

    @Override
    public void run(String... args) throws Exception {
        if(ddlValue.equals("validate")) return;
        initMenuCategories();
        initMenus();
        initMenuOptions();
        initDepartment();
        initHoleOrder();
        initDeliveryOrder();
        initReservationOrder();
    }

    private void initMenuCategories() {
        menuCategoryService.saveMenuCategory(
                MenuCategory.builder()
                        .name("닭")
                        .build());
        menuCategoryService.saveMenuCategory(
                MenuCategory.builder()
                        .name("굴")
                        .build());
        menuCategoryService.saveMenuCategory(
                MenuCategory.builder()
                        .name("사이드")
                        .build());
    }

    private void initMenus() {
        MenuCategory 닭 = menuCategoryService.findByName("닭");
        MenuCategory 굴 = menuCategoryService.findByName("굴");
        MenuCategory 사이드 = menuCategoryService.findByName("사이드");

        menuService.saveMenu(Menu.builder()
                .menuCategory(닭)
                .name("안동찜닭")
                .price(23000)
                .content("단짠 간장맛")
                .build());

        menuService.saveMenu(Menu.builder()
                .menuCategory(닭)
                .name("닭조림")
                .price(25000)
                .content("매콤하고 국물을 조린 빨간 닭조림")
                .build());

        menuService.saveMenu(Menu.builder()
                .menuCategory(닭)
                .name("닭볶음탕")
                .price(28000)
                .content("매콤하고 국물있는 빨간 닭볶음탕")
                .build());

        menuService.saveMenu(Menu.builder()
                .menuCategory(닭)
                .name("굴찜닭")
                .price(32000)
                .content("굴이 듬뿍 들어간 빨간 찜닭")
                .build());

        menuService.saveMenu(Menu.builder()
                .menuCategory(굴)
                .name("굴국밥")
                .price(8000)
                .content("밥이 말아서 나오는 국밥")
                .build());

        menuService.saveMenu(Menu.builder()
                .menuCategory(굴)
                .name("굴탕")
                .price(9000)
                .content("굴이 더 들어가고 밥이 따로 나오는 국밥")
                .build());

        menuService.saveMenu(Menu.builder()
                .menuCategory(굴)
                .name("굴파전")
                .price(15000)
                .content("철판으로 오래 따뜻한 굴파전")
                .build());

        menuService.saveMenu(Menu.builder()
                .menuCategory(굴)
                .name("굴부추전")
                .price(15000)
                .content("철판으로 오래 따뜻한 굴부추전")
                .build());

        menuService.saveMenu(Menu.builder()
                .menuCategory(사이드)
                .name("공기밥")
                .price(1500)
                .content("공기밥")
                .build());

        menuService.saveMenu(Menu.builder()
                .menuCategory(사이드)
                .name("음료수")
                .price(1000)
                .content("음료수")
                .build());
    }

    private void initMenuOptions() {
        Menu 안동찜닭 = menuService.findByName("안동찜닭");
        Menu 닭조림 = menuService.findByName("닭조림");
        Menu 닭볶음탕 = menuService.findByName("닭볶음탕");
        Menu 굴찜닭 = menuService.findByName("굴찜닭");

        setMenuOption(안동찜닭);
        setMenuOption(닭볶음탕);
        setMenuOption(굴찜닭);

        menuOptionService.saveMenuOption(MenuOption.builder()
                .menu(닭조림)
                .price(0)
                .name("안맵게")
                .content("안맵게")
                .build());

        menuOptionService.saveMenuOption(MenuOption.builder()
                .menu(닭조림)
                .price(0)
                .name("매운맛")
                .content("매운맛")
                .build());

        menuOptionService.saveMenuOption(MenuOption.builder()
                .menu(닭조림)
                .price(0)
                .name("보통맛")
                .content("보통맛")
                .build());
    }

    private void setMenuOption(Menu menu){
        menuOptionService.saveMenuOption(MenuOption.builder()
                .menu(menu)
                .price(0)
                .name("안맵게")
                .content("안맵게")
                .build());

        menuOptionService.saveMenuOption(MenuOption.builder()
                .menu(menu)
                .price(0)
                .name("매운맛")
                .content("매운맛")
                .build());

        menuOptionService.saveMenuOption(MenuOption.builder()
                .menu(menu)
                .price(0)
                .name("보통맛")
                .content("보통맛")
                .build());

        menuOptionService.saveMenuOption(MenuOption.builder()
                .menu(menu)
                .price(2000)
                .name("당면많이")
                .content("당면많이")
                .build());
    }

    private void initDepartment() {
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

    private void initHoleOrder() {
        Menu 찜닭 = menuService.findByName("안동찜닭");
        Menu 굴국밥 = menuService.findByName("굴국밥");

        OrderItem holeItem = OrderItem.builder()
                .menu(찜닭)
                .count(2)
                .build();

        OrderItem holeItem2 = OrderItem.builder()
                .menu(굴국밥)
                .count(8)
                .build();

        holeItem.getMenuOptions().add(menuOptionService.findByMenuAndName(찜닭, "안맵게"));
        holeItem.getMenuOptions().add(menuOptionService.findByMenuAndName(찜닭, "당면많이"));

        orderItemService.saveOrderItem(holeItem);
        orderItemService.saveOrderItem(holeItem2);

        // 홀 주문 시작
        Order order = Order.builder()
                .orderType(OrderType.HOLE)
                .orderDate(LocalDateTime.now())
                .workStatus(WorkStatus.COOK)
                .tableNum(4)
                .build();
        order.addOrderItem(holeItem);
        order.addOrderItem(holeItem2);

        orderService.saveOrder(order);

        holeItem.setOrder(order);
        holeItem2.setOrder(order);
    }

    private void initDeliveryOrder() {
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
        address.setAddress("독산 스타벅스");
        address.setDetail("큰 테이블");
        address.setPhoneNumber("01032240276");
        addressService.saveAddress(address);

        Order deliveryOrder = Order.builder()
                .orderType(OrderType.DELIVERY)
                .orderDate(LocalDateTime.now())
                .workStatus(WorkStatus.COOK)
                .address(address)
                .build();
        deliveryOrder.addOrderItem(deliveryItem);
        deliveryOrder.addOrderItem(deliveryItem2);

        orderService.saveOrder(deliveryOrder);

        deliveryItem.setOrder(deliveryOrder);
        deliveryItem2.setOrder(deliveryOrder);
        // 배달 주문 종료
    }

    private void initReservationOrder() {
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
        Order order = Order.builder()
                .orderDate(LocalDateTime.now())
                .orderType(OrderType.RESERVATION)
                .tableNum(3)
                .people(4)
                .reservationDate(LocalDateTime.now().withHour(18).withMinute(0))
                .workStatus(WorkStatus.RESERVATION)
                .department(department)
                .build();
        order.addOrderItem(reservationItem);
        order.addOrderItem(reservationItem2);

        orderService.saveOrder(order);

        reservationItem.setOrder(order);
        reservationItem2.setOrder(order);
        // 예약 시작
    }
}
