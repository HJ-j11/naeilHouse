package com.house.start.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Consumer {

    @Id @GeneratedValue
    @Column(name = "consumer_id")
    private Long id;

    @OneToMany(mappedBy = "consumer")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "consumer")
    private List<Like> likes = new ArrayList<>();

    private String name; // 소비자 이름

    @Column(name = "id")
    private String cId; // 아이디

    private String pwd; // 비밀번호

}
