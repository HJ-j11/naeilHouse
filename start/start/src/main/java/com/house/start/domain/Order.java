package com.house.start.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate; // 주문 일시

    private OrderStatus orderStatus; // 주문 상태 [주문, 취소, 완료]

<<<<<<< HEAD
=======
    //==연관관계 편의 메서드==//
    public void setMember(Consumer consumer) {
        this.consumer = consumer;
        consumer.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //==생성 메서드==//

    /**
     *  주문 객체 생성
     */
    public static Order createOrder(Consumer consumer, Delivery delivery, OrderItem... orderItems) {

        // 현재 소비자 포인트 - 총 주문 포인트
        int totalOrderPoint = 0;

        Order order = new Order();
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
            totalOrderPoint += orderItem.getOrderPrice();
        }

        // 현재 소비자 포인트 - 총 주문 포인트
        int pointBeforeOrder = consumer.getPoint();
        int pointAfterOrder = pointBeforeOrder - totalOrderPoint; // 예외 처리 필요
        consumer.setPoint(pointAfterOrder);
        order.setConsumer(consumer);


        order.setOrderStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }
>>>>>>> 62bccde (개발중)

}
