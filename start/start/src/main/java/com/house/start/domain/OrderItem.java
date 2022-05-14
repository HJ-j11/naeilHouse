package com.house.start.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "orderitem_id")
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

    @Column(name="review_yn")
    private boolean reviewYn = false;

    //==생성 메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

}
