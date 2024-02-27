package com.kcj.management.shop.model.menu;

import com.kcj.management.shop.util.StringUtil;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuOption {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @Lob
    private String content;

    private int price;

    @ManyToOne
    private Menu menu;

    @Builder.Default
    private boolean used = true;

    private String htmlId;

    @PostPersist
    public void afterSave(){
        if(htmlId == null) {
            htmlId = getClass().getSimpleName().toLowerCase() + StringUtil.DELIMITER +  String.format("%06d", id);
        }
    }

    @Override
    public String toString(){
        return "MenuOption : " + name + " - " + price + "원 \n";
    }
}
