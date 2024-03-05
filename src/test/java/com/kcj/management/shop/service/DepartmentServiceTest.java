package com.kcj.management.shop.service;

import com.kcj.management.shop.model.order.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class DepartmentServiceTest {
    @SpyBean
    private DepartmentService departmentService;

    @BeforeEach
    void before(){
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
    void findAllSection() {
        for(String section : departmentService.findAllSection()) {
            System.out.println("----------------");
            for(Department department : departmentService.findBySection(section)) {
                System.out.println(department.getDept());
            }
        }
    }
}