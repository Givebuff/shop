package com.kcj.management.shop.model.order;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id @GeneratedValue
    private Long id;

    private String address;

    private String phoneNumber;
}
