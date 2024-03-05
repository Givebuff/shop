package com.kcj.management.shop.model.menu;

import com.kcj.management.shop.util.StringUtil;
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
//    @Id @GeneratedValue
//    private Long id;

    @Id
    @GeneratedValue
    private Long id;

    private String htmlId;

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
    @OrderBy("name asc")
    private List<MenuOption> menuOptions = new ArrayList<>();

    @PostUpdate
    public void afterSave(){
        if(htmlId == null) {
            htmlId = getClass().getSimpleName().toLowerCase() + StringUtil.DELIMITER +  String.format("%06d", id);
        }
    }

    @Override
    public String toString(){
        return "Menu : " + name + " - " + price + "원 \n";
    }
}
