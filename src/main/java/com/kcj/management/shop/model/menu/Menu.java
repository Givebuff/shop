package com.kcj.management.shop.model.menu;

import com.kcj.management.shop.model.File;
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
public class Menu {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @Lob
    private String content;

    private boolean view;

    @OneToOne(mappedBy = "menu", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private File file;

    private int price;

    @ManyToOne
    private MenuCategory menuCategory;

    @OneToMany(mappedBy = "menu")
    private List<MenuOption> menuOptions = new ArrayList<>();
}
