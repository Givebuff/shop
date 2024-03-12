package com.kcj.management.shop.model.menu;

import com.kcj.management.shop.util.StringUtil;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuCategory {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @Builder.Default
    private boolean used = true;

    private String htmlId;

    @PostPersist
    public void afterSave(){
        if(htmlId == null) {
            htmlId = getClass().getSimpleName().toLowerCase() + StringUtil.DELIMITER +  String.format("%06d", id);
        }
    }
}
