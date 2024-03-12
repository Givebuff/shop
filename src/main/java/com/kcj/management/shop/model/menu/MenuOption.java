package com.kcj.management.shop.model.menu;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private Menu menu;

    @Builder.Default
    private boolean used = true;

    private String htmlId;

    @PostUpdate
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
