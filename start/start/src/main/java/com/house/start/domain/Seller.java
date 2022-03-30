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

    private String name; // 판매자 이름
    private String sId; // 아이디
    private String pwd; // 비밀번호

    private String storeName; // 상호명
    private Boolean isApproved; // 관리자의 승인 여부
}
