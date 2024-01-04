package com.kcj.management.shop.model.menu;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MenuOption {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @Lob
    private String content;

    private int price;

    @ManyToOne
    private Menu menu;
}
