package com.kcj.management.shop.model;

import com.kcj.management.shop.model.menu.Menu;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class File {
    @Id @GeneratedValue
    private Long id;

    private String originalName;

    private String name;

    private String directory;

    private Long size;

    @OneToOne
    private Menu menu;
}
