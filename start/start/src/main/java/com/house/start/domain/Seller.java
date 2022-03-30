package com.house.start.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Seller {

    @Id @GeneratedValue
    @Column(name = "seller_id")
    private Long id;

    @OneToMany(mappedBy = "seller")
    private List<Item> items = new ArrayList<>();

    private String name;
    private String sId;
    private String pwd;

    private String storeName;
    private Boolean isApproved;
}
