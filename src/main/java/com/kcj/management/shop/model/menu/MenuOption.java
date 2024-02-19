package com.kcj.management.shop.model.menu;

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
}
