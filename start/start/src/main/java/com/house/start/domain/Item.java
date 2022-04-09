package com.house.start.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @OneToMany(mappedBy = "item")
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller; // 판매자

    private String name; // 상품 이름
    private int price; // 상품 가격
    private String info; // 상품 정보

    @OneToOne
    @JoinColumn(name="category_id")
    private Category category; // 상품 카테고리

    // 상품 상태
    private ItemStatus itemStatus;

}
