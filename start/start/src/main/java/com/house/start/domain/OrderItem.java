package com.house.start.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
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

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private OrderItemStatus orderItemStatus;

    @Builder
    public OrderItem (Order order, Item item, int orderPrice, int count, Delivery delivery, OrderItemStatus orderItemStatus) {
        this.order = order;
        this.item = item;
        this.orderPrice = orderPrice;
        this.count = count;
        this.delivery = delivery;
        this. orderItemStatus = orderItemStatus;
    }

    //==생성 메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    //==연관관계 편의 메서드==//
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrderItem(this);
    }

}
