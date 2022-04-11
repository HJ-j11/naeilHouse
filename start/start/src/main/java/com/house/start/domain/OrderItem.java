package com.house.start.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
public class OrderItem {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    /**
     * 단방향
     */
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int orderPrice; // 주문 당시 가격

    private int count; // 주문 수량

}
