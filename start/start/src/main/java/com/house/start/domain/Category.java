package com.house.start.domain;



import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter
@NoArgsConstructor
public class Category {

    @Id @GeneratedValue
    @Column(name="category_id")
    private long id;

    @Column(name="category_name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Item> items = new ArrayList<>();

    @Builder
    public Category(String name) {
        this.name = name;
    }

}
