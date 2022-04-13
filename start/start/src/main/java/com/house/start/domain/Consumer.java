package com.house.start.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Consumer {

    @Id @GeneratedValue
    @Column(name = "consumer_id")
    private Long id;

    @OneToMany(mappedBy = "consumer")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "consumer")
    private List<Like> likes = new ArrayList<>();

    private String name; // 소비자 이름
    private int point; // 포인트 금액

    @Column(name = "id")
    private String cId; // 아이디

    private String pwd; // 비밀번호

}
