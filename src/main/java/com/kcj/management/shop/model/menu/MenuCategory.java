package com.kcj.management.shop.model.menu;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

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
}
