package com.kcj.management.shop.model.order;

import com.kcj.management.shop.model.menu.Menu;
import com.kcj.management.shop.model.menu.MenuOption;
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
public class OrderItem {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Menu menu;

    @OneToMany
    private List<MenuOption> menuOptions = new ArrayList<>();

    private int count;

    public int getTotalPrice(){
        int total = menu.getPrice();

        for(MenuOption menuOption: menuOptions){
            total += menuOption.getPrice();
        }

        return total * count;
    }
}
