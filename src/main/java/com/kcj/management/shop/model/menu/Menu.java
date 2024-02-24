package com.kcj.management.shop.model.menu;

import com.kcj.management.shop.util.StringPrefixedSequenceIdGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_seq")
    @GenericGenerator(
            name = "menu_seq",
            strategy = "com.kcj.management.shop.util.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "MENU_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%09d") })
    private String id;

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
