package com.kcj.management.shop.model.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {
    @Id @GeneratedValue
    private Long id;

    private String address;

    private String phoneNumber;
}
