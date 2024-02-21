package com.kcj.management.shop.model.menu;

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
public class Menu {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @Lob
    private String content;

    @Builder.Default
    private boolean used = true;

    private int price;

    @ManyToOne
    private MenuCategory menuCategory;

    @Builder.Default
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MenuOption> menuOptions = new ArrayList<>();
}
