package com.house.start.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate; // 주문 일시

    @Builder
    public Order(Member member, LocalDateTime orderDate) {
        this.member = member;
        this.orderDate = LocalDateTime.now();
    }

    //==연관관계 편의 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    //==생성 메서드==//

    /**
     *  주문 객체 생성
     */
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {

        // 현재 소비자 포인트 - 총 주문 포인트
        int totalOrderPoint = 0;

        Order order = new Order();
//        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
            totalOrderPoint += orderItem.getOrderPrice();
        }

        // 현재 소비자 포인트 - 총 주문 포인트
        int pointBeforeOrder = member.getPoint();
        int pointAfterOrder = pointBeforeOrder - totalOrderPoint; // 예외 처리 필요
        member.setPoint(pointAfterOrder);
        order.setMember(member);

        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    public static Order createOrders(Member member, List<OrderItem> orderItems) {

        // 현재 소비자 포인트 - 총 주문 포인트
        int totalOrderPoint = 0;

        Order order = Order.builder()
                .member(member)
                        .build();

        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
            totalOrderPoint += orderItem.getOrderPrice();
        }

        // 현재 소비자 포인트 - 총 주문 포인트
        int pointBeforeOrder = member.getPoint();
        int pointAfterOrder = 0;

        if(totalOrderPoint >= pointBeforeOrder) {
            pointAfterOrder = pointBeforeOrder - totalOrderPoint;
        } else {
            // 보유 포인트보다 구매 포인트가 클 경우.

        }
        member.setPoint(pointAfterOrder);

        return order;
    }

}
